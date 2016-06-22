package cc.picc.web;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import cc.picc.component.RegisterForm;
import cc.picc.component.RegisterService;
import cc.picc.entity.UserBasics;

@RunWith(value = MockitoJUnitRunner.class)
public class RegisterControllerTest {

	@Mock
	private RegisterService registerService;

	@Mock
	private UserBasics sampleUserBasics;

	@Before
	public void setUp() {
		when(registerService.registerNewUser(any(RegisterForm.class)))
				.thenReturn(sampleUserBasics);
	}

	@Test
	public void testRegisterForm() throws Exception {
		RegisterController controller = new RegisterController(registerService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/register")).andExpect(
				view().name("users/register-form"));
	}

	/**
	 * 成功通过
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegister() throws Exception {
		RegisterController controller = new RegisterController(registerService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(
				post("/register").param("username", "alibaba")
						.param("usercode", "2342424234")
						.param("email", "lijnting@picc.com.cn")
						.param("mobile", "2342424234")
						.param("password", "pass@word1")
						.param("confirmPassword", "pass@word1")).andExpect(
				view().name("users/register-success"));
	}

	/**
	 * 因为密码不匹配返回表单页面
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterFailOnPasswordValidation() throws Exception {
		RegisterController controller = new RegisterController(registerService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(
				post("/register").param("username", "alibaba")
						.param("usercode", "2342424234")
						.param("email", "lijnting@picc.com.cn")
						.param("mobile", "2342424234")
						.param("password", "pass@word1")
						.param("confirmPassword", "pass@word2")).andExpect(
				view().name("users/register-form"));
	}

	/**
	 * 因为email不合规而不通过
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterFailOnEmailValidation() throws Exception {
		RegisterController controller = new RegisterController(registerService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(
				post("/register").param("username", "alibaba")
						.param("usercode", "2342424234")
						.param("email", "lijinitn")
						.param("mobile", "2342424234")
						.param("password", "pass@word1")
						.param("confirmPassword", "pass@word1")).andExpect(
				view().name("users/register-form"));
	}
}
