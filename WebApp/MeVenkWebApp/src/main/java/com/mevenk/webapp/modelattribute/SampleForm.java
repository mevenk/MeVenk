/**
 *
 */
package com.mevenk.webapp.modelattribute;

/**
 * @author venky
 *
 */
public class SampleForm {

	private Integer number;
	private String name;
	private Integer radioButton;
	private Boolean checkBoxBoolean;
	private Integer hiddenNumber;
	private String hiddenString;
	private Boolean hiddenBoolean;

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the radioBoolean
	 */
	public Integer getRadioButton() {
		return radioButton;
	}

	/**
	 * @param radioBoolean the radioBoolean to set
	 */
	public void setRadioButton(Integer radioButton) {
		this.radioButton = radioButton;
	}

	/**
	 * @return the checkBoxBoolean
	 */
	public Boolean getCheckBoxBoolean() {
		return checkBoxBoolean;
	}

	/**
	 * @param checkBoxBoolean the checkBoxBoolean to set
	 */
	public void setCheckBoxBoolean(Boolean checkBoxBoolean) {
		this.checkBoxBoolean = checkBoxBoolean;
	}

	/**
	 * @return the hiddenNumber
	 */
	public Integer getHiddenNumber() {
		return hiddenNumber;
	}

	/**
	 * @param hiddenNumber the hiddenNumber to set
	 */
	public void setHiddenNumber(Integer hiddenNumber) {
		this.hiddenNumber = hiddenNumber;
	}

	/**
	 * @return the hiddenString
	 */
	public String getHiddenString() {
		return hiddenString;
	}

	/**
	 * @param hiddenString the hiddenString to set
	 */
	public void setHiddenString(String hiddenString) {
		this.hiddenString = hiddenString;
	}

	/**
	 * @return the hiddenBoolean
	 */
	public Boolean getHiddenBoolean() {
		return hiddenBoolean;
	}

	/**
	 * @param hiddenBoolean the hiddenBoolean to set
	 */
	public void setHiddenBoolean(Boolean hiddenBoolean) {
		this.hiddenBoolean = hiddenBoolean;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SampleForm [number=" + number + ", name=" + name + ", radioBoolean=" + radioButton
				+ ", checkBoxBoolean=" + checkBoxBoolean + ", hiddenNumber=" + hiddenNumber + ", hiddenString="
				+ hiddenString + ", hiddenBoolean=" + hiddenBoolean + "]";
	}

}
