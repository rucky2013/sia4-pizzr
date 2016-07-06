package cc.pizzr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cc.pizzr.commons.CredentialType;
import cc.pizzr.entity.UserCredential;

/**
 * 用户凭据dao
 * 
 * @author Justin
 *
 */
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
	/**
	 * 
	 * @param credentialType
	 * @param userId
	 * @return
	 */
	@Query("select c from UserCredential c where c.credentialType = ?1 and c.userId = ?2")
	UserCredential findByUserId(CredentialType credentialType, Long userId);
}
