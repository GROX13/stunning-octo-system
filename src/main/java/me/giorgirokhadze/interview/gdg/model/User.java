package me.giorgirokhadze.interview.gdg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String username;
	private String locale;
	private int scheduledMinutes;
	private List<Video> videos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getScheduledMinutes() {
		return scheduledMinutes;
	}

	public void setScheduledMinutes(int scheduledMinutes) {
		this.scheduledMinutes = scheduledMinutes;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
}
