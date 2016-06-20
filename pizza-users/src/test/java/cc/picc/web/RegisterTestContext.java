package cc.picc.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import cc.picc.component.RegisterForm;
import cc.picc.component.RegisterService;
import cc.picc.entity.UserBasics;

@EnableAutoConfiguration
public class RegisterTestContext {
	@Bean
	public RegisterService registerService() {
		RegisterService mock = mock(RegisterService.class);
		when(mock.registerNewUser(fineRegisterForm())).thenReturn(
				sampleUserBasics());
		return mock;
	}

	private static RegisterForm fineRegisterForm() {
		RegisterForm fine = new RegisterForm();
		fine.setUsername("wavejint");
		fine.setUsercode("13160312");
		fine.setEmail("lijinting@picc.com.cn");
		fine.setMobile("18811680317");
		fine.setPassword("pass@word1");
		fine.setConfirmPassword("pass@word2");
		return fine;
	}

	private static UserBasics sampleUserBasics() {
		UserBasics basics = new UserBasics();
		basics.setId(RandomUtils.nextLong(1000, 1099));
		basics.setUsername("wavejint");
		basics.setEnabled(true);
		basics.setUsercode("13160312");
		Date now = DateTime.now().toDate();
		basics.setCreated(now);
		basics.setLatestModified(now);
		return basics;
	}
}
