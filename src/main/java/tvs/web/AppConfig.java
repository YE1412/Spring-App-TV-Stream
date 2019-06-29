package tvs.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

@SuppressWarnings("deprecation")
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
	    System.out.println("OK!M");
	    r.setBasenames("/tvs/web/movie");
	    //r.setUseCodeAsDefaultMessage(true);
	    return r;
	}
	@Bean("localeResolver")
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		System.out.println("OK!LR");
		slr.setDefaultLocale(new Locale("en"));
	    return slr;
	}
	@Bean("localChangeInterceptor")
	public LocaleChangeInterceptor localeChangeInter() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		System.out.println("OK!LCI");
		lci.setParamName("language");
	    return lci;
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
		System.out.println("OK!");
	      registry.addInterceptor(localeChangeInter());
	}
}
