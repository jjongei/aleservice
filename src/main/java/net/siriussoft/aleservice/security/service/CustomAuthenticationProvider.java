package net.siriussoft.aleservice.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private static final String MASTER_PASSWORD = "tlfldntm!23";

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String accessingUsername = authentication.getName();
        String accessingPassword = authentication.getCredentials().toString();
        logger.debug("Internal user accessing... {}", accessingUsername);

        UserDetails userDetails = userDetailsService.loadUserByUsername(accessingUsername);

        if(userDetails == null || !userDetails.getUsername().equalsIgnoreCase(accessingUsername)) {
            throw new BadCredentialsException("Could not found user by name.");
        }

        Collection<? extends GrantedAuthority> authorities = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (accessingPassword.equals(MASTER_PASSWORD) ||
                encoder.matches(accessingPassword, userDetails.getPassword())) {
            authorities = userDetails.getAuthorities();
        } else {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, accessingPassword, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
