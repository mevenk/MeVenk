/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.util.http.client.MeVenkWebAppClientUtil.logClientDetails;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

/**
 * @author venky
 *
 */
@WebServlet(displayName = "MeVenkWebAppApplicationLaunchServlet", urlPatterns = { "/" })
public class MeVenkWebAppApplicationLaunchServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 4335245090845278657L;

	private static final Logger LOG = getLogger(MeVenkWebAppApplicationLaunchServlet.class);

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		LOG.log(CONFIG, "Initializing Application launch Servlet[" + this + "]");
	}

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		LOG.info("Application launched @ " + new Date());
		logClientDetails(httpServletRequest);
		try {
			httpServletResponse.sendRedirect("welcome.mevenk");
		} catch (IOException ioException) {
			LOG.error(ioException);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		LOG.log(CONFIG, "Application launch Servlet[" + this + "] destroyed!!");
	}

	@Override
	public String toString() {
		return super.toString() + "|" + getServletName();
	}

}
