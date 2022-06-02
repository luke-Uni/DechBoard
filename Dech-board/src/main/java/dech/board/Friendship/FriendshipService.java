package dech.board.Friendship;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {

    @Autowired
    FriendshipRequestRepository requestRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    public void createFriendshipRequest(String from, String to) {
        if (from != null && to != null) {

            if (duplicateRequest(from, to)) {
                System.out.println(from + " already sent a Friendship Request to " + to);
            } else if (isAcceptance(from, to)) {
                System.out.println(from + " accepted the friendship request from " + to);
                createFriendship(from, to);
            } else if (!from.equalsIgnoreCase(to)) {
                System.out.println(from + "sent a friendship request to " + to);

                FriendshipRequest request = new FriendshipRequest(from, to);
                request.setRequestId(getHighestFriendshipRequestId() + 1);
                requestRepository.save(request);
            }
        }
        // System.out.println(from + " " + to);
    }

    public void createFriendship(String from, String to) {
        Friendship friendship = new Friendship(from, to);
        friendship.setFriendshipId(getHighestFriendshipId() + 1);
        friendshipRepository.save(friendship);
    }

    // check if request already exists
    public boolean duplicateRequest(String from, String to) {

        List<FriendshipRequest> allRequests = requestRepository.findAll();

        for (FriendshipRequest friendshipRequest : allRequests) {
            if (friendshipRequest.getFrom().equalsIgnoreCase(from) && friendshipRequest.getTo().equalsIgnoreCase(to)) {
                return true;
            }
        }

        return false;
    }

    // Check if request is actually a accept of a request
    public boolean isAcceptance(String from, String to) {

        List<FriendshipRequest> allRequests = requestRepository.findAll();

        for (FriendshipRequest friendshipRequest : allRequests) {
            if (friendshipRequest.getFrom().equalsIgnoreCase(to) && friendshipRequest.getTo().equalsIgnoreCase(from)) {
                return true;
            }
        }

        return false;
    }

    public int getHighestFriendshipId() {

        int max = 1;

        List<Friendship> allUser = friendshipRepository.findAll();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getFriendshipId() >= 1) {
                max = allUser.get(i).getFriendshipId();
            }

        }

        return max;
    }

    public int getHighestFriendshipRequestId() {

        int max = 1;

        List<FriendshipRequest> allUser = requestRepository.findAll();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getRequestId() >= 1) {
                max = allUser.get(i).getRequestId();
            }

        }

        return max;
    }

    public List<Friendship> getFriendsbyUser(String username) {
        List<Friendship> allFriendships = friendshipRepository.findAll();
        List<Friendship> allFriendsOfUser = new ArrayList<>();
        for (Friendship friendship : allFriendships) {
            System.out.println(friendship.getUsername2() + " Hä? " + username);
            if (friendship.getUsername1().equalsIgnoreCase(username)
                    || friendship.getUsername2().equalsIgnoreCase(username)) {
                allFriendsOfUser.add(friendship);
            }
        }

        return allFriendsOfUser;
    }
}
