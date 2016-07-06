package cc.pizzr.component.security;

import static cc.pizzr.commons.CredentialType.PASSWORD;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cc.pizzr.entity.UserBasics;
import cc.pizzr.entity.UserCredential;
import cc.pizzr.repository.UserBasicsRepository;
import cc.pizzr.repository.UserCredentialRepository;

/**
 * 
 * @author Justin
 *
 */
@Component
@PropertySource(value = { "classpath:securitymessage.properties" })
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${username.notfound.message}")
	private String usernameNotfoundMessage;

	private UserBasicsRepository userRepo;

	private UserCredentialRepository userCredentialRepo;

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = REQUIRED, readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		UserBasics ub = userRepo.findByUsername(username, true);
		if (ub == null) {
			throw new UsernameNotFoundException(usernameNotfoundMessage);
		}

		UserCredential uc = userCredentialRepo.findByUserId(PASSWORD, ub.getId());

		List<SimpleGrantedAuthority> list = Arrays.asList(new SimpleGrantedAuthority("USER"),
				new SimpleGrantedAuthority("ADMIN"));

		return new User(ub.getUsername(), uc.getContent(), list);
	}

	public UserBasicsRepository getUserRepo() {
		return userRepo;
	}

	@Autowired
	public void setUserRepo(UserBasicsRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setUserCredentialRepo(UserCredentialRepository userCredentialRepo) {
		this.userCredentialRepo = userCredentialRepo;
	}

}
