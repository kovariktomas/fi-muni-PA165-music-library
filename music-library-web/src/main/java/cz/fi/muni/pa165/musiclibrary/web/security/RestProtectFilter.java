package cz.fi.muni.pa165.musiclibrary.web.security;

import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;
import cz.fi.muni.pa165.musiclibrary.facade.ApplicationUserFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

@WebFilter(urlPatterns = "/rest/*")
public class RestProtectFilter implements Filter {

	private ApplicationUserFacade applicationUserFacade;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApplicationContext context = WebApplicationContextUtils
			.getRequiredWebApplicationContext(filterConfig.getServletContext());
		this.applicationUserFacade = context.getBean(ApplicationUserFacade.class);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) s;

		if (!isUserAuthorized(request)) {
			response401(response);
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean isUserAuthorized(HttpServletRequest request) {
		// all users can read, only authenticated users can modify
		if (request.getMethod().equals("GET")) {
			return true;
		}

		String auth = request.getHeader("Authorization");

		if (auth == null) {
			return false;
		}

		String[] credentials = parseAuthHeader(auth);
		return areCredentialsValid(credentials);
	}

	private String[] parseAuthHeader(String auth) {
		return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
	}

	private boolean areCredentialsValid(String[] credentials) {
		return credentials.length == 2 && areCredentialsValid(credentials[0], credentials[1]);
	}

	private boolean areCredentialsValid(String email, String password) {
		ApplicationUserDTO found;

		try {
			found = applicationUserFacade.findByEmail(email);
		} catch (EmptyResultDataAccessException ex) {
			return false;
		}

		return found != null && applicationUserFacade.verifyPassword(found.getId(), password);
	}

	private void response401(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().println("{\"error\":\"Authorization required (HTTP Basic)\"}");
	}
}
