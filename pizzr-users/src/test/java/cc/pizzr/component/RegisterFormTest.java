package cc.pizzr.component;

import static cc.pizzr.commons.CredentialType.PASSWORD;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import cc.pizzr.component.RegisterForm;
import cc.pizzr.entity.UserBasics;
import cc.pizzr.entity.UserContactInfo;
import cc.pizzr.entity.UserCredential;

/**
 * 
 * @author lijinting01
 * 
 */
public class RegisterFormTest {

	private RegisterForm registerForm;

	private StandardPasswordEncoder pe;

	private RegisterForm newRegisterForm() {
		RegisterForm form = new RegisterForm();
		form.setConfirmPassword("123456");
		form.setPassword("123456");
		form.setEmail("lijinting01@pizzr.com.cn");
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

		pe = new StandardPasswordEncoder();
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
		UserCredential uc = registerForm.getUserCredential(pe);
		assertThat(PASSWORD, is(equalTo(uc.getCredentialType())));
		assertTrue(pe.matches(registerForm.getPassword(), (uc.getContent())));
		// 确保任何情况下不会返回Null值
		UserCredential pn = new RegisterForm().getUserCredential(pe);
		assertThat(pn, not(nullValue()));
	}

}
