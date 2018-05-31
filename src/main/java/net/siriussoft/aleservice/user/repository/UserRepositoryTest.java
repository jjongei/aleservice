package net.siriussoft.aleservice.user.repository;

import net.siriussoft.aleservice.common.util.DateUtil;
import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.SignupType;
import net.siriussoft.aleservice.user.model.User;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void insertTest() {
        for(int idx = 0; idx < 100; idx++) {
            SessionKey sessionKey = new SessionKey("tmp" + idx);
            String userId = "userId" + idx;
            String name = "name " + idx;
            String password = "password" + idx;
            SignupType signupType = SignupType.general;
            User user = new User(sessionKey,userId,name,password,signupType );
            System.out.println("  "+idx+" : " + userId);
            userRepository.save(user);
        }
    }
}
