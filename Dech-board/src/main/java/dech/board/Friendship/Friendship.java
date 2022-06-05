package dech.board.Friendship;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Friendship")
public class Friendship {

    @Id
    private int friendshipId;

    private String username1;
    private String username2;

    public Friendship(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
