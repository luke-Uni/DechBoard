package dech.board.Friendship;

import org.springframework.stereotype.Service;

@Service
public class FriendshipService {

    public void createFriendshipRequest(String from, String to) {
        System.out.println(from + " " + to);
    }
}
