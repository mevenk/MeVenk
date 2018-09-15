/**
 *
 */
package com.mevenk.webapp.bdo;

import java.util.Date;

/**
 * @author venky
 *
 */
public interface BaseBDO {

	public Date databaseTime();

	public String databaseTimeFormatted();

}
