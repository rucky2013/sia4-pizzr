package cc.pizzr.component;

import cc.pizzr.entity.UserBasics;

/**
 * 用户注册业务组件
 * 
 * @author Justin
 *
 */
public interface RegisterService {

	/**
	 * 
	 * @param registerForm
	 * @return
	 */
	UserBasics registerNewUser(RegisterForm registerForm);
}
