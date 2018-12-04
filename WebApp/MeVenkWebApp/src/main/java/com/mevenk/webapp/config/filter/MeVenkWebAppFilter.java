/**
 *
 */
package com.mevenk.webapp.config.filter;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;

import com.mevenk.webapp.util.MeVenkWebAppUtil;
import com.mevenk.webapp.util.constants.MeVenkWebAppConstants;

/**
 * @author venky
 *
 */
@WebFilter(filterName = "MeVenkWebAppFilter", urlPatterns = "*")
public class MeVenkWebAppFilter implements Filter {

	private static final Logger LOG = getLogger(MeVenkWebAppFilter.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception exception) {
			LOG.error(exception);
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			httpServletRequest.setAttribute(MeVenkWebAppConstants.EXCEPTION_OCCURED_ATTRIBUTE_NAME,
					MeVenkWebAppUtil.exceptionStactTraceAsString(exception));
			httpServletRequest.getRequestDispatcher("views/unknownError.jsp").forward(httpServletRequest, response);
		}

	}

}
