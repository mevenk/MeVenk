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
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOG = getLogger(DispatcherServletInitializer.class);

	private static final String SYSTEM_PROPERTY_NAME_DEPLOYMENT_SERVER_NAME = "deploymentServerName";

	private static final String URL_MAPPING_DISPATCHER_SERVLET_MEVENK = "*.mevenk";

	private enum DeploymentServer {
		TOMCAT("org.apache.catalina.servlets.DefaultServlet");

		private String qualifiedClassNameDefaultServlet;

		private DeploymentServer(String qualifiedClassNameDefaultServlet) {
			this.qualifiedClassNameDefaultServlet = qualifiedClassNameDefaultServlet;
		}

		/**
		 * @return the qualifiedClassNameDefaultServlet
		 */
		public final String getQualifiedClassNameDefaultServlet() {
			return qualifiedClassNameDefaultServlet;
		}

	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		setCorrelationIdThreadContext("STARTUP");

		String systemPropertyDeploymentServerName = System.getProperty(SYSTEM_PROPERTY_NAME_DEPLOYMENT_SERVER_NAME);

		if (systemPropertyDeploymentServerName == null || systemPropertyDeploymentServerName.isEmpty()) {
			throw new IllegalArgumentException(
					"System Property[" + SYSTEM_PROPERTY_NAME_DEPLOYMENT_SERVER_NAME + "] not available!!");
		}

		LOG.log(CONFIG, "Deployment server:", systemPropertyDeploymentServerName);

		DeploymentServer deploymentServer = DeploymentServer.valueOf(systemPropertyDeploymentServerName);
		LOG.log(CONFIG, "DeploymentServer:", deploymentServer);
		String qualifiedClassNameDefaultServletClass = deploymentServer.getQualifiedClassNameDefaultServlet();

		LOG.log(CONFIG, "Default Servlet Class Name:", qualifiedClassNameDefaultServletClass);

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
				qualifiedClassNameDefaultServletClass);
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
