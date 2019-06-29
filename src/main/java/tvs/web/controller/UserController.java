package tvs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tvs.daohibernate.model.User;
import tvs.interf.ILogger;
import tvs.interf.IUser;

@Controller()
@RequestMapping("/user")
public class UserController {
	@Autowired
    IUser manager;
	@Autowired
	@Qualifier("run")
	ILogger logger;
	@ModelAttribute("user")
    public User newUser() {
        return manager.newUser();
//		return new User();
    }

    @RequestMapping(value = "/show")
    public String show() {
//        logger.log("show user " + user);
        return "user";
    }

    @RequestMapping(value = "/login")
    public String login() {
//        logger.log("login user " + user);
//        user.setName("It's me");
        return "user";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
//        logger.log("logout user " + user);
//        user.setName("Anonymous");
        return "user";
    }
}
