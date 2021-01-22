package br.com.softblue.bluetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.com.softblue.bluetask.domain.task.Task;

@SpringBootApplication
public class BluetaskBackendApplication implements RepositoryRestConfigurer{

	private static final Logger logger = LoggerFactory.getLogger(BluetaskBackendApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BluetaskBackendApplication.class, args);
		logger.info("BlueTask iniciado");
	}

	@Override
	public void configureRepositoryRestConfiguration(
	        RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
			
	    config.exposeIdsFor(Task.class);

	    corsRegistry
	        .addMapping("/**")
		    .allowedOrigins("*")
		    .allowedMethods("GET","POST","PUT","DELETE");

	    logger.info("Repository CORS setub.. OK");		
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vrel) {
		Validator validator = validator();
		vrel.addValidator("beforeCreate", validator);
		vrel.addValidator("beforeSave", validator);
		
		logger.info("Validator... OK!");
	}
}
