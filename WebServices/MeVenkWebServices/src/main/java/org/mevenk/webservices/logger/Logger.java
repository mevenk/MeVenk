/**
 * 
 */
package org.mevenk.webservices.logger;

/**
 * @author vkolisetty
 *
 */
public interface Logger {

	public void debug(final Object message);

	public void debug(final String message);

	public void debug(final String message, final Object... params);

	public void error(final Object message);

	public void error(final String message);

	public void error(final String message, final Object... params);

	public void error(final Object message, final Throwable throwable);

	public void error(final String message, final Throwable throwable);

	public void error(final String message, final Throwable throwable, final Object... params);

	public void fatal(final Object message);

	public void fatal(final String message);

	public void fatal(final String message, final Object... params);

	public void info(final Object message);

	public void info(final String message);

	public void info(final String message, final Object... params);

	public void trace(final Object message);

	public void trace(final String message);

	public void trace(final String message, final Object... params);

	public void warn(final Object message);

	public void warn(final String message);

	public void warn(final String message, final Object... params);

}
