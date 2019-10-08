package me.giorgirokhadze.interview.gsg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
	private String commentLink;

	public String getCommentLink() {
		return commentLink;
	}

	public void setCommentLink(String commentLink) {
		this.commentLink = commentLink;
	}
}
