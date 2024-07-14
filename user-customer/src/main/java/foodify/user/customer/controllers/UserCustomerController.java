package foodify.user.customer.controllers;

import foodify.user.customer.domain.dto.RegisterUserCustomerDto;
import foodify.user.customer.domain.orm.UserCustomer;
import foodify.user.customer.services.UserCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodify/v1/user-customer")
public class UserCustomerController {

    private final UserCustomerService userCustomerService;

    public UserCustomerController(UserCustomerService userCustomerService) {
        this.userCustomerService = userCustomerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserCustomerDto registerUserCustomerDto) {
        userCustomerService.registerUser(registerUserCustomerDto);
        return ResponseEntity.ok("User Customer saved successfully!");
    }

    @GetMapping("/get-login")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserCustomer> getLogin(@RequestParam String login) {
        UserCustomer user = userCustomerService.getUserByLogin(login);
        return ResponseEntity.ok(user);
    }

}
