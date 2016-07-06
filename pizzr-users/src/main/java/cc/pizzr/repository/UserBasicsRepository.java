package cc.pizzr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cc.pizzr.entity.UserBasics;

/**
 * 
 * @author Justin
 *
 */
public interface UserBasicsRepository extends JpaRepository<UserBasics, Long> {
	/**
	 * 查找用户
	 * 
	 * @param username
	 * @param enabled
	 * @return
	 */
	@Query("select u from UserBasics u where u.username = ?1 and u.enabled = ?2")
	UserBasics findByUsername(String username, boolean enabled);
}
