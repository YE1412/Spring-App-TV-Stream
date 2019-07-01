package tvs.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tvs.web.business.SpringBusinessConfig;
@Import(SpringBusinessConfig.class)
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
				"tvs.imp",
				"tvs.web"},
		basePackageClasses = {SpringStart.class})
public class SpringStart implements WebApplicationInitializer {
//	@Autowired
//	@Qualifier("run")
//	public ILogger logger;
	private String TMP_FOLDER = "/tmp"; 
	private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024 * 1024;
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		// create a context and find beans
//        try (AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");){	        	
//            logger = ctx.getBean("stderrLogger", ILogger.class);
//    		logTrace("Starting App...");
//        };
//		logTrace("Starting App...");
		// Init application context
        AnnotationConfigWebApplicationContext webCtx
            = new AnnotationConfigWebApplicationContext();
//        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
//		File filesDir = (File) servletContext.;
        webCtx.register(AppConfig.class);
        webCtx.register(SpringStart.class);
        webCtx.setServletContext(servletContext);
        // Init dispatcher servlet
        ServletRegistration.Dynamic servlet
            = servletContext.addServlet("springapp", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 5, MAX_UPLOAD_SIZE / 2);
               
        servlet.setMultipartConfig(multipartConfigElement);
        servlet.addMapping("*.htm");
        servlet.addMapping("/actions/*");
	}
//	private void logTrace(String msg) {
//		this.logger.log(msg);
//	}
//	@Bean
//	public ViewResolver viewResolver() {
//	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	    viewResolver.setViewClass(JstlView.class);
//	    viewResolver.setPrefix("/WEB-INF/jsp/");
//	    viewResolver.setSuffix(".jsp");
//	    return viewResolver;
//	}
//	@Bean("messageSource")
//	public ResourceBundleMessageSource messageSource() {
//	    ResourceBundleMessageSource r = new ResourceBundleMessageSource();
//	    System.out.println("OK!M");
//	    r.setBasenames("/tvs/web/movie_fr_FR");
//	    r.setUseCodeAsDefaultMessage(true);
//	    return r;
//	}
//	@Bean("localeResolver")
//	public SessionLocaleResolver localeResolver() {
//		SessionLocaleResolver slr = new SessionLocaleResolver();
//		System.out.println("OK!LR");
//		slr.setDefaultLocale(Locale.FRANCE);
//	    return slr;
//	}
//	@Bean("localChangeInterceptor")
//	public LocaleChangeInterceptor localeChangeInter() {
//		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//		System.out.println("OK!LCI");
//		lci.setParamName("language");
//	    return lci;
//	}
//	@Bean
//	public SimpleUrlHandlerMapping simpleUrlMap() {
//		Map<String, String> urlMap = new HashMap<String, String>();
//		urlMap.put("*.htm", "messageSource");
//		urlMap.put("/actions/*", "messageSource");
//		SimpleUrlHandlerMapping sum = new SimpleUrlHandlerMapping();
//		sum.setInterceptors(localeChangeInter());
//		sum.setUrlMap(urlMap);
//		return sum;
//		
//	}
}
