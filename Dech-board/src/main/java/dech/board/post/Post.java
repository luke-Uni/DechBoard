package dech.board.post;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

//import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Post")
public class Post {
	// Attributes for a post on the dashboard
	@Id
	private int postId;

	private String username;
	private String title;
	private String content;
	private int upVotes;
	private int downVotes;
	private Boolean important;

	private static int counter = 1;

	// The Attributes upVotes and downVotes dont belong in the Constructer
	@PersistenceConstructor
	@JsonCreator
	public Post(String title, String content,
			Boolean important) {

		this.postId = ++counter;
		// this.username = username;
		this.title = title;
		this.content = content;
		this.important = important;

	}



	public Post(String title, String content) {

		this.postId = ++counter;
		// this.username = username;
		this.title = title;
		this.content = content;
		this.important = false;

	}

	public int getMessageId() {
		return postId;
	}

	public void setMessageId(int messageId) {
		this.postId = messageId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	public int getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	@Override
	public String toString() {
		String s;
		s = "Username: " + username + "\n" + "Title: " + title + "\n" + "Content: " + "\n" + content + "\n"
				+ "Important: " + important;
		return s;
	}

}
