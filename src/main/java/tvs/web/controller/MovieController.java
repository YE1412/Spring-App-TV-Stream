package tvs.web.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tvs.daohibernate.model.Movie;
import tvs.interf.ILogger;
import tvs.interf.IMovie;
import tvs.web.validators.MovieFormValidator;
@Controller()
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private IMovie manager;
	@Autowired
	@Qualifier("run")
	private ILogger logger;
	@Autowired
	MovieFormValidator validator;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.log("List of products");
        return "movies-list";
    }
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editMovie(@ModelAttribute Movie m) {
	    return "movieForm";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String saveMovie(@ModelAttribute Movie m, BindingResult result) {
		validator.validate(m, result);
	    if (result.hasErrors()) {
	        return "movieForm";
	    }
	    logger.log("movie id = " + m.getId());
	    if (m.getId() != 0) {
	    	manager.updateMovie(m);
	    }else {
	    	manager.addMovie(m);
	    }
	    return "redirect:list";
	}
	@ModelAttribute("movie")
	public Movie newMovie(
	        @RequestParam(value = "id", required = false) Integer movieNumber) {
	    if (movieNumber != null) {
	    	Movie m = manager.findMovie(movieNumber);
	        logger.log("find movie " + m.getName());
	        return m;
	    }
	    Movie m = new Movie();
	    m.setName("");
	    logger.log("new movie = " + m);
	    return m;
	}
	@ModelAttribute("movies")
	Collection<Movie> movies() {
	    logger.log("Making list of products");
	    return manager.findAllMovies();
	}
//	@ModelAttribute("movieCertifs")
//	public Map<String, String> productTypes() {
//		manager.
//	    Map<String, String> types = new LinkedHashMap<>();
//	    types.put("type1", "Type 1");
//	    types.put("type2", "Type 2");
//	    types.put("type3", "Type 3");
//	    types.put("type4", "Type 4");
//	    types.put("type5", "Type 5");
//	    return types;
//	}
}
