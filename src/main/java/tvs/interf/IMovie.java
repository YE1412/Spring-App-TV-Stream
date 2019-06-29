package tvs.interf;

import java.util.List;

import org.springframework.stereotype.Service;

import tvs.daohibernate.model.Movie;
import tvs.daohibernate.model.MovieName;

@Service
public interface IMovie {
	public void addMovie(Movie m);
	public Movie findMovie(int id);
	public void updateMovie(Movie m);
	public void removeMovie(int id);
	public List<Movie> findAllMovies();
	public List<Movie> findMoviesByName(String pattern);
	public List<MovieName> namedQueriesFindAllFilmByNamesAsc();
	public List<MovieName> namedQueriesGetFilmNames();
	public List<Movie> findMovieByCertification(String cert);
}
