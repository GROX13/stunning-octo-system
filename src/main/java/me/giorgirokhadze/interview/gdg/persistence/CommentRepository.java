package me.giorgirokhadze.interview.gdg.persistence;

import me.giorgirokhadze.interview.gdg.persistence.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
