package net.siriussoft.aleservice.user.repository;

import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.User;

public interface UserRepository {
    public User findBySessionKey(SessionKey sessionKey);

    public User findByUserId(String userId) ;

    public void save(User user);

    public void remove(User user);
}
