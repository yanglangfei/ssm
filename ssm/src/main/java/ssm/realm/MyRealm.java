package ssm.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class MyRealm extends AuthorizingRealm {

	/**
	 * Ϊ��ǰ��¼��Subject�����ɫ��Ȩ��
	 * 
	 * @see ������:�����и÷����ĵ���ʱ��Ϊ����Ȩ��Դ������ʱ
	 * @see ������:����ÿ�η�������Ȩ��Դʱ����ִ�и÷����е��߼�,�����������Ĭ�ϲ�δ����AuthorizationCache
	 * @see ���˸о���ʹ����Spring3
	 *      .1��ʼ�ṩ��ConcurrentMapCache֧��,����������Ƿ�����AuthorizationCache
	 * @see ����˵��������ݿ��ȡȨ����Ϣʱ,��ȥ����Spring3.1�ṩ�Ļ���,����ʹ��Shior�ṩ��AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// ��ȡ��ǰ��¼���û���,�ȼ���(String)principals.fromRealm(this.getName()).iterator().next()
		String curentUser = (String) super.getAvailablePrincipal(principals);
		System.err.println("��Ȩ:" + curentUser);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// ʵ���п��ܻ�������ע�͵����������ݿ�ȡ��
		if (curentUser != null && curentUser.equals("jack")) {
			// ���һ����ɫ,�������������ϵ����,����֤�����û�ӵ��admin��ɫ
			authorizationInfo.addRole("admin");
			// ���Ȩ��
			authorizationInfo.addStringPermission("admin:manager");
			System.out.println("��Ϊ�û�[jack]������[admin]��ɫ��[admin:manage]Ȩ��");
			return authorizationInfo;
		}
		// ���÷���ʲô������ֱ�ӷ���null�Ļ�,�ͻᵼ���κ��û�����/admin/listUser.jspʱ�����Զ���ת��unauthorizedUrlָ���ĵ�ַ
		// ���applicationContext.xml�е�<bean id="shiroFilter">������
		return null;
	}

	/**
	 * ��֤��ǰ��¼��Subject
	 * 
	 * @see ������:�����и÷����ĵ���ʱ��ΪLoginController.login()������ִ��Subject.login()ʱ
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// ��ȡ�����û��������������
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		if ("jack".equals(usernamePasswordToken.getUsername())) {
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					"", "", this.getName());
			this.setSession("currentUser", "jack");
			return authenticationInfo;

		}
		return null;
	}

	/**
	 * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ��
	 * 
	 * @see ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ��
	 */
	private void setSession(String key, String value) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Session session = subject.getSession();
			System.out.println("session ��ʱʱ��:��" + session.getTimeout() + "��");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}

	}

}
