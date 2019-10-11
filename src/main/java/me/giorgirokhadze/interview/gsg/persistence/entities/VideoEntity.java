package me.giorgirokhadze.interview.gsg.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SequenceGenerator(name = "videos_seq_gen", sequenceName = "videos_seq", allocationSize = 1)
@Entity(name = "videos")
public class VideoEntity {

	@Id
	@GeneratedValue(generator = "videos_seq_gen", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "video_id", nullable = false)
	private String videoId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<CommentEntity> comments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public void addComment(CommentEntity commentEntity) {
		comments.add(commentEntity);
	}

	public String getVideoLink() {
		return String.format("https://www.youtube.com/watch?v=%s", getVideoId());
	}
}
