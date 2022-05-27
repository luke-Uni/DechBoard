package dech.board.confirmation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends MongoRepository<Confirmation, Integer> {
    
}
