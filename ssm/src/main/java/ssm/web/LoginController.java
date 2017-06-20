package ssm.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		// �����¼ʧ�ܴ�request�л�ȡ��֤�쳣��Ϣ,shiroLoginFailure����shiro�쳣���ȫ�޶���
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		System.out.println("=============================");
		// ����shiro���ص��쳣��·���жϣ��׳�ָ���쳣��Ϣ
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(
					exceptionClassName)) {
				// ���ջ��׸��쳣������
				//throw new CustomException("�˺Ų�����");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				//throw new CustomException("�û���/�������");
			} else if ("randomCodeError".equals(exceptionClassName)) {
				//throw new CustomException("��֤�����");
			} else {
				//throw new Exception();// �������쳣����������δ֪����
			}
		}
		return "list";

	}

}
