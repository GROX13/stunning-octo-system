package me.giorgirokhadze.interview.gsg.persistence;

import me.giorgirokhadze.interview.gsg.persistence.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
