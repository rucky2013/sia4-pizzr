package cc.picc.component;

import static cc.picc.commons.ContactType.EMAIL_ADDRESS;
import static cc.picc.commons.ContactType.MOBILE_PHONE;
import static cc.picc.commons.CredentialType.PASSWORD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.crypto.password.PasswordEncoder;

import cc.picc.entity.UserBasics;
import cc.picc.entity.UserContactInfo;
import cc.picc.entity.UserCredential;
import cc.picc.validation.FieldEqualsConstraint;

/**
 * 
 * @author Justin
 * 
 */
@FieldEqualsConstraint(targetField = "confirmPassword", compareTo = { "password" }, message = "两次输入的密码不匹配")
public class RegisterForm {

	@NotNull
	@Size(min = 5, max = 255)
	private String username;

	private String usercode;

	private String mobile;

	@NotNull
	@Pattern(regexp = "(^$|^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)", message = "请输入有效的邮件地址")
	private String email;

	@NotNull
	@Size(min = 6, max = 16)
	private String password;

	@NotNull
	@Size(min = 6, max = 16)
	private String confirmPassword;

	/**
	 * 提取用户信息
	 * 
	 * @param registerForm
	 * @return
	 */
	public UserBasics getUserBasics() {
		UserBasics user = new UserBasics();
		user.setUsername(getUsername());
		user.setUsercode(getUsercode());
		Date now = DateTime.now().toDate();
		user.setCreated(now);
		user.setLatestModified(now);
		user.setEnabled(true);
		return user;
	}

	/**
	 * 提取用户联系信息
	 * 
	 * @return
	 */
	public List<UserContactInfo> getUserContactInfo() {
		List<UserContactInfo> uci = new ArrayList<>();

		if (StringUtils.isNotBlank(email)) {
			UserContactInfo emailAddr = new UserContactInfo();
			emailAddr.setContactType(EMAIL_ADDRESS);
			emailAddr.setPayload(email);
			uci.add(emailAddr);
		}
		if (StringUtils.isNotBlank(mobile)) {
			UserContactInfo mobileNumber = new UserContactInfo();
			mobileNumber.setContactType(MOBILE_PHONE);
			mobileNumber.setPayload(mobile);
			uci.add(mobileNumber);
		}
		return uci;
	}

	/**
	 * 获取用户凭证信息
	 * 
	 * @param passwordEncoder
	 * @return
	 */
	public UserCredential getUserCredential(PasswordEncoder passwordEncoder) {
		UserCredential uc = new UserCredential();
		uc.setContent(getPassword() != null ? passwordEncoder.encode(getPassword()) : null);
		Date timestamp = DateTime.now().toDate();
		uc.setCreated(timestamp);
		uc.setLatestModified(timestamp);
		uc.setCredentialType(PASSWORD);
		return uc;
	}

	@Override
	public String toString() {
		return String.format(
				"RegisterForm [username=%s, usercode=%s, mobile=%s, email=%s, password_=%s, confirmPassword_=%s]",
				username, usercode, mobile, email, password, confirmPassword);
	}

	public String getUsername() {
		return username;
	}

	public String getUsercode() {
		return usercode;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
