package me.giorgirokhadze.interview.gsg.persistence;

import me.giorgirokhadze.interview.gsg.persistence.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
