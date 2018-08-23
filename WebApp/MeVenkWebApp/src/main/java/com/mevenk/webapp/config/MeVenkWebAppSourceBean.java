/**
 *
 */
package com.mevenk.webapp.config;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.exceptionStactTraceAsString;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.AT_SIGH;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.BRACES_CLOSE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.BRACES_OPEN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.DOUBLE_SPACE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.EMPTY_STRING;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.LINE_SEPARATOR;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SINGLE_COLUN_AND_SPACE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SINGLE_SPACE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SQUARE_BRACKET_CLOSE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SQUARE_BRACKET_OPEN;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author venky
 *
 */
public class MeVenkWebAppSourceBean implements InitializingBean, DisposableBean {

	private static final Logger LOG = getLogger(MeVenkWebAppSourceBean.class);

	private String beanName;
	private String beanClassName;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.log(CONFIG, "Properties set for {} [{}]", beanName, beanClassName);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		LOG.log(CONFIG, "Destroying {} [{}]", beanName, beanClassName);

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(beanName + SQUARE_BRACKET_OPEN + this.getClass().getName() + SQUARE_BRACKET_CLOSE + AT_SIGH
				+ Integer.toHexString(this.hashCode()));
		result.append(SINGLE_SPACE + "Object" + SINGLE_SPACE + BRACES_OPEN);
		result.append(LINE_SEPARATOR);

		Field[] fields = this.getClass().getFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append(DOUBLE_SPACE);
			try {
				result.append(field.getName());
				result.append(SINGLE_COLUN_AND_SPACE);
				result.append(field.get(this));
				result.append(LINE_SEPARATOR);
			} catch (IllegalAccessException exception) {
				LOG.log(CONFIG, "Error generating toString during field {}->{}", field.getName(),
						this.getClass().getSimpleName());
				LOG.error("{}", exceptionStactTraceAsString(exception));
			}

		}

		Method[] methods = this.getClass().getMethods();

		String methodName = null;
		String exceptionMessageIllegalArgumentException = null;
		for (Method method : methods) {
			try {
				exceptionMessageIllegalArgumentException = null;
				methodName = method.getName();
				if (methodName.startsWith("get") && !methodName.equalsIgnoreCase("getClass")) {
					result.append(DOUBLE_SPACE);
					// result.append(methodName.substring(3).replaceFirst("\\b([A-Z])(.*)",
					// "\\L$1$2"));
					result.append(new String(EMPTY_STRING + methodName.charAt(3)).toLowerCase() + EMPTY_STRING
							+ methodName.substring(4));
					result.append(SINGLE_COLUN_AND_SPACE);
					result.append(method.invoke(this));
					result.append(LINE_SEPARATOR);
				}
			} catch (IllegalArgumentException illegalArgumentException) {
				exceptionMessageIllegalArgumentException = illegalArgumentException.getMessage();
				if (!isEmpty(exceptionMessageIllegalArgumentException)
						&& !isBlank(exceptionMessageIllegalArgumentException)
						&& exceptionMessageIllegalArgumentException.contains("wrong number of arguments")) {
					continue;
				} else {
					throw illegalArgumentException;
				}
			} catch (Exception exception) {
				LOG.log(CONFIG, "Error generating toString during method {}->{}", methodName,
						this.getClass().getSimpleName());
				LOG.error("{}", exceptionStactTraceAsString(exception));
			}
		}

		result.append(BRACES_CLOSE);

		return result.toString();
	}

}
