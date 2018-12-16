/**
 *
 */
package com.mevenk.webapp.to;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author venky
 *
 */
public abstract class BaseTO implements Serializable, Cloneable, Comparable<Object> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7992749326712636838L;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, true);
	}

	/**
	 *
	 */
	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object, true);
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 *
	 */
	@Override
	public int compareTo(Object object) {
		return CompareToBuilder.reflectionCompare(this, object, true);
	}

}
