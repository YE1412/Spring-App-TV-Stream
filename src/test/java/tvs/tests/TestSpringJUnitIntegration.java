package tvs.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvs.interf.ILogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config.xml")
public class TestSpringJUnitIntegration {
	@Autowired
	@Qualifier("run")
	public ILogger logger;
	
//	@Autowired
//	public IFileBrowser iFile;
	
	 @Before
    public void beforeEachTest() {
        System.err.println("====================");
    }

    void use(ILogger logger) {
    	logger.log("Voila le résultat");
    }

//    void use(IFileBrowser file) {
//    }
 // Test FileLogger
    @Test
    public void testBeanFileLogger() {
    	System.err.println("+++ FileLogger");
//        BeanFileLogger logger = new BeanFileLogger();
//        logger.setFileName("/tmp/tvs.log");
//        logger.start();
        use(logger);
//        logger.stop();
    }
//    @Test
//    public void testStderrLogger() {
//        System.err.println("+++ StderrLogger");
//        use(logger);
//    }

//    @Test
//    public void testCalculatorWithLogger() {
//        use(iFile);
//    }
}
