package net.siriussoft.aleservice.common.controller;

import net.siriussoft.aleservice.security.model.CustomUserDetails;
import net.siriussoft.aleservice.user.model.User;
import net.siriussoft.aleservice.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/login222", method = RequestMethod.GET)
    public String getLoginPage(){
        System.out.println("login GET");
        return "login";
    }

    @RequestMapping(value="/login/process2222", method = RequestMethod.POST)
    public String loginProcess(User user){
        System.out.println(user.toString());
        if (user != null && StringUtils.isNotEmpty(user.getUserId())&&StringUtils.isNotEmpty(user.getPassword())) {
            System.out.println("login POST");
        }

        return "TTTTTTTTTTT";
    }
}
