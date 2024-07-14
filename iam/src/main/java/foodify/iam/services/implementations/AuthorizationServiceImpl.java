package foodify.iam.services.implementations;

import foodify.iam.domain.dto.AuthenticationDto;
import foodify.iam.domain.dto.LoginResponseDto;
import foodify.iam.domain.orm.User;
import foodify.iam.exceptions.AuthenticationIamException;
import foodify.iam.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthorizationServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public LoginResponseDto authenticateAndGenerateToken(AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                authenticationDto.login(), authenticationDto.password());

        Authentication auth = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = (User) auth.getPrincipal();
        System.out.println("******USER " + user);
        String token = tokenService.generateToken(user);

        return new LoginResponseDto(token);
    }
}
