package tvs.web.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tvs.daohibernate.Dao;
import tvs.daohibernate.model.Movie;
import tvs.daohibernate.model.MovieName;
import tvs.interf.IMovie;

@Service("movieManager")
public class InMemoryMoviesManager implements IMovie {
	static Dao dao;
	final Map<Integer, Movie> movies;
    int maxId = 0;
    
    
	public InMemoryMoviesManager() {
		super();
		this.movies = new HashMap<Integer, Movie>();
		dao = new Dao();
        dao.init();
	}

	@Override
	public void addMovie(Movie m) {
		// TODO Auto-generated method stub
		dao.addMovie(m);
	}

	@Override
	public Movie findMovie(int id) {
		// TODO Auto-generated method stub
		return dao.findMovie(id);
	}

	@Override
	public void updateMovie(Movie m) {
		// TODO Auto-generated method stub
		dao.updateMovie(m);
	}

	@Override
	public void removeMovie(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Movie> findAllMovies() {
		// TODO Auto-generated method stub
		int i = 0;
		for (Movie m:dao.findAllMovies()) {
			this.movies.put(i, m);
			i++;
		}
		return dao.findAllMovies();
	}

	@Override
	public List<Movie> findMoviesByName(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieName> namedQueriesFindAllFilmByNamesAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieName> namedQueriesGetFilmNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findMovieByCertification(String cert) {
		// TODO Auto-generated method stub
		return null;
	}

}
