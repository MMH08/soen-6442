/**
 * 
 */
package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * @author fly
 *
 */
public class LoadGameResponse implements Response{
	
	private boolean status;

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
