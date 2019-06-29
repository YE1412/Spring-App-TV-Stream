package tvs.simplejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tvs.simplejdbc.model.Movie;

@Service
public class SimpleSpringJdbc {
	@Autowired
	private JdbcTemplate jt;
	private Connection c;
	public void initSchema() {
		try {
			c = jt.getDataSource().getConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addName(int id, String name) {
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO movie(id, title_short) VALUES (?, ?) ");
			ps.setInt(1, id);
			ps.setString(2, name);
			int rowsInserted = ps.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new movie was inserted successfully!");
			}
//		    while(rs.next()) {
//		        String city = rs.getString("City");
//		        String country = rs.getString("Country");
//		        System.out.printf("The city %s is in %s%n", city, country);
//		    }
		} catch (SQLException ex) {
		    // something has failed and we print a stack trace to analyse the error
		    ex.printStackTrace();
		    // ignore failure closing connection
		    try { c.close(); } catch (SQLException e) { }
		} 
//		finally {
//		    // properly release our connection
//		    DataSourceUtils.releaseConnection(c, jt.getDataSource());
//		}
	}
	
	public void deleteName(int id) {
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM movie WHERE id=?");
			ps.setInt(1, id);
			int rowsDeleted = ps.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			try { c.close(); } catch (SQLException ex) { }
		} 
//		finally {
//		    // properly release our connection
//		    DataSourceUtils.releaseConnection(c, jt.getDataSource());
//		}
	}
	
	public Movie findName(int id) {
		Movie m = null;
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, title_short from movie WHERE id=?");
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			int count = 0;
			while (result.next()){
				m = movieMapper(result, count);
			    String output = "Movie #%d: %s - %d";
			    System.out.println(String.format(output, ++count, m.getName(), m.getId()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			try { c.close(); } catch (SQLException ex) { }
		} 
//		finally {
//		    // properly release our connection
//		    DataSourceUtils.releaseConnection(c, jt.getDataSource());
//		}
		return m;
	}
	public List<Movie> findNames(){
		List<Movie> movies = null;
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * from movie");
			ResultSet result = ps.executeQuery();
			int count = 0;
			movies = new ArrayList<Movie>();
			while (result.next()){
				Movie m = movieMapper(result, count);
			    movies.add(m);
			    String output = "Movie #%d: %s - %d";
			    System.out.println(String.format(output, ++count, m.getName(), m.getId()));
			    m = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			try { c.close(); } catch (SQLException ex) { }
		} 
//		finally {
//		    // properly release our connection
//		    DataSourceUtils.releaseConnection(c, jt.getDataSource());
//		}
		return movies;
	}
	public void longWork() {
		try {
			c = jt.getDataSource().getConnection();
			Thread.sleep(5000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException inte) {
			// TODO Auto-generated catch block
			inte.printStackTrace();
			try { c.close(); } catch (SQLException ex) { }
		}
//		finally {
//		    // properly release our connection
//		    DataSourceUtils.releaseConnection(c, jt.getDataSource());
//		}
	}
	private static Movie movieMapper(ResultSet rs, int i) throws SQLException {
	    Movie m = new Movie();
	    m.setId(rs.getInt("id"));
	    m.setName(rs.getString("title_short"));
	    return m;
	}
}
