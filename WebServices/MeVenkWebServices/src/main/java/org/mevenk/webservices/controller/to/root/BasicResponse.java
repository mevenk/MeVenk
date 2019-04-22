/**
 * 
 */
package org.mevenk.webservices.controller.to.root;

/**
 * @author vkolisetty
 *
 */
public class BasicResponse extends BasicRequest {

	private Long timestamp;

	/**
	 * @param string
	 * @param integer
	 * @param character
	 */
	public BasicResponse(String string, Integer integer, Character character) {
		super(string, integer, character);
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * @return the timestamp
	 */
	public final Long getTimestamp() {
		return timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicResponse [timestamp=" + timestamp + ", BasicRequest=" + super.toString() + "]";
	}

}
