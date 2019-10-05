package me.giorgirokhadze.interview.gdg.persistence;

import me.giorgirokhadze.interview.gdg.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}
