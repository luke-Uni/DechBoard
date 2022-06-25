package dech.board.post;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

import org.bson.types.Binary;
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
	private Binary image;
	private int upVotes;
	private int downVotes;
	private Boolean important;

	private LocalDateTime creationDate;

	private static int counter = 1;

	private int messageBoardId;

	

	
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
		this.creationDate = LocalDateTime.now();

	}

	public Post(String title, String content) {

		this.postId = ++counter;
		// this.username = username;
		this.title = title;
		this.content = content;
		this.important = false;

	}

	public int getPostId() {
		return postId;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getMessageId() {
		return postId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
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

	public int getMessageBoardId() {
		return messageBoardId;
	}

	public void setMessageBoardId(int messageBoardId) {
		this.messageBoardId = messageBoardId;
	}



	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		String s;
		s = "MessageBoardID:"+messageBoardId +"Username: " + username + "\n" + "Title: " + title + "\n" + "Content: " + "\n" + content + "\n"
				+ "Important: " + important;
		return s;
	}

}
