package net.siriussoft.aleservice.security.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.SignupType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private SessionKey sessionKey;
    private String userId;
    private String name;
    private String password;
    private SignupType signupType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
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
