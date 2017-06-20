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
		// 如果登录失败从request中获取认证异常信息,shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		System.out.println("=============================");
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(
					exceptionClassName)) {
				// 最终会抛给异常处理器
				//throw new CustomException("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				//throw new CustomException("用户名/密码错误");
			} else if ("randomCodeError".equals(exceptionClassName)) {
				//throw new CustomException("验证码错误");
			} else {
				//throw new Exception();// 最终在异常处理器生成未知错误
			}
		}
		return "list";

	}

}
