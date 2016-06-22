package cc.picc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.picc.entity.UserBasics;

public interface UserBasicsRepository extends JpaRepository<UserBasics, Long> {
}
