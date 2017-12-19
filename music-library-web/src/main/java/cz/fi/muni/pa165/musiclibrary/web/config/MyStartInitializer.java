package cz.fi.muni.pa165.musiclibrary.web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * Replaces web.xml file.
 * Extends the class {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer} that
 * <ul>
 * <li>creates spring context specified in class returned by {@link #getRootConfigClasses()}</li>
 * <li>initializes {@link org.springframework.web.servlet.DispatcherServlet Spring MVC dispatcher servlet} with it</li>
 * <li>maps dispatcher servlet to URL returned by {@link #getServletMappings()}</li>
 * <li>maps filters returned by {@link #getServletFilters()} to the dispatcher servlet</li>
 * </ul>
 *
 * @author Martin Kuba makub@ics.muni.cz
 * @author Jan-Sebastian Fabik
 */
public class MyStartInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{MySpringMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		return new Filter[]{encodingFilter};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
		registration.setMultipartConfig(new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
			32000000, 320000000, 32000000));
	}

}
