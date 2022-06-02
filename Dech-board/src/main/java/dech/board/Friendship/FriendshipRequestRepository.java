package dech.board.Friendship;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRequestRepository extends MongoRepository<FriendshipRequest, Integer> {

}
