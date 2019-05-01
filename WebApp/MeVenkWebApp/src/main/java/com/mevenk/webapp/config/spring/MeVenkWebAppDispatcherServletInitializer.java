/**
 *
 */
package com.mevenk.webapp.config.spring;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.util.ThreadContextUtil.setCorrelationIdThreadContext;
import static com.mevenk.webapp.util.http.client.MeVenkWebAppClientUtil.RESOURCE_TYPE_CLIENT_RESOURCE;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mevenk.webapp.config.spring.database.MeVenkDatabaseConfiguration;

/**
 * @author venky
 *
 */
public class MeVenkWebAppDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOG = getLogger(MeVenkWebAppDispatcherServletInitializer.class);
	
	private static final String DEFAULT_SERVLET_CLASS_NAME = "org.apache.catalina.servlets.DefaultServlet";

	private static final String URL_MAPPING_DISPATCHER_SERVLET_MEVENK = "*.mevenk";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		setCorrelationIdThreadContext("STARTUP");

		LOG.log(CONFIG, "Dispatcher Servet startup" + this);

		WebApplicationContext webApplicationDispatcherContext = getDispatcherContext();

		servletContext.addListener(new ContextLoaderListener(webApplicationDispatcherContext));

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationDispatcherContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
		FilterRegistration.Dynamic dynamicFilterRegistration = servletContext.addFilter("characterEncodingFilter",
				characterEncodingFilter);
		dynamicFilterRegistration.addMappingForUrlPatterns(null, true, URL_MAPPING_DISPATCHER_SERVLET_MEVENK);

		ServletRegistration.Dynamic dispatcherDynamicServlet = servletContext.addServlet("mevenk", dispatcherServlet);
		dispatcherDynamicServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		dispatcherDynamicServlet.addMapping(URL_MAPPING_DISPATCHER_SERVLET_MEVENK);
		dispatcherDynamicServlet.setLoadOnStartup(1);

		ServletRegistration.Dynamic defaultServlet = servletContext.addServlet("default",
				DEFAULT_SERVLET_CLASS_NAME);
		for (String resourceTypeExt : RESOURCE_TYPE_CLIENT_RESOURCE) {
			defaultServlet.addMapping("*." + resourceTypeExt);
		}

	}

	private static AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationDispatcherContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationDispatcherContext.register(MeVenkWebAppRootConfiguration.class);
		return annotationConfigWebApplicationDispatcherContext;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MeVenkWebAppRootConfiguration.class, MeVenkWebAppPropertiesConfiguration.class,
				MeVenkWebAppAspectConfiguration.class, MeVenkDatabaseConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MeVenkWebAppServletConfiguration.class, TilesConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { URL_MAPPING_DISPATCHER_SERVLET_MEVENK };
	}

}
