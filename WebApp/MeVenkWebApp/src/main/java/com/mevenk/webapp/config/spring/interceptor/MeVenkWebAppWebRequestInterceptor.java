package com.mevenk.webapp.config.spring.interceptor;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author venky
 *
 */
public class MeVenkWebAppWebRequestInterceptor implements WebRequestInterceptor {

	private static final Logger LOG = getLogger(MeVenkWebAppWebRequestInterceptor.class);

	@Autowired
	private HttpServletRequest httpRequest;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#preHandle(org.
	 * springframework.web.context.request.WebRequest)
	 */
	@Override
	public void preHandle(WebRequest webRequest) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - preHandle");
		LOG.debug("Request:{}", webRequest);
		validateRequestpreHandle(webRequest);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#postHandle(org.
	 * springframework.web.context.request.WebRequest,
	 * org.springframework.ui.ModelMap)
	 */
	@Override
	public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - postHandle");
		LOG.debug("Request:{}|ModelMap:{}", webRequest, modelMap);
		postRequestActivities(webRequest, modelMap);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#afterCompletion
	 * (org.springframework.web.context.request.WebRequest, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(WebRequest webRequest, Exception exception) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - afterCompletion");
		if (exception != null) {
			LOG.error("Exception:{}", exception);
		}

	}

	/**
	 *
	 * @param webRequest
	 */
	private void validateRequestpreHandle(WebRequest webRequest) {

		if (httpRequest.getSession(false) == null) {
			LOG.info("HTTP Session not available!");
			String sessionId = webRequest.getSessionId();
			LOG.log(CONFIG, "HTTP Session created with id:{}", sessionId);
		}

	}

	/**
	 *
	 * @param webRequest
	 * @param modelMap
	 */
	private void postRequestActivities(WebRequest webRequest, ModelMap modelMap) {
		LOG.debug("Context path:{}, ModelMap size:{}", webRequest.getContextPath(), modelMap.size());

	}

}