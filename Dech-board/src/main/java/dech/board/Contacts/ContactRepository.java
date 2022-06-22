package dech.board.Contacts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contacts, Integer> {

}
