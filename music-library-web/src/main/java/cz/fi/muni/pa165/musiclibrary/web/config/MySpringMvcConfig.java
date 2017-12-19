package cz.fi.muni.pa165.musiclibrary.web.config;

import cz.fi.muni.pa165.musiclibrary.sampledata.MusicLibraryWithSampleDataConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

/**
 * The central Spring context and Spring MVC configuration.
 * The @Configuration annotation declares it as Spring configuration.
 * The @EnableWebMvc enables default  MVC config for using @Controller, @RequestMapping and so on,
 * see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-config-enable
 *
 * @author Martin Kuba makub@ics.muni.cz
 * @author Jan-Sebastian Fabik
 */

@EnableWebMvc
@Configuration
@Import({MusicLibraryWithSampleDataConfiguration.class})
@ComponentScan(basePackages = "cz.fi.muni.pa165.musiclibrary.web.controllers")
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

	final static Logger log = LoggerFactory.getLogger(MySpringMvcConfig.class);

	private static final String TEXTS = "Texts";

	/**
	 * Enables default Tomcat servlet that serves static files.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		log.debug("enabling default servlet for static files");
		configurer.enable();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	/**
	 * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
	 */
	@Bean
	public ViewResolver viewResolver() {
		log.debug("registering JSP in /WEB-INF/jsp/ as views");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Provides localized messages.
	 */
	@Bean
	public MessageSource messageSource() {
		log.debug("registering ResourceBundle 'Texts' for messages");
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(TEXTS);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Provides JSR-303 Validator.
	 */
	@Bean
	public Validator validator() {
		log.debug("registering JSR-303 validator");
		return new LocalValidatorFactoryBean();
	}
}
