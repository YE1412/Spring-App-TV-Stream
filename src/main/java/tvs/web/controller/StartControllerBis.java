package tvs.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tvs.interf.ILogger;

@Controller
@RequestMapping("/tests/")
public class StartControllerBis {
	@Autowired
	@Qualifier("integ")
	public ILogger logger;
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView sayHello() {
		logTrace("Starting JSP page...");
        String now = (new Date()).toString();
        return new ModelAndView("hello", "now", now);
    }
	
	private void logTrace(String msg) {
		logger.log(msg);
	}
}
