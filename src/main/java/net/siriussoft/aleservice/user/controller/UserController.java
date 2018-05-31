package net.siriussoft.aleservice.user.controller;

import net.siriussoft.aleservice.common.util.DateUtil;
import net.siriussoft.aleservice.common.util.EncAlgorithm;
import net.siriussoft.aleservice.common.util.KeyGenerator;
import net.siriussoft.aleservice.user.model.SessionKey;
import net.siriussoft.aleservice.user.model.SignupType;
import net.siriussoft.aleservice.user.model.User;
import net.siriussoft.aleservice.user.repository.UserRepository;
import net.siriussoft.aleservice.user.repository.UserRepositoryTest;
import net.siriussoft.aleservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/user")
    public String login() {

        //this.insertTest();

        return "hello world!";
    }

    @RequestMapping(value="/user/saveGeneral", method = RequestMethod.POST)
    public String createGeneral(User user) {
        userService.createGeneral(user);
        return "success";
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.GET)
    public String signUpPage(Locale locale, Model model) {
        System.out.println("signup");
        return "signUp";
    }

    public void insertTest() {
        for(int idx = 0; idx < 100; idx++) {
            SessionKey sessionKey = new SessionKey("tmp" + idx);
            String userId = "userId" + idx;
            String name = "name " + idx;
            String password = "password" + idx;
            SignupType signupType = SignupType.general;
            User user = new User(sessionKey,userId,name,password,signupType );
            System.out.println("  "+idx+" : " + userId);
            userService.createGeneral(user);
        }
    }
}
