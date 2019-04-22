/**
 * 
 */
package org.mevenk.webservices.config.spring.properties;

import javax.annotation.PostConstruct;

import org.mevenk.webservices.util.MeVenkWebServicesUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author vkolisetty
 *
 */
@ConfigurationProperties("base")
public class BaseProperties {

	private String string;
	private Integer integer;
	private Character character;

	/**
	 * @return the string
	 */
	public final String getString() {
		return string;
	}

	/**
	 * @param string the string to set
	 */
	public final void setString(String string) {
		this.string = string;
	}

	/**
	 * @return the integer
	 */
	public final Integer getInteger() {
		return integer;
	}

	/**
	 * @param integer the integer to set
	 */
	public final void setInteger(Integer integer) {
		this.integer = integer;
	}

	/**
	 * @return the character
	 */
	public final Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public final void setCharacter(Character character) {
		this.character = character;
	}

	@PostConstruct
	private void postConstruct() {

		MeVenkWebServicesUtil.validateNotNull(true, string, integer, character);

	}

}
