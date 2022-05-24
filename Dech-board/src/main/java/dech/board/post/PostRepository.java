package dech.board.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//MongoDb integration
@Repository
public interface PostRepository extends MongoRepository<Post, Integer> {

}
