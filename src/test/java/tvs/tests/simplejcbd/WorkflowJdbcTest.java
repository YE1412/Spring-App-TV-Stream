package tvs.tests.simplejcbd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvs.simplejdbc.SimpleJdbcConfig;
import tvs.simplejdbc.SimpleSpringJdbc;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleJdbcConfig.class)
public class WorkflowJdbcTest {
	@Autowired
    SimpleSpringJdbc dao;
	
	@Before
    public void beforeEachTest() {
        System.err.println("====================");
    }

    @After
    public void afterEachTest() {
    }
    
	@Test
    public void testWorks() throws Exception {
        long debut = System.currentTimeMillis();
        // exécution des threads
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 1; (i < 5); i++) {
            executor.execute(dao::longWork);
        }

        // attente de la fin des threads
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.HOURS);

        // calcul du temps de réponse
        long fin = System.currentTimeMillis();
        System.out.println("duree = " + (fin - debut) + "ms");
    }
}
