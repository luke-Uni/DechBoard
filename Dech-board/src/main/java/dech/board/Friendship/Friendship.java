package dech.board.Friendship;

import org.springframework.data.annotation.Id;

public class Friendship {

    @Id
    private int friendshipId;

    private String username1;
    private String username2;

    public Friendship(String user1, String user2) {
        this.username1 = user1;
        this.username2 = user2;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getUser1() {
        return username1;
    }

    public String getUser2() {
        return username2;
    }

    public void setUser1(String username1) {
        this.username1 = username1;
    }

    public void setUser2(String username2) {
        this.username2 = username2;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
