package cc.picc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.picc.entity.UserContactInfo;

/**
 * 
 * @author Justin
 *
 */
public interface UserContactInfoRepository extends JpaRepository<UserContactInfo, Long> {
}
