package foodify.establishment.repository;

import foodify.establishment.domain.orm.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByEstablishmentId(String establishmentId);
    List<MenuItem> findByIdIn(List<String> ids);
}
