package foodify.establishment.domain.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "establishments")
public class Establishment {
    @Id
    private String id;
    private String name;
    private String address;
    private List<String> menuItems;
}

