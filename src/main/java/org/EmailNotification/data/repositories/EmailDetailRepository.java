package org.EmailNotification.data.repositories;

import org.EmailNotification.data.models.EmailDetail;
import org.EmailNotification.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDetailRepository extends MongoRepository<EmailDetail, String> {
   EmailDetail findByUser(User user);
}
