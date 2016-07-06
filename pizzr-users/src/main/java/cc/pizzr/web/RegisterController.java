package cc.pizzr.web;

import static cc.pizzr.commons.users.RegisterViewResource.REGISTER_FORM;
import static cc.pizzr.commons.users.RegisterViewResource.REGISTER_SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.pizzr.component.RegisterForm;
import cc.pizzr.component.RegisterService;
import cc.pizzr.entity.UserBasics;

/**
 * 
 * @author Justin
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

	private RegisterService registerService;

	/**
	 * 
	 * @param registerService
	 */
	@Autowired
	public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}

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
		return REGISTER_FORM.getName();
	}

	/**
	 * 
	 * @param registerForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(method = POST)
	public String register(@Valid RegisterForm registerForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return REGISTER_FORM.getName();
		}

		UserBasics userBasics = registerService.registerNewUser(registerForm);
		logger.info("Created A New User Request, User :" + userBasics);
		model.addAttribute("newUser", userBasics);
		return REGISTER_SUCCESS.getName();

	}
}
