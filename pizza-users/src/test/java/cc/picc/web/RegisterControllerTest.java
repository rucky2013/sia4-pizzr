package cc.picc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import cc.picc.AppTest;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTest.class })
public class RegisterControllerTest {

	@Test
	public void testRegisterForm() throws Exception {
		RegisterController controller = new RegisterController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/register")).andExpect(view().name("users/register-form"));
	}

	@Test
	public void testRegister() throws Exception {
		RegisterController controller = new RegisterController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/register").param("username", "alibaba").param("usercode", "2342424234")
				.param("email", "lijnting@picc.com.cn").param("mobile", "2342424234").param("password", "pass@word1")
				.param("confirmPassword", "pass@word1")).andExpect(view().name("/users/register-success"));
	}

}
