/**
 *
 */
package com.mevenk.webapp.util;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.Date;

/**
 * @author venky
 *
 */
public final class DateTimeUtil {

	private Date startDate;
	private Date endDate;
	private Long differenceInMillis;
	private Long differenceInSeconds;
	private Long differenceInMinutes;
	private Long differenceInHours;
	private Long differenceInDays;

	/**
	 * @param dateBefore
	 * @param dateAfter
	 */
	public DateTimeUtil(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		loadStartDateEndDateDifference();
	}

	/**
	 * @return the startDate
	 */
	public final Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public final Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the differenceInMillis
	 */
	public final Long getDifferenceInMillis() {
		return differenceInMillis;
	}

	/**
	 * @return the differenceInSeconds
	 */
	public final Long getDifferenceInSeconds() {
		return differenceInSeconds;
	}

	/**
	 * @return the differenceInMinutes
	 */
	public final Long getDifferenceInMinutes() {
		return differenceInMinutes;
	}

	/**
	 * @return the differenceInHours
	 */
	public final Long getDifferenceInHours() {
		return differenceInHours;
	}

	/**
	 * @return the differenceInDays
	 */
	public final Long getDifferenceInDays() {
		return differenceInDays;
	}

	/**
	 *
	 */
	private void loadStartDateEndDateDifference() {

		differenceInMillis = endDate.getTime() - startDate.getTime();

		differenceInSeconds = MILLISECONDS.toSeconds(differenceInMillis);
		differenceInMinutes = MILLISECONDS.toMinutes(differenceInMillis);
		differenceInHours = MILLISECONDS.toHours(differenceInMillis);
		differenceInDays = MILLISECONDS.toDays(differenceInMillis);

	}

	public final String getStartDateEndDateDifference() {

		return String.format("%02d:%02d:%02d", MILLISECONDS.toHours(differenceInMillis),
				MILLISECONDS.toMinutes(differenceInMillis) % HOURS.toMinutes(1),
				MILLISECONDS.toSeconds(differenceInMillis) % MINUTES.toSeconds(1));
	}

	public static final String getStartDateEndDateDifferences(Date startDate, Date endDate) {
		return new DateTimeUtil(startDate, endDate).getStartDateEndDateDifference();
	}

}
