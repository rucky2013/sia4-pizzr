package cc.picc.component;

import static cc.picc.commons.CredentialType.PASSWORD;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cc.picc.entity.UserBasics;
import cc.picc.entity.UserContactInfo;
import cc.picc.entity.UserCredential;

/**
 * 
 * @author lijinting01
 * 
 */
public class RegisterFormTest {

	private RegisterForm registerForm;

	private RegisterForm newRegisterForm() {
		RegisterForm form = new RegisterForm();
		form.setConfirmPassword("123456");
		form.setPassword("123456");
		form.setEmail("lijinting01@picc.com.cn");
		form.setMobile("18811111111");
		form.setUsercode("12345687");
		form.setUsername("lijinting01");
		return form;
	}

	@Before
	public void setUp() throws Exception {
		if (registerForm == null) {
			this.registerForm = newRegisterForm();
		}
	}

	@Test
	public void testGetUserBasics() {
		UserBasics user = registerForm.getUserBasics();
		assertThat(registerForm.getUsername(), is(equalTo(user.getUsername())));
		assertThat(registerForm.getUsercode(), is(equalTo(user.getUsercode())));
		// 确保任何情况下不会返回Null值
		UserBasics possibleNull = registerForm.getUserBasics();
		assertThat(possibleNull, not(nullValue()));
	}

	@Test
	public void testGetUserContactInfo() {
		List<UserContactInfo> ucis = registerForm.getUserContactInfo();
		assertThat(ucis, not(empty()));
		// 确保任何情况下不会返回Null值
		List<UserContactInfo> ucis2 = new RegisterForm().getUserContactInfo();
		assertThat(ucis2, not(nullValue()));
	}

	@Test
	public void testGetUserCredential() {
		UserCredential uc = registerForm.getUserCredential();
		assertThat(PASSWORD, is(equalTo(uc.getCredentialType())));
		assertThat(registerForm.getPassword(), is(equalTo(uc.getContent())));
		// 确保任何情况下不会返回Null值
		UserCredential pn = new RegisterForm().getUserCredential();
		assertThat(pn, not(nullValue()));
	}

}
