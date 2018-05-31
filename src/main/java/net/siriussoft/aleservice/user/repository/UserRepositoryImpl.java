package net.siriussoft.aleservice.user.repository;

import com.querydsl.jpa.JPQLQuery;
import net.siriussoft.aleservice.user.model.QUser;
import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    //@Override
    public User findBySessionKey(SessionKey sessionKey) {
        User result = null;
        QUser qUser = QUser.user;
        JPQLQuery query = this.from(qUser);
        if (!ObjectUtils.isEmpty(sessionKey)) {
            query.where(qUser.sessionKey.eq(sessionKey));
            result = (User)query.fetchOne();
        }
        return result;
    }

    ///= @Override
    public User findByUserId(String userId) {
        QUser qUser = QUser.user;

        return this.from(qUser).where(qUser.userId.eq(userId)).fetchOne();
    }

    //@Override
    public void save(User user) {
        this.getEntityManager().persist(user);
    }

    //@Override
    public void remove(User user) {
        this.getEntityManager().remove(user);
    }
}
