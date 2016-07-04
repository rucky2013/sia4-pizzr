package cc.picc.component.security;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.picc.commons.CredentialType;
import cc.picc.entity.UserBasics;
import cc.picc.entity.UserCredential;
import cc.picc.repository.UserBasicsRepository;
import cc.picc.repository.UserCredentialRepository;

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBasics ub = userRepo.findByUsername(username, true);
		if (ub == null) {
			throw new UsernameNotFoundException(usernameNotfoundMessage);
		}

		UserCredential uc = userCredentialRepo.findByUserId(CredentialType.PASSWORD, ub.getId());

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
