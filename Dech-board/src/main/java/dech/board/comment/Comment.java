package dech.board.comment;

import java.time.LocalDateTime;

public class Comment {
	int commentId;
	int messageId;
	String creator;
	String content;
	LocalDateTime dateTime;

	public Comment(int commentId, int messageId, String creator, String content, LocalDateTime dateTime) {
		super();
		this.commentId = commentId;
		this.messageId = messageId;
		this.creator = creator;
		this.content = content;
		this.dateTime = dateTime;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
