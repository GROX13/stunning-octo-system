package me.giorgirokhadze.interview.gsg.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, updatable = false)
	private String username;

	@Column(name = "region_code", nullable = false)
	private String regionCode;

	@Column(name = "scheduled_minutes", nullable = false)
	private Integer scheduledMinutes;

	@Column(name = "encoded_password", nullable = false)
	private String encodedPassword;

	@OrderBy("id DESC")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<VideoEntity> videos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getScheduledMinutes() {
		return scheduledMinutes;
	}

	public void setScheduledMinutes(Integer scheduledMinutes) {
		this.scheduledMinutes = scheduledMinutes;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public List<VideoEntity> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoEntity> videos) {
		this.videos = videos;
	}

	public void addVideo(VideoEntity videoEntity) {
		videos.add(videoEntity);
	}
}
