package foodify.user.customer.services;

import foodify.user.customer.domain.dto.RegisterUserCustomerDto;
import foodify.user.customer.domain.orm.UserCustomer;

public interface UserCustomerService {
    void registerUser(RegisterUserCustomerDto registerUserCustomerDto);
    UserCustomer getUserByLogin(String login);
}
