package tvs.web.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tvs.interf.ILogger;
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class SessionCounterController {
	@Autowired
	@Qualifier("run")
	public ILogger logger;
	
	@RequestMapping(value = "/counterIncr/", method = RequestMethod.GET)
	public ModelAndView increaseSessionCounterRequestParams(@RequestParam("num") int val,
										@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date d) {
		logTrace("Session Increased..." + val);
        Date now = new Date();
//        now.set(Calendar.DAY_OF_MONTH, Calendar.get(Calendar.DAY_OF_MONTH) + val);
//        now.get+ val;
        return new ModelAndView("hello", "now", now.toString());
    }
	@RequestMapping(value = "/counterIncr/{param}", method = RequestMethod.GET)
	public ModelAndView increaseSessionCounterPathParams(@PathVariable("param") int val,
										@PathVariable("version") String v,
										@PathVariable("extension") String e,
										@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date d) {
		logTrace("Session Increased..." + val);
        String now = (new Date()).toString();
        return new ModelAndView("hello", "now", now + val);
    }
	@RequestMapping(value = "/counterIncr/{param:[0-9]+}-{version:[0-9]+[.0-9]*}{extension:.[aA-zA]+}", method = RequestMethod.GET)
	public ModelAndView increaseSessionCounterWithPathParamsMask(@PathVariable("param") int val,
										@PathVariable("version") String v,
										@PathVariable("extension") String e,
										@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date d) {
		logTrace("Session Path Params Increased... param - " + val+" version - "+v+" extension - "+e);
        String now = (new Date()).toString();
        return new ModelAndView("hello", "now", now + val);
    }
	//matching /counterIncr/matrix/42;id=15;nom=thomas
	@RequestMapping(value = "/counterIncr/matrix/{params}", method = RequestMethod.GET)
	public ModelAndView matrixParamsDef(@PathVariable String params, @MatrixVariable Map<String, String> matrix) {
        String now = (new Date()).toString();
        logTrace("Matrix variable Path Params... matrix - " + matrix);
        logTrace("Matrix variable Path Params... params - " + params);
        return new ModelAndView("hello", "now", now + matrix);
    }
	
	private void logTrace(String msg) {
		logger.log(msg);
	}
}
