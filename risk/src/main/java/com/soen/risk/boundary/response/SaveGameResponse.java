/**
 * 
 */
package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * @author fly
 *
 */
public class SaveGameResponse implements Response {
	private String savedFileName;

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

}
