//package tvs.tests.simplejcbd;
//
//import static org.junit.Assert.assertEquals;
//
//import java.sql.SQLException;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import tvs.simplejdbc.SimpleJdbcConfig;
//import tvs.simplejdbc.SimpleSpringJdbc;
//import tvs.simplejdbc.model.Movie;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SimpleJdbcConfig.class)
//public class SimpleJcbdSpringTest {
//	@Autowired
//    SimpleSpringJdbc dao;
//	
//	@Before
//    public void beforeEachTest() {
//        System.err.println("====================");
//    }
//
//    @After
//    public void afterEachTest() {
//    }
    
//	@Test
//    public void testSimleJdbcSample() throws SQLException {
//        dao.initSchema();
//        dao.deleteName(100);
//        dao.deleteName(200);
//        dao.addName(100, "Hello");
//        dao.addName(200, "Salut");
//        assertEquals("Hello", dao.findName(100).getName());
//        assertEquals("Salut", dao.findName(200).getName());
//        for (Movie m : dao.findNames()) {
//            System.out.printf("name=%s\n", m.getName());
//        }
//    }

//    @Test(expected = AssertionError.class)
//    public void testErrors() throws SQLException, AssertionError{
//        dao.initSchema();
//        dao.addName(100, "Hello");
//        dao.initSchema();
//        dao.addName(100, "Salut");
//    }
//}
