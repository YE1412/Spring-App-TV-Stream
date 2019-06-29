package tvs.tests.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tvs.daohibernate.Dao;
import tvs.daohibernate.model.Movie;
public class DaoTest {
	static Dao dao;
	@BeforeClass
    public static void before() {
        dao = new Dao();
        dao.init();
    }

    @AfterClass
    public static void after() {
    	dao.close();
    }
    
    @Before
    public void tearUp() {
    	System.err.println("====================");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateMovie() {
    	System.err.println("+++ create and find movie thru JPA test");
    	Movie m = new Movie();
//    	m.setId(15);
    	m.setName("Home");
    	dao.addMovie(m);
    	assertEquals("Home", dao.findMoviesByName("Home").get(0).getName());
    }
//    @Test
//	  public void testFindMovie() {
//	    	System.err.println("+++ find movie thru JPA test");
//	    	assertEquals("Hello", dao.findMovie(100).getName());
//	  }
//    @Test
//    public void testCRUDMovie() {
//    	System.err.println("+++ create/update/delete movie thru JPA test");
//    	Movie m = new Movie();
//    	m.setId(16);
//    	m.setName("Home");
//    	//dao.addMovie(m);
//    	//assertEquals("Home", dao.findMovie(16).getName());
//    	//m.setName("Home_replaced");
//    	//dao.updateMovie(m);
//        //assertEquals("Home_replaced", dao.findMovie(16).getName());
//        //dao.removeMovie(m.getId());
//        //assertEquals(null, dao.findMovie(16));
//        assertEquals(3, dao.findMoviesByName("H").size());
//        assertEquals("Hello", dao.namedQueriesFindAllFilm().get(0).getName());
//    }
//    @Test
//    public void testMovieNameBean() {
//    	System.err.println("+++ moviename Bean thru JPA test");
//    	assertEquals(1, dao.namedQueriesGetFilmNames().size());
//    }
//    @Test
//    public void testMovieCertifs() {
//    	System.err.println("+++ moviecertifs thru JPA test");
//    	Movie m = new Movie();
//    	Certification c = new Certification(1,"AWARD", 1991);
//    	Set<Certification> certifs = new HashSet<>();
//    	m.setId(14);
//    	m.setName("Home");
//    	c.setOwner(m);
//    	certifs.add(c);
//    	m.setCertifs(certifs);
//    	dao.addMovie(m);
//    	assertEquals(1, dao.findMovie(m.getId()).getCertifs().size());
//    }
//    @Test
//    public void testFindMovieByCertifs() {
//    	System.err.println("+++ findmoviecertifs thru JPA test");
//    	List<Movie> l = dao.findMovieByCertification("AWARD");
//    	assertEquals("Home", l.get(0).getName());
//    }
//    @Test(expected = SQLException.class)
//    public void testErrors() throws SQLException {
//    	Movie m = new Movie();
//    	m.setId(17);
//    	m.setName("Home");
//    	dao.addMovie(m);
//    }
}
