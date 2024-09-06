package foodify.user.customer.controllers;

import foodify.user.customer.GetUserByLoginRequest;
import foodify.user.customer.UserCustomerResponse;
import foodify.user.customer.UserCustomerServiceGrpc;
import foodify.user.customer.services.UserCustomerService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import foodify.user.customer.domain.orm.UserCustomer;
import org.springframework.security.access.prepost.PreAuthorize;

@GrpcService
public class UserCustomerGrpcController extends UserCustomerServiceGrpc.UserCustomerServiceImplBase {

    private final UserCustomerService userCustomerService;

    public UserCustomerGrpcController(UserCustomerService userCustomerService) {
        this.userCustomerService = userCustomerService;
    }

    @Override
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void getUserByLogin(GetUserByLoginRequest request, StreamObserver<UserCustomerResponse> responseObserver) {
        String login = request.getLogin();
        UserCustomer userCustomer = userCustomerService.getUserByLogin(login);

        UserCustomerResponse response = UserCustomerResponse.newBuilder()
                .setId(userCustomer.id())
                .setName(userCustomer.name())
                .setEmail(userCustomer.email())
                .setLogin(userCustomer.login())
                .setPassword(userCustomer.password())
                .setRole(userCustomer.role().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
