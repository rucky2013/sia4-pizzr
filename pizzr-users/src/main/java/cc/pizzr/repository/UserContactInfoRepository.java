package cc.pizzr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.pizzr.entity.UserContactInfo;

/**
 * 
 * @author Justin
 *
 */
public interface UserContactInfoRepository extends JpaRepository<UserContactInfo, Long> {
}
