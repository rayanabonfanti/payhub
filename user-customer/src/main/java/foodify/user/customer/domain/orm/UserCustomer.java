package foodify.user.customer.domain.orm;

import foodify.user.customer.domain.enumaration.UserCustomerRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user-customer")
public record UserCustomer(
        @Id String id,
        String name,
        String email,
        String login,
        String password,
        UserCustomerRole role
) {}
