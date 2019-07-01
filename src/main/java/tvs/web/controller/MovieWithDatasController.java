package tvs.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tvs.daohibernate.model.Movie;
import tvs.interf.ILogger;
import tvs.interf.IMovie;
import tvs.web.model.FormDataWithFile;
import tvs.web.validators.MovieFormWithDatasValidator;
@Controller()
@RequestMapping("/movies_with_datas")
public class MovieWithDatasController {
	@Autowired
	private IMovie manager;
	@Autowired
	@Qualifier("run")
	private ILogger logger;
	@Autowired
	MovieFormWithDatasValidator validator;
	@ModelAttribute("movies")
	Collection<Movie> movies() {
//	    logger.log("Making list of products");
	    return manager.findAllMovies();
	}
	@ModelAttribute("movieWithDatas")
	public FormDataWithFile newMovieWithDatas(@RequestParam(value = "id", required = false) Integer movieNumber) {
	    if (movieNumber != null) {
	    	Movie m = manager.findMovie(movieNumber);
	    	FormDataWithFile fdf = FormDataWithFile.of(m.getName());
//	        logger.log("find movie " + m.getName());
	        return fdf;
	    }
	    FormDataWithFile fdf = new FormDataWithFile();
	    fdf.setDest_name("");
//	    logger.log("new movieDataWithFile = " + fdf);
	    return fdf;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.log("List of products");
        return "movies-with-datas-list";
    }
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String submitFile(@ModelAttribute FormDataWithFile fdf){
		return "movieWithDatasForm";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String submitFile(@ModelAttribute FormDataWithFile fdf, 
			BindingResult result,
			ModelMap modelMap) throws Exception{
		validator.validate(fdf, result);
	    if (result.hasErrors()) {
	        return "movieWithDatasForm";
	    }
	    File sf = null;
	    if (!fdf.getFile().isEmpty()) {
	    	int ind = fdf.getFile().getOriginalFilename().lastIndexOf(".");
	    	String ext = fdf.getFile().getOriginalFilename().substring(ind);
	    		byte[] bytes = fdf.getFile().getBytes();
	    		//Creating the directory to store file
	    		String rootPath = System.getProperty("catalina.home");
	    		File dir = createRootTmpFile(rootPath);
	    		//Create the file on server
	    		sf = createFileOnServer(dir, fdf.getDest_name()+ext, bytes);
//	    		logger.log("Server File Location = "+serverFile.getAbsolutePath());
	    }else {
	    	logger.log("File empty !");
	    }
//	    try {
//	    	File filsDest = new File("mov.txt");
//	    	URI uri = ClassLoader.getSystemResource("classpath:content/").toURI();
//	    	String mainPath = Paths.get(uri).toString();
//	    	Path path = Paths.get(mainPath ,"mov.txt");find movie " + formDataWithFile.getFile().getOriginalFilename());
//	    	if (!filsDest.exists()) {
//	    		filsDest.createNewFile();
//	    		Files.copy(formDataWithFile.getFile().getInputStream(), filsDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
//	    		H264TrackImpl h264Track = new H264TrackImpl(new FileDataSourceImpl(formDataWithFile.getFile()));
//	    		AACTrackImpl aacTrack = new AACTrackImpl(new FileDataSourceImpl("audio.aac"));
//	    		com.googlecode.mp4parser.authoring.Movie movie = new com.googlecode.mp4parser.authoring.Movie();
//	    		Container mp4file = new DefaultMp4Builder().build(movie);
//	    		FileChannel fc = new FileOutputStream(new File("classpath:content/output.mp4")).getChannel();
//	    		mp4file.writeContainer(fc);
//	    		fc.close();
//	    		movie.addTrack(h264Track);
//	    		movie.addTrack(aacTrack);
//	    		filsDest.createNewFile();
//			}
			//formDataWithFile.getFile().transferTo(new File("classpath:content/output.mp4"));
//		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    Movie m=Movie.of(fdf.getDest_name());
	    m.setFilePath(sf.getPath());
	    manager.addMovie(m);
	    
		modelMap.addAttribute("formDataWithFile", fdf);
	    return "movieDetails";
	}
	private File createRootTmpFile(String rootPath) {
		File dir = new File(rootPath + File.separator + "tmpFiles");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	
	private File createFileOnServer(File dir, String fileFullNameWithExt, byte[] bytes) throws Exception {
		File serverFile = new File(dir.getAbsolutePath()+File.separator+fileFullNameWithExt);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
		logger.log("Server File Location = "+serverFile.getAbsolutePath());
		return serverFile;
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
