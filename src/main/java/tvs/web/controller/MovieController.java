package tvs.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.tracks.AACTrackImpl;
import com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;

import tvs.daohibernate.model.Movie;
import tvs.interf.ILogger;
import tvs.interf.IMovie;
import tvs.web.model.FormDataWithFile;
import tvs.web.validators.MovieFormValidator;
import tvs.web.validators.MovieFormWithDatasValidator;
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
//	    logger.log("movie id = " + m.getId());
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
//	        logger.log("find movie " + m.getName());
	        return m;
	    }
	    Movie m = new Movie();
	    m.setName("");
//	    logger.log("new movie = " + m);
	    return m;
	}
	@ModelAttribute("movies")
	Collection<Movie> movies() {
//	    logger.log("Making list of products");
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
