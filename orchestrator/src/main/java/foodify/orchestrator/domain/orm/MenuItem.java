package foodify.orchestrator.domain.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuItem {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String establishmentId;
}

