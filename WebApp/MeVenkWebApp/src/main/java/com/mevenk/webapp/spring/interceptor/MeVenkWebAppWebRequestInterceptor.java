package com.mevenk.webapp.spring.interceptor;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author venky
 *
 */
public class MeVenkWebAppWebRequestInterceptor implements WebRequestInterceptor {

	private static final Logger LOG = getLogger(MeVenkWebAppWebRequestInterceptor.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#preHandle(org.
	 * springframework.web.context.request.WebRequest)
	 */
	@Override
	public void preHandle(WebRequest request) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - preHandle");

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
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - postHandle");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.context.request.WebRequestInterceptor#afterCompletion
	 * (org.springframework.web.context.request.WebRequest, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		LOG.info("MeVenkWebAppWebRequestInterceptor - afterCompletion");

	}

}