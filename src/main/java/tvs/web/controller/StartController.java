package tvs.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import tvs.interf.ILogger;
@Service("/hello.htm")
public class StartController implements Controller {
	@Autowired
	@Qualifier("integ")
	public ILogger logger;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
        logTrace("Starting JSP page...");
		return new ModelAndView("hello", "now", new Date().toString());
	}
	private void logTrace(String msg) {
		logger.log(msg);
	}
}
