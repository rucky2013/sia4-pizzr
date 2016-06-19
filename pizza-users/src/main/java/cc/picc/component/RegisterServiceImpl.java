package cc.picc.component;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
@Transactional(value = SUPPORTS)
@Component
@PropertySource(value = { "classpath:message.properties" })
public class RegisterServiceImpl implements RegisterService {

	@Value("contact.info.missing")
	private static String contactInfoMissingMessage;

	@Value("confirm.password.not.match")
	private static String confirmPasswordNotMatchMessage;

	@Autowired
	private UserBasicsRepository userRepo;

	@Autowired
	private UserContactInfoRepository userContactInfoRepo;

	@Autowired
	private UserCredentialRepository userCredentialRepo;

	private void ensureValid(RegisterForm registerForm) {
		// 检查二选一必填项是否正确
		if (isBlank(registerForm.getEmail()) && isBlank(registerForm.getMobile())) {
			throw new ValidationException(contactInfoMissingMessage);
		}
		// 检查两次输入密码是否一致
		if (!StringUtils.equals(registerForm.getConfirmPassword(), registerForm.getPassword())) {
			throw new ValidationException(confirmPasswordNotMatchMessage);
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional(value = REQUIRED)
	public UserBasics registerNewUser(RegisterForm registerForm) {
		ensureValid(registerForm);

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
