/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.logClientDetails;
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
		LOG.log(CONFIG, "Initializing Servlet[" + this + "]");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("Application launched @ " + new Date());
		logClientDetails(req);
		resp.sendRedirect("welcome.mevenk");
	}

	@Override
	public void destroy() {
		super.destroy();
		LOG.log(CONFIG, "Initial Servlet[" + this + "]destroyed!!");
	}

	@Override
	public String toString() {
		return super.toString() + "|" + getServletName();
	}

}
