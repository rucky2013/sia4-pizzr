package cc.picc.component;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cc.picc.entity.UserBasics;
import cc.picc.entity.UserContactInfo;
import cc.picc.entity.UserCredential;
import cc.picc.repository.UserBasicsRepository;
import cc.picc.repository.UserContactInfoRepository;
import cc.picc.repository.UserCredentialRepository;

/**
 * 
 * @author Justin
 * 
 */
@Component
@PropertySource(value = { "classpath:message.properties" })
@Transactional(propagation = SUPPORTS, readOnly = true)
public class RegisterServiceImpl implements RegisterService {

	@Value("contact.info.missing")
	private static String contactInfoMissingMessage;

	@Value("confirm.password.not.match")
	private static String confirmPasswordNotMatchMessage;

	private UserBasicsRepository userRepo;

	private UserContactInfoRepository userContactInfoRepo;

	private UserCredentialRepository userCredentialRepo;

	/**
	 * 构造函数
	 * 
	 * @param userRepo
	 * @param userContactInfoRepo
	 * @param userCredentialRepo
	 */
	@Autowired
	public RegisterServiceImpl(UserBasicsRepository userRepo,
			UserContactInfoRepository userContactInfoRepo,
			UserCredentialRepository userCredentialRepo) {
		this.userRepo = userRepo;
		this.userContactInfoRepo = userContactInfoRepo;
		this.userCredentialRepo = userCredentialRepo;
	}

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = REQUIRED, isolation = READ_COMMITTED)
	public UserBasics registerNewUser(RegisterForm registerForm) {
		UserBasics userBasics = registerForm.getUserBasics();
		List<UserContactInfo> uciList = registerForm.getUserContactInfo();
		UserCredential uc = registerForm.getUserCredential();

		userBasics = userRepo.save(userBasics);
		Long usrId = userBasics.getId();
		for (UserContactInfo uci : uciList) {
			uci.setUserId(usrId);
		}
		userContactInfoRepo.save(uciList);

		uc.setUserId(usrId);
		userCredentialRepo.save(uc);

		return userBasics;
	}

}
