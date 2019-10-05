package me.giorgirokhadze.interview.gsg.persistence;

import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}
