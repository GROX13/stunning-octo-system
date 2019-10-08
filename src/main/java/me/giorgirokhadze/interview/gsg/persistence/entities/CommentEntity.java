package me.giorgirokhadze.interview.gsg.persistence.entities;

import javax.persistence.*;

@Entity(name = "comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "comment_id", nullable = false)
	private String commentId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "video_id")
	private VideoEntity video;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public VideoEntity getVideo() {
		return video;
	}

	public void setVideo(VideoEntity video) {
		this.video = video;
	}

	public String getCommentLink() {
		return String.format("%s&lc=%s", getVideo().getVideoLink(), getCommentId());
	}
}
