package dech.board.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//MongoDb integration
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

}
