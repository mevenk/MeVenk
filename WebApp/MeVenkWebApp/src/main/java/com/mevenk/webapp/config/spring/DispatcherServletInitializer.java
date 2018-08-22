/**
 *
 */
package com.mevenk.webapp.config.spring;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mevenk.webapp.config.spring.listener.MeVenkWebAppContextLoaderInitializer;

/**
 * @author venky
 *
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String URL_MAPPING_DISPATCHER_SERVLET_MEVENK = "*.mevenk";

	private static final Logger LOG = getLogger(DispatcherServletInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOG.log(CONFIG, "Dispatcher Servet startup" + this);

		WebApplicationContext webApplicationDispatcherContext = getDispatcherContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationDispatcherContext));
		// servletContext.addListener(MeVenkWebAppContextLoaderInitializer.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationDispatcherContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic dispatcherDynamicServlet = servletContext.addServlet("mevenk", dispatcherServlet);
		dispatcherDynamicServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		dispatcherDynamicServlet.addMapping(URL_MAPPING_DISPATCHER_SERVLET_MEVENK);
		dispatcherDynamicServlet.setLoadOnStartup(1);

	}

	private AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationDispatcherContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationDispatcherContext.register(MeVenkWebAppRootConfiguration.class);
		annotationConfigWebApplicationDispatcherContext.register(MeVenkWebAppServletConfiguration.class);
		return annotationConfigWebApplicationDispatcherContext;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MeVenkWebAppRootConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MeVenkWebAppServletConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { URL_MAPPING_DISPATCHER_SERVLET_MEVENK };
	}

}
