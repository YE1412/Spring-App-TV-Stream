package tvs.daohibernate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Bean;
import tvs.daohibernate.model.Movie;
import tvs.daohibernate.model.MovieName;
import tvs.daohibernate.model.User;
public class Dao {
	private EntityManagerFactory factory = null;
	public void init() {
		String url = null, user = null, password = null;
		try (InputStream inputStream = getClass()
				.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(inputStream);
//            System.out.println(prop.getProperty("jdbc.url"));
//            System.out.println(prop.getProperty("jdbc.user"));
//            System.out.println(prop.getProperty("jdbc.password"));
            // get the property value and print it out
            url = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.user");
            password = prop.getProperty("jdbc.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		try {
			factory = Persistence.createEntityManagerFactory("tvstreamdb", hibernateProperties(url, user, password));
		}catch( Exception e) {
			e.printStackTrace();
		}
	}
	@Bean
	public Map<String, String> hibernateProperties(String url,
									        String user,
									        String password){
	    final Map<String, String> properties = new HashMap<String, String>();
	    properties.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
	    properties.put("javax.persistence.jdbc.url", url);

	    properties.put("javax.persistence.jdbc.user", user);
	    properties.put("javax.persistence.jdbc.password", password);
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.hbm2ddl.auto", "create");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
	    return properties;
	}
   public void close() {
      if (factory != null) {
         factory.close();
      }
   }
   
	// Créer un EM et ouvrir une transaction
	private EntityManager newEntityManager() throws Exception{
	   EntityManager em = factory.createEntityManager();
	   //factory.addNamedQuery(query.getIdentifier(), em.createQuery(query.getQuery()));
	   em.getTransaction().begin();
	   return (em);
	}
	
	// Fermer un EM et défaire la transaction si nécessaire
	private void closeEntityManager(EntityManager em) {
	   if (em != null) {
	      if (em.isOpen()) {
	         EntityTransaction t = em.getTransaction();
	         if (t.isActive()) {
	            try {
	               t.rollback();
	            } catch (PersistenceException e) {
	            }
	         }
	         em.close();
	      }
	   }
}
   
   public void addMovie(Movie p) {
	   EntityManager em = null;
	   try {
	      em = newEntityManager();
	      // utilisation de l'EntityManager
	      em.persist(p);
	      em.getTransaction().commit();
	      System.err.println("addMovie witdh id=" + p.getId());
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	      if (em != null) {
	    	  closeEntityManager(em);
	      }
	   }
	}


	public Movie findMovie(int id) {
	    EntityManager em = null;
	    Movie p = null;
	    try {
	    	em = newEntityManager();
	        // utilisation de l'EntityManager
	        p = em.find(Movie.class, id);
	        p.getCertifs().size();
	        return p;
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return p;
		} finally {
	      if (em != null) {
	    	  closeEntityManager(em);
	      }
	    }
	}
	
	public void updateMovie(Movie m) {
		EntityManager em = null;
	    Movie p = null;
	    try {
	    	em = newEntityManager();
	        // utilisation de l'EntityManager
	        p = em.find(Movie.class, m.getId());
	        p.setName(m.getName());
	        em.flush();
	        em.getTransaction().commit();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	      if (em != null) {
	    	  closeEntityManager(em);
	      }
	    }
		
	}

	public void removeMovie(int id){
		EntityManager em = null;
	    Movie p = null;
	    try {
	    	em = newEntityManager();
	        // utilisation de l'EntityManager
	    	p = em.find(Movie.class, id);
	    	em.remove(p);
	    	em.getTransaction().commit();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	      if (em != null) {
	    	  closeEntityManager(em);
	      }
	    }
	}
	
	public List<Movie> findAllMovies() {
	    EntityManager em = null;
	    List<Movie> movies = null;
	    try {
	        em = newEntityManager();
	        String query = "SELECT m FROM movie m";
	        TypedQuery<Movie> q = em.createQuery(query, Movie.class);
	        movies = q.getResultList();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return movies;
	}
	public List<Movie> findMoviesByName(String pattern) {
		EntityManager em = null;
	    List<Movie> movies = null;
	    try {
	        em = newEntityManager();
	        String query = "SELECT m FROM movie m WHERE m.name LIKE :name ";
	        //em.set
	        TypedQuery<Movie> q = em.createQuery(query, Movie.class);
	        movies = q.setParameter("name", pattern+"%").getResultList();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return movies;
	}
	public List<Movie> namedQueriesFindAllFilm() {
		EntityManager em = null;
	    List<Movie> movies = null;
	    try {
	        em = newEntityManager();
	        TypedQuery<Movie> q = em.createNamedQuery("findAllMoviesOrderAsc",Movie.class);
	        movies = q.getResultList();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return movies;
	}
	public List<MovieName> namedQueriesGetFilmNames() {
		EntityManager em = null;
	    List<MovieName> movienames = null;
	    try {
	        em = newEntityManager();
	        TypedQuery<MovieName> q = em.createNamedQuery("findAllMoviesNames", MovieName.class);
	        movienames = q.getResultList();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return movienames;
	}
	public List<Movie> findMovieByCertification(String cert) {
		EntityManager em = null;
	    List<Movie> movies = null;
	    try {
	        em = newEntityManager();
	        TypedQuery<Movie> q = em.createNamedQuery("findMovieByCertif", Movie.class);
	        movies = q.setParameter("name", cert+"%").getResultList();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return movies;
	}
	
	public void addUser(User u) {
		EntityManager em = null;
		   try {
		      em = newEntityManager();
		      // utilisation de l'EntityManager
		      em.persist(u);
		      em.getTransaction().commit();
		      System.err.println("addUser witdh neme=" + u.getNom());
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		      if (em != null) {
		    	  closeEntityManager(em);
		      }
		   }
	}
	public User createUser() {
		 return User.of("default", "motdepasse", new Date());
	}
	public User findUser(String name, String pass) {
		EntityManager em = null;
	    User usr = null;
	    try {
	        em = newEntityManager();
	        String query = "SELECT u FROM user u WHERE u.nom=:name AND u.pass=:pass ";
	        //em.set
	        //use criteria later
	        TypedQuery<User> q = em.createQuery(query, User.class);
	        usr = q.setParameter("name", name).setParameter("pass", pass).getSingleResult();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        closeEntityManager(em);
	    }
		return usr;
	}
}
