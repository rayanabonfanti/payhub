package foodify.iam.services.implementations;

import foodify.iam.domain.orm.User;
import foodify.iam.domain.orm.UserRole;
import foodify.iam.utils.ConstantsUtil;
import foodify.iam.utils.RedisCache;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthUserCustomerServiceImpl implements UserDetailsService {

    private final RestTemplate restTemplate;
//    private final UserCustomerServiceGrpc.UserCustomerServiceBlockingStub blockingStub;

    public AuthUserCustomerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
//                .usePlaintext()
//                .build();
//        blockingStub = UserCustomerServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        GetUserByLoginRequest request = GetUserByLoginRequest.newBuilder()
//                .setLogin(login)
//                .build();
//
//        UserCustomerResponse userCustomerResponse = blockingStub.getUserByLogin(request);
//
//        return User.fromUserCustomerResponse(userCustomerResponse);
//        User cachedUser = redisCache.getCachedUser(login);
//        if (cachedUser != null) {
//            return cachedUser;
//        }

//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ConstantsUtil.URL_USER_CUSTOMER +
//                        ConstantsUtil.PATH_USER_CUSTOMER)
//                .queryParam("login", login);
//
//        ResponseEntity<User> response = restTemplate.getForEntity(uriBuilder.toUriString(), User.class);
//        User user = response.getBody();
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with login: " + login);
//        }
//
////        redisCache.cacheUser(login, user);
//        return user;
        return new User(
                "326cc6e3-3224-416c-83e1-d58fb15ca057", // id
                "Fulano Admin", // name
                "fulano-admin@gmail.com", // email
                "fulano-admin", // login
                "$2a$10$uB7hjI.lBrV6B8qRJ8p9nuu2PLK/W3weU69idVGfXHq11C8LOnDJy", // password
                UserRole.ADMIN // role
        );
    }

}