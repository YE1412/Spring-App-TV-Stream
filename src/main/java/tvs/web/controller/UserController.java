package tvs.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tvs.daohibernate.model.Movie;
import tvs.daohibernate.model.User;
import tvs.interf.ILogger;
import tvs.interf.IUser;
import tvs.web.validators.UserRegisterFormValidator;

@Controller()
@RequestMapping("/user")
public class UserController {
	@Autowired
    IUser manager;
	@Autowired
	@Qualifier("run")
	ILogger logger;
	@Autowired
	UserRegisterFormValidator validator;
	
	@ModelAttribute("user")
    public User newUser(@RequestParam(value = "nom", required = false) String name) {
        if (name == null || name.equals("Anonymous")) {
        	User u = new User();
    	    u.setNom("Anonymous");
    	    u.setPass("");
//    	    logger.log("new movie = " + m);
    	    return u;
        }
		return manager.findUser(name);
//		return new User();
    }

    @RequestMapping(value = "/show")
    public String show() {
//        logger.log("show user " + user);
        return "user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
//        logger.log("login user " + user);
//        user.setName("It's me");
        return "userLoginForm";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
//        logger.log("login user " + user);
//        user.setName("It's me");
        return "userRegisterForm";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user,
    		BindingResult result) {
    	validator.validate(user, result);
    	if (result.hasErrors()) {
    		return "userRegisterForm";
    	}
    	user.setBirthDay(new Date());
    	manager.addUser(user);
    	return "user";
    }
    @RequestMapping(value = "/logout")
    public String logout() {
//        logger.log("logout user " + user);
//        user.setName("Anonymous");
        return "user";
    }
}
