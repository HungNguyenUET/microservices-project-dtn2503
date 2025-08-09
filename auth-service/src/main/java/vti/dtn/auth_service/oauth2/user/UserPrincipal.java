package vti.dtn.auth_service.oauth2.user;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import vti.dtn.auth_service.entity.UserEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static vti.dtn.auth_service.oauth2.common.OAuth2Constant.ROLE_USER;

@Setter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static UserPrincipal create(UserEntity user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER));
        return new UserPrincipal(user.getUsername(), user.getPassword(), authorities, Map.of());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return username;
    }
}
