package cc.picc.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.picc.component.RegisterForm;
import cc.picc.component.RegisterService;
import cc.picc.entity.UserBasics;

/**
 * 
 * @author Justin
 *
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	public static final String REGISTERFORM_VIEW_LOCATION = "users/register-form";
	public static final String REGISTERSUCCESS_VIEW_LOCATION = "users/register-success";
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private RegisterService registerService;

	/**
	 * 显示注册页面
	 * 
	 * @param registerForm
	 *            注册表单
	 * @return
	 */
	@RequestMapping(method = GET)
	public String registerForm(RegisterForm registerForm) {
		logger.debug("Show Register Form" + registerForm.toString());
		return REGISTERFORM_VIEW_LOCATION;
	}

	/**
	 * 
	 * @param registerForm
	 * @param model
	 * @return
	 */
	@RequestMapping(method = POST)
	public String register(@Valid RegisterForm registerForm, Model model) {
		UserBasics userBasics = registerService.registerNewUser(registerForm);
		logger.info("Created A New User Request, User :" + userBasics);
		model.addAttribute("newUser", userBasics);
		return REGISTERSUCCESS_VIEW_LOCATION;
	}
}
