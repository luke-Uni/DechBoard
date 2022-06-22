package dech.board.Contacts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRequestRepository extends MongoRepository<ContactRequest, Integer> {

}
