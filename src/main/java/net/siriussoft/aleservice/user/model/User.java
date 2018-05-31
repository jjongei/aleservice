package net.siriussoft.aleservice.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.siriussoft.aleservice.common.util.DateUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@Access(AccessType.FIELD)
@Table(name="user")
public class User {
    @EmbeddedId
    private SessionKey sessionKey;

    @Column(name = "user_id", columnDefinition = "nvarchar(128)", nullable = false)
    private String userId;

    @Column(name="name", columnDefinition = "nvarchar(128)")
    private String name;

    @Column(name="password", columnDefinition = "nvarchar(128)")
    private String password;

    @Column(name = "signup_type", columnDefinition = "nvarchar(32)")
    @Enumerated(EnumType.STRING)
    private SignupType signupType;

    @Column(name = "logining_flag", columnDefinition = "char(1)")
    private Boolean loginingFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", columnDefinition="datetime", nullable = false )
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", columnDefinition="datetime", nullable = false )
    private Date modifiedDate;

    public User(SessionKey sessionKey, String userId, String name, String password, SignupType signupType) {
        this.setSessionKey(sessionKey);
        this.setUserId(userId);
        this.setName(name);
        this.setPassword(password);
        this.setSignupType(signupType);
    }

    public User(SessionKey sessionKey, String userId, String name, SignupType signupType) {

        Date currentTime = DateUtil.getCurrentDate();

        this.setSessionKey(sessionKey);
        this.setUserId(userId);
        this.setName(name);
        this.setSignupType(signupType);
        this.setCreatedDate(currentTime);
        this.setModifiedDate(currentTime);
    }

    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        if (sessionKey == null) throw new IllegalArgumentException("Session key");
        this.sessionKey = sessionKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId == null) throw new IllegalArgumentException("user Id");
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name");
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("password");
        this.password = password;
    }

    public SignupType getSignupType() {
        return signupType;
    }

    public void setSignupType(SignupType signupType) {
        if (signupType == null) throw new IllegalArgumentException("signup type");
        this.signupType = signupType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    private void changeLoginInfo(boolean loginInfo) {
        this.loginingFlag = loginingFlag;
        this.setModifiedDate(DateUtil.getCurrentDate());
    }

    public boolean signupByGeneral(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!this.signupType.equals(SignupType.general) || !encoder.matches(password, this.password)) {
            throw new LoginFailedException();
        }
        this.changeLoginInfo(true);
        return true;
    }

    public boolean isAuthByFacebookOrGoogle() {
        if(this.signupType.equals(SignupType.general)) {
            throw new LoginFailedException();
        }
        this.changeLoginInfo(true);
        return true;
    }

    public void logout() {
        this.changeLoginInfo(false);
    }

    public boolean checkLogin() {
        return this.loginingFlag;
    }
}
