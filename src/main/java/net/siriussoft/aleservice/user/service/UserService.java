package net.siriussoft.aleservice.user.service;

import net.siriussoft.aleservice.common.exception.NoQueryResultException;
import net.siriussoft.aleservice.common.util.DateUtil;
import net.siriussoft.aleservice.common.util.EncAlgorithm;
import net.siriussoft.aleservice.common.util.KeyGenerator;
import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.SignupType;
import net.siriussoft.aleservice.user.model.User;
import net.siriussoft.aleservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByUserId(String userId) throws NoQueryResultException {
        User user = userRepository.findByUserId("super");
        return user;
    }

    public User findUserBySessionKey(SessionKey sessionKey) {
        User user = userRepository.findBySessionKey(sessionKey);
        return user;
    }

    public void createGeneral(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setSessionKey(new SessionKey(KeyGenerator.uuidHash(EncAlgorithm.SHA256)));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setSignupType(SignupType.general);
        user.setCreatedDate(DateUtil.getCurrentDate());
        user.setModifiedDate(DateUtil.getCurrentDate());
        this.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void remove(User user) {
        userRepository.remove(user);
    }
}
