package me.giorgirokhadze.interview.gdg.persistence;

import me.giorgirokhadze.interview.gdg.persistence.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
