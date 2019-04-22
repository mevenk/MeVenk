/**
 * 
 */
package org.mevenk.webservices.controller.to.root;

import org.mevenk.webservices.deserializer.BasicRequestDeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author vkolisetty
 *
 */
@JsonDeserialize(using = BasicRequestDeSerializer.class)
public class BasicRequest {

	private String string;
	private Integer integer;
	private Character character;

	/**
	 * @param string
	 * @param integer
	 * @param character
	 */
	public BasicRequest(String string, Integer integer, Character character) {
		this.string = string;
		this.integer = integer;
		this.character = character;
	}

	/**
	 * @return the string
	 */
	public final String getString() {
		return string;
	}

	/**
	 * @return the integer
	 */
	public final Integer getInteger() {
		return integer;
	}

	/**
	 * @return the character
	 */
	public final Character getCharacter() {
		return character;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicRequest [string=" + string + ", integer=" + integer + ", character=" + character + "]";
	}

}
