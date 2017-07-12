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
	 * 为当前登录的Subject授予角色和权限
	 * 
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3
	 *      .1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String curentUser = (String) super.getAvailablePrincipal(principals);
		System.err.println("授权:" + curentUser);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 实际中可能会像上面注释的那样从数据库取得
		if (curentUser != null && curentUser.equals("jack")) {
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			authorizationInfo.addRole("admin");
			// 添加权限
			authorizationInfo.addStringPermission("admin:manager");
			System.out.println("已为用户[jack]赋予了[admin]角色和[admin:manage]权限");
			return authorizationInfo;
		}
		// 若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
		// 详见applicationContext.xml中的<bean id="shiroFilter">的配置
		return null;
	}

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 获取基于用户名和密码的令牌
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
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(String key, String value) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Session session = subject.getSession();
			System.out.println("session 超时时间:【" + session.getTimeout() + "】");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}

	}

}