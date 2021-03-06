package tvs.web;

import java.util.Locale;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

public class AppConfig implements WebMvcConfigurer {
	@Bean  
    public InternalResourceViewResolver viewResolver() {  
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
        resolver.setPrefix("/WEB-INF/jsp/");  
        resolver.setSuffix(".jsp");
        return resolver;  
    }
	@Bean("messageSource")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource r = new ResourceBundleMessageSource();
	    r.setBasenames("movie");
	    //r.setUseCodeAsDefaultMessage(true);
	    return r;
	}
	@Bean("localeResolver")
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("en"));
	    return slr;
	}
	@Bean("localChangeInterceptor")
	public LocaleChangeInterceptor localeChangeInter() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("language");
	    return lci;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    return new CommonsMultipartResolver();
	}
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
	// TODO Auto-generated method stub
	  UrlPathHelper pathHelper = new UrlPathHelper();
	  //Enable matrix variable
	  pathHelper.setRemoveSemicolonContent(false);
	  configurer.setUrlPathHelper(pathHelper);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(localeChangeInter());
	}
}
