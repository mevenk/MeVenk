/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.config.servlet.WebApplicationInitializer.runInitialActivities;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.Logger;

/**
 * @author venky
 *
 */
@WebServlet(displayName = "InitialContextServlet", loadOnStartup = 2, urlPatterns = { "*.initialContextServlet" })
public class InitialContextServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3714753823237815022L;

	private static final Logger LOG = getLogger(InitialContextServlet.class);

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		LOG.log(CONFIG, "Initializing Servlet[" + this + "]");
		try {
			runInitialActivities(servletConfig);
		} catch (Exception exception) {
			exception.printStackTrace();
			LOG.error("ERROR Loading Master Data!!!!");
		}
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
