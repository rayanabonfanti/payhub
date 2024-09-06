package foodify.user.customer.controllers;

import foodify.user.customer.GetUserByLoginRequest;
import foodify.user.customer.UserCustomerResponse;
import foodify.user.customer.UserCustomerServiceGrpc;
import foodify.user.customer.domain.dto.RegisterUserCustomerDto;
import foodify.user.customer.domain.enumaration.UserCustomerRole;
import foodify.user.customer.domain.orm.UserCustomer;
import foodify.user.customer.interceptor.AuthorizationInterceptor;
import foodify.user.customer.services.UserCustomerService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@RestController
@RequestMapping("/foodify/v1/user-customer")
public class UserCustomerController {

    private final UserCustomerService userCustomerService;
    private UserCustomerServiceGrpc.UserCustomerServiceBlockingStub blockingStub;

    public UserCustomerController(UserCustomerService userCustomerService) {
        this.userCustomerService = userCustomerService;

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        // Inicializando o stub sem o interceptor
        this.blockingStub = UserCustomerServiceGrpc.newBlockingStub(channel);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserCustomerDto registerUserCustomerDto) {
        userCustomerService.registerUser(registerUserCustomerDto);
        return ResponseEntity.ok("User Customer saved successfully!");
    }

    @GetMapping("/get-login")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<UserCustomer> getLogin(@RequestParam String login) {
//        UserCustomer user = userCustomerService.getUserByLogin(login);
//        return ResponseEntity.ok(user);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            AuthorizationInterceptor interceptor = new AuthorizationInterceptor(token);
            blockingStub = blockingStub.withInterceptors(interceptor);
        }

        GetUserByLoginRequest grpcRequest = GetUserByLoginRequest.newBuilder()
                .setLogin(login)
                .build();

        UserCustomerResponse userCustomerResponse = blockingStub.getUserByLogin(grpcRequest);

        return ResponseEntity.ok(new UserCustomer(userCustomerResponse.getId(),
                userCustomerResponse.getName(),
                userCustomerResponse.getEmail(),
                userCustomerResponse.getLogin(),
                userCustomerResponse.getPassword(),
                UserCustomerRole.valueOf(userCustomerResponse.getRole())));
    }

    @GetMapping("/test-api-rest")
    public ResponseEntity<String> getTestApiRest() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/test-auth-api-rest")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getTestAuthApiRest() {
        return ResponseEntity.ok("OK");
    }

}
