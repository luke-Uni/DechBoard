package dech.board.Contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dech.board.user.UserService;

@Service
public class ContactService {

    @Autowired
    UserService userService;

    @Autowired
    ContactRequestRepository requestRepository;

    @Autowired
    ContactRepository contactRepository;

    public void createFriendshipRequest(String from, String to) {
        if (from == null && to == null) {
            System.out.println(from + " or " + to + " is null");
        } else if (duplicateRequest(from, to)) {
            System.out.println(from + " already sent a Contact Request to " + to);
        } else if (isAcceptance(from, to)) {
            System.out.println(from + " accepted the Contact request from " + to);
            createFriendship(from, to);
            deleteRequest(from, to);
        } else if (areFriends(from, to)) {
            System.out.println(from + " and " + to + " are already friends");
        } else if (!from.equalsIgnoreCase(to)) {
            System.out.println(from + " sent a friendship request to " + to);

            ContactRequest request = new ContactRequest(from, to);
            request.setRequestId(getHighestFriendshipRequestId() + 1);
            requestRepository.save(request);
        }

    }

    public void createFriendship(String from, String to) {
        Contacts friendship = new Contacts(from, to);
        friendship.setContactId(getHighestFriendshipId() + 1);
        contactRepository.save(friendship);
    }

    // check if request already exists
    public boolean duplicateRequest(String from, String to) {

        List<ContactRequest> allRequests = requestRepository.findAll();

        for (ContactRequest friendshipRequest : allRequests) {
            if (friendshipRequest.getFrom().equalsIgnoreCase(from) && friendshipRequest.getTo().equalsIgnoreCase(to)) {
                return true;
            }
        }

        return false;
    }
    // Check if request is actually a accept of a request
    public boolean isAcceptance(String from, String to) {

        List<ContactRequest> allRequests = requestRepository.findAll();

        for (ContactRequest friendshipRequest : allRequests) {
            if (friendshipRequest.getFrom().equalsIgnoreCase(to) && friendshipRequest.getTo().equalsIgnoreCase(from)) {
                return true;
            }
        }

        return false;
    }

    public List<ContactRequest> getAllrequestsToUser(String username) {
        List<ContactRequest> allRequests = requestRepository.findAll();

        List<ContactRequest> requestsToUser = new ArrayList<>();

        for (ContactRequest contactRequest : allRequests) {
            if (contactRequest.getTo().equalsIgnoreCase(username)) {
                requestsToUser.add(contactRequest);
            }
        }

        return requestsToUser;

    }

    public int getHighestFriendshipId() {

        int max = 1;

        List<Contacts> allUser = contactRepository.findAll();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getContactId() >= 1) {
                max = allUser.get(i).getContactId();
            }

        }

        return max;
    }

    public int getHighestFriendshipRequestId() {

        int max = 1;

        List<ContactRequest> allUser = requestRepository.findAll();
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getRequestId() >= 1) {
                max = allUser.get(i).getRequestId();
            }

        }

        return max;
    }

    public List<Contacts> getFriendsbyUser(String username) {
        List<Contacts> allFriendships = contactRepository.findAll();
        List<Contacts> allFriendsOfUser = new ArrayList<>();
        for (Contacts friendship : allFriendships) {
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

        List<ContactRequest> allRequests = requestRepository.findAll();

        for (ContactRequest friendshipRequest : allRequests) {
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

        List<Contacts> allFriendships = contactRepository.findAll();

        for (Contacts friendshipRequest : allFriendships) {
            if (friendshipRequest.getUsername1().equalsIgnoreCase(user1)
                    && friendshipRequest.getUsername2().equalsIgnoreCase(user2) ||
                    friendshipRequest.getUsername1().equalsIgnoreCase(user2)
                            && friendshipRequest.getUsername2().equalsIgnoreCase(user1)) {

                return friendshipRequest.getContactId();

            }
        }

        System.out.println(user1 + " is not a friend of " + user2);
        return -100;
    }

    public void deleteFriendship(String user1, String user2) {
        if (getIdOfFriendship(user1, user2) != -100) {
            System.out.println("Delete Friendship beetween: " + user1 + "-X-" + user2);
            contactRepository.deleteById(getIdOfFriendship(user1, user2));
        }
    }

    public boolean areFriends(String user1, String user2) {

        List<Contacts> allFriends = contactRepository.findAll();

        for (Contacts friendship : allFriends) {
            if (user1.equals(friendship.getUsername1()) && user2.equals(friendship.getUsername2()) ||
                    user1.equals(friendship.getUsername2()) && user2.equals(friendship.getUsername1())) {
                return true;
            }
        }

        return false;
    }

}
