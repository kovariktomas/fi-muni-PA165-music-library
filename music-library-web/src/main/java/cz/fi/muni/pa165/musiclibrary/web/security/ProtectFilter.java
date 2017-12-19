package cz.fi.muni.pa165.musiclibrary.web.security;

import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
	urlPatterns = {
		"/musician/create",
		"/musician/edit/*",
		"/musician/delete/*",
		"/album/create",
		"/album/edit/*",
		"/album/delete/*",
		"/song/create",
		"/song/delete/*",
		"/genre/create",
		"/genre/edit/*",
		"/genre/delete/*"
	}
)
public class ProtectFilter implements Filter {
	@Override
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) s;

		ApplicationUserDTO userDTO = (ApplicationUserDTO) request.getSession().getAttribute("authenticatedUser");

		if (userDTO == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
