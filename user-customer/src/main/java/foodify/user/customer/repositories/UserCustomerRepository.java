package foodify.user.customer.repositories;

import foodify.user.customer.domain.orm.UserCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCustomerRepository extends MongoRepository<UserCustomer, String> {
    Optional<UserCustomer> findByLogin(String login);
}
