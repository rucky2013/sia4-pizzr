package cc.picc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.picc.entity.UserCredential;

/**
 * 用户凭据dao
 * 
 * @author Justin
 *
 */
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
}
