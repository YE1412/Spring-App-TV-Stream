package tvs.imp;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tvs.interf.ILogger;
@Service("stderrLogger")
@Qualifier("run")
public class StderrLogger implements ILogger {
	private static final Logger logger = Logger.getLogger(StderrLogger.class);
	
	public StderrLogger() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Start Service
	@PostConstruct
	 public void start() {
	        System.err.println("Start " + this);
	 }
	// stop service
    @PreDestroy
    public void stop() {
        System.err.println("Stop " + this);
    }

	public void log(String msg) {
		// TODO Auto-generated method stub
		System.err.printf("%tF %1$tR | %s\n", new Date(), msg);
		logger.debug("Fait !");
	}

}
