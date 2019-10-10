package me.giorgirokhadze.interview.gsg.controllers.converters;

import me.giorgirokhadze.interview.gsg.model.Comment;
import me.giorgirokhadze.interview.gsg.model.Video;
import me.giorgirokhadze.interview.gsg.persistence.entities.CommentEntity;
import me.giorgirokhadze.interview.gsg.persistence.entities.VideoEntity;
import org.springframework.stereotype.Component;

@Component
public class VideoConverter implements Converter<VideoEntity, Video> {

	@Override
	public Video convert(VideoEntity originalValue) {
		final Video video = new Video();
		video.setVideoLink(originalValue.getVideoLink());
		if (!originalValue.getComments().isEmpty()) {
			// at this point we know that there is only one video comment on each video
			CommentEntity commentEntity = originalValue.getComments().get(0);
			Comment comment = new Comment();
			comment.setCommentLink(commentEntity.getCommentLink());
			video.setComment(comment);
		}
		return video;
	}
}
