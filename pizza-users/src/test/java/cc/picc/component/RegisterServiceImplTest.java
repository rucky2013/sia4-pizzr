package cc.picc.component;

import static cc.picc.commons.CredentialType.PASSWORD;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import cc.picc.entity.UserBasics;
import cc.picc.entity.UserContactInfo;
import cc.picc.entity.UserCredential;
import cc.picc.repository.UserBasicsRepository;
import cc.picc.repository.UserContactInfoRepository;
import cc.picc.repository.UserCredentialRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceImplTest {

	@Mock
	private UserBasicsRepository userRepo;

	@Mock
	private UserContactInfoRepository userContactInfoRepo;

	@Mock
	private UserCredentialRepository userCredentialRepo;

	@Mock
	private UserBasics userBasics;

	@Mock
	private UserCredential userCredential;

	@Spy
	private ArrayList<UserContactInfo> userContactInfoList;

	@Mock
	private UserContactInfo userContactInfo;

	@Mock
	private RegisterForm registerForm;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {

		when(userBasics.getId()).thenReturn(1001L);
		when(userBasics.getUsername()).thenReturn("hello");
		when(userBasics.getUsercode()).thenReturn("kitty");
		when(registerForm.getUserBasics()).thenReturn(userBasics);
		doReturn(userBasics).when(userRepo).save(any(UserBasics.class));

		when(userCredential.getId()).thenReturn(10001L);
		when(userCredential.getCredentialType()).thenReturn(PASSWORD);
		when(registerForm.getUserCredential()).thenReturn(userCredential);
		when(userCredentialRepo.save(any(UserCredential.class))).thenReturn(
				userCredential);

		when(userContactInfo.getId()).thenReturn(100001L);
		// 使用真实object进行spy
		userContactInfoList.add(userContactInfo);
		when(registerForm.getUserContactInfo()).thenReturn(userContactInfoList);
		when(userContactInfoRepo.save(any(Iterable.class))).thenReturn(
				userContactInfoList);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRegisterNewUser() {
		RegisterServiceImpl registerService = new RegisterServiceImpl(userRepo,
				userContactInfoRepo, userCredentialRepo);
		UserBasics ub = registerService.registerNewUser(registerForm);
		assertThat(1001L, is(equalTo(ub.getId())));
		assertThat("hello", is(equalTo(ub.getUsername())));
		assertThat("kitty", is(equalTo(ub.getUsercode())));
		verify(userRepo, times(1)).save(any(UserBasics.class));
		verify(userContactInfoRepo, times(1)).save(any(Iterable.class));
		verify(userCredentialRepo, times(1)).save(any(UserCredential.class));
	}

}
