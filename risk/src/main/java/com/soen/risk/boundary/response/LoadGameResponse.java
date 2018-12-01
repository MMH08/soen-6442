package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class LoadGameResponse.
 * @author Hina
 */
public class LoadGameResponse implements Response{
	
	/** The status. */
	private boolean status;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
