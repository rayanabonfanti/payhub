package foodify.user.customer.services.implementations;

import foodify.user.customer.domain.dto.RegisterUserCustomerDto;
import foodify.user.customer.domain.orm.UserCustomer;
import foodify.user.customer.repositories.UserCustomerRepository;
import foodify.user.customer.services.UserCustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCustomerServiceImpl implements UserCustomerService {

    private final UserCustomerRepository userCustomerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserCustomerServiceImpl(UserCustomerRepository userCustomerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userCustomerRepository = userCustomerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registerUser(RegisterUserCustomerDto registerUserCustomerDto) {
        UserCustomer newUser = new UserCustomer(
                UUID.randomUUID().toString(),
                registerUserCustomerDto.name(),
                registerUserCustomerDto.email(),
                registerUserCustomerDto.login(),
                bCryptPasswordEncoder.encode(registerUserCustomerDto.password()),
                registerUserCustomerDto.role()
        );

        userCustomerRepository.save(newUser);
    }

    @Override
    public UserCustomer getUserByLogin(String login) {
        return userCustomerRepository.findByLogin(login).orElse(null);
    }
}
