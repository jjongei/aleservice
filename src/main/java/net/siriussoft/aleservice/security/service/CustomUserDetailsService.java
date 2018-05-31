package net.siriussoft.aleservice.security.service;

import net.siriussoft.aleservice.security.model.CustomUserDetails;
import net.siriussoft.aleservice.user.model.User;
import net.siriussoft.aleservice.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Finding user by login username. (username : {})", username);

        User user = null;
        try {
            user = userService.findUserByUserId(username);
        } catch(Exception e) {
            logger.warn(e.getMessage());
        }

        if (user == null) {
            throw new UsernameNotFoundException("Could not found user name.");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setSessionKey(user.getSessionKey());
        customUserDetails.setUserId(user.getUserId());
        customUserDetails.setName(user.getName());
        customUserDetails.setSignupType(user.getSignupType());
        customUserDetails.setPassword(user.getPassword());

        return customUserDetails;
    }
}
