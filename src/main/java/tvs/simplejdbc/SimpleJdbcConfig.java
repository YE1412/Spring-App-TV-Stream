package tvs.simplejdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configurable
@ComponentScan(basePackages = "tvs.simplejdbc")
@PropertySource("classpath:config.properties")
public class SimpleJdbcConfig {
	@Bean
    // Définition des paramètres d'accès à la BD
    public DataSource handMadeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql//localhost/tvstreamdb");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
	@Bean
	public DataSource paramDataSource(@Value("${jdbc.url}") String url,
        @Value("${jdbc.user}") String user,
        @Value("${jdbc.password}") String password) {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setUrl(url);
	        dataSource.setUsername(user);
	        dataSource.setPassword(password);
	        return dataSource;
		
	}
	@Bean
    // Création d'une instance de JdbcTemplate
    public JdbcTemplate myJdbcTemplate(@Autowired DataSource ds) {
        JdbcTemplate t = new JdbcTemplate(ds);
        return t;
    }
	@Bean
	@Primary
	// Définition des paramètres d'accès à la BD
	public DataSource dbcp2DataSource(@Value("${jdbc.url}") String url,
	        @Value("${jdbc.user}") String user,
	        @Value("${jdbc.password}") String password) {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
	    dataSource.setInitialSize(8); // ouvrir cinq connexions
	    dataSource.setMaxTotal(8); // pas plus de cinq connexions
	    return dataSource;
	}
}
