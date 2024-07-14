package foodify.user.customer.domain.dto;

import foodify.user.customer.domain.enumaration.UserCustomerRole;

public record RegisterUserCustomerDto(String name,
                                      String email,
                                      String login,
                                      String password,
                                      UserCustomerRole role) {
}
