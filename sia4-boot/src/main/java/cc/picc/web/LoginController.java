package cc.picc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Justin
 *
 */
@Controller
public class LoginController {

	private static final String LOGIN_VIEW = "login";

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return LOGIN_VIEW;
	}
}
