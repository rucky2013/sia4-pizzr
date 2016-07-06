package cc.pizzr.commons.users;

import cc.pizzr.commons.ViewResource;

/**
 * 
 * @author lijinting01
 * 
 */
public enum RegisterViewResource implements ViewResource {

	/**
	 * 注册表单页面
	 */
	REGISTER_FORM("users/register-form", "用户注册"),

	/**
	 * 注册成功页面
	 */
	REGISTER_SUCCESS("users/register-success", "注册成功");

	private String title;

	private String name;

	private RegisterViewResource(String name, String title) {
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

}
