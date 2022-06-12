package dech.board.Friendship;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dech.board.user.User;
import dech.board.user.UserService;

@Service
public class FriendshipService {
    @Autowired
    UserService userService;
    @Autowired
    FriendshipRequestRepository requestRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    public void createFriendshipRequest(String from, String to) {
        if (from == null && to == null) {
            System.out.println(from + " or " + to + " is null");
        } else if (duplicateRequest(from, to)) {
            System.out.println(from + " already sent a Friendship Request to " + to);
        } else if (isAcceptance(from, to)) {
            System.out.println(from + " accepted the friendship request from " + to);
            createFriendship(from, to);
            deleteRequest(from, to);
        } else if (areFriends(from, to)) {
            System.out.println(from + " and " + to + " are already friends");
        } else if (!from.equalsIgnoreCase(to)) {
            System.out.println(from + " sent a friendship request to " + to);

            FriendshipRequest request = new FriendshipRequest(from, to);
            request.setRequestId(getHighestFriendshipRequestId() + 1);
            requestRepository.save(request);
        }

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
    // public ArrayList<User> friendsObject(String username) {
    // List<Friendship> nameList= getFriendsbyUser(username);
    // ArrayList<User> objectFriends= new ArrayList<> ();
    // for(int i=0; i<nameList.size(); i++){
    // System.out.println(nameList.get(i).getUsername1()+" username 2
    // :"+nameList.get(i).getUsername2());

    // if(!nameList.get(i).getUsername1().equalsIgnoreCase(username)){
    // objectFriends.add(userService.getUserByUsername(nameList.get(i).getUsername1()))
    // ;}
    // else if(!nameList.get(i).getUsername2().equalsIgnoreCase(username)){
    // objectFriends.add(userService.getUserByUsername(nameList.get(i).getUsername2()))
    // ;
    // }}
    // return objectFriends;
    // }
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

    // After Friendship is created the request can be deleted
    public void deleteRequest(String from, String to) {
        if (getIdOfRequest(from, to) != -100) {

            System.out.println("Delete Request from: " + from + " to: " + to);
            requestRepository.deleteById(getIdOfRequest(from, to));
        }

    }

    public int getIdOfRequest(String from, String to) {

        List<FriendshipRequest> allRequests = requestRepository.findAll();

        for (FriendshipRequest friendshipRequest : allRequests) {
            if (friendshipRequest.getFrom().equalsIgnoreCase(from) && friendshipRequest.getTo().equalsIgnoreCase(to) ||
                    friendshipRequest.getFrom().equalsIgnoreCase(to)
                            && friendshipRequest.getTo().equalsIgnoreCase(from)) {

                return friendshipRequest.getRequestId();

            }
        }

        System.out.println(from + " did not send request to " + to);
        return -100;
    }

    public int getIdOfFriendship(String user1, String user2) {

        List<Friendship> allFriendships = friendshipRepository.findAll();

        for (Friendship friendshipRequest : allFriendships) {
            if (friendshipRequest.getUsername1().equalsIgnoreCase(user1)
                    && friendshipRequest.getUsername2().equalsIgnoreCase(user2) ||
                    friendshipRequest.getUsername1().equalsIgnoreCase(user2)
                            && friendshipRequest.getUsername2().equalsIgnoreCase(user1)) {

                return friendshipRequest.getFriendshipId();

            }
        }

        System.out.println(user1 + " is not a friend of " + user2);
        return -100;
    }

    public void deleteFriendship(String user1, String user2) {
        if (getIdOfFriendship(user1, user2) != -100) {
            System.out.println("Delete Friendship beetween: " + user1 + "-X-" + user2);
            friendshipRepository.deleteById(getIdOfFriendship(user1, user2));
        }
    }

    public boolean areFriends(String user1, String user2) {

        List<Friendship> allFriends = friendshipRepository.findAll();

        for (Friendship friendship : allFriends) {
            if (user1.equals(friendship.getUsername1()) && user2.equals(friendship.getUsername2()) ||
                    user1.equals(friendship.getUsername2()) && user2.equals(friendship.getUsername1())) {
                return true;
            }
        }

        return false;
    }

}
