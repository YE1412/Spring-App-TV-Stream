/**
 * 
 */
package tvs.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tvs.imp.BeanFileLogger;
import tvs.imp.FileLogger;
import tvs.imp.StderrLogger;
import tvs.interf.ILogger;

/**
 * @author PROD
 *
 */
public class TestLoggerServices {
	@Before
    public void beforeEachTest() {
        System.err.println("====================");
    }

    @After
    public void afterEachTest() {
    }

    // use a logger
    void use(ILogger logger) {
    	logger.log("Voila le résultat = terminé !");
    }

    // Test StderrLogger
    @Test
    public void testStderrLogger() {
        // create the service
        StderrLogger logger = new StderrLogger();
        // start the service
        logger.start();
        // use the service
        use(logger);
        // stop the service
        logger.stop();
    }
    // Test FileLogger
    @Test
    public void testFileLogger() {
        FileLogger logger = new FileLogger();
        logger.setFileName("tvs.log");
        logger.start();
        use(logger);
        logger.stop();
    }
    // Test BeanFileLogger
    @Test
    public void testBeanFileLogger() {
        // create the service
        BeanFileLogger logger = new BeanFileLogger();
        // set parameter
        logger.setFileName("tvsbean.log");
        // start
        logger.start();
        // use
        use(logger);
        // stop
        logger.stop();
    }
}
