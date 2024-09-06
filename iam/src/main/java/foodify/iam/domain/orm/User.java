package foodify.iam.domain.orm;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record User(String id, String name, String email, String login, String password, UserRole role) implements UserDetails {

//    public static User fromUserCustomerResponse(UserCustomerResponse response) {
//        UserRole role = UserRole.valueOf(response.getRole().toUpperCase());
//        return new User(response.getId(), response.getName(), response.getEmail(), response.getLogin(), response.getPassword(), role);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role() == UserRole.ADMIN) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
