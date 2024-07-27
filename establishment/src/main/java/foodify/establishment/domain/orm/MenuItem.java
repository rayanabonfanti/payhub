package foodify.establishment.domain.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "menuItems")
public class MenuItem {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String establishmentId;
}
