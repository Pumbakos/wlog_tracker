package wlog_tracker.trackerdb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.yml")
public class TrackerDbPersistenceJPAConfig {

	@Autowired
	private Environment env;
	
	@Bean(name = "trackerDBDataSource")
	public DataSource trackerDBDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("spring.tracker-db-datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.tracker-db-datasource.url"));
		dataSource.setUsername(env.getProperty("spring.tracker-db-datasource.username"));
		dataSource.setPassword(env.getProperty("spring.tracker-db-datasource.password"));

		return dataSource;
	}
}
