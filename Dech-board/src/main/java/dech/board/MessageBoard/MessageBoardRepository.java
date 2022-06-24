package dech.board.MessageBoard;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface MessageBoardRepository  extends MongoRepository<MessageBoard, Integer>{
    
}
