/**
 *
 */
package com.mevenk.webapp.dao.functions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.jdbc.ReturningWork;

/**
 * @author venky
 *
 */
public class GetFormattedDate implements ReturningWork<String> {

	private String dateFormat;

	public GetFormattedDate() {
		this.dateFormat = "Dy, dd MON yyyy  HH24:MI:SS.MS";
	}

	/**
	 *
	 */
	public GetFormattedDate(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String execute(Connection connection) throws SQLException {
		CallableStatement functionCallFormattedDate = connection.prepareCall("? = call TO_CHAR(NOW(), :dateFormat)");
		functionCallFormattedDate.setString("dateFormat", dateFormat);
		functionCallFormattedDate.execute();
		return functionCallFormattedDate.getString(1);
	}

}
