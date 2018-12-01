package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class SaveGameResponse.
 * @author Hina
 */
public class SaveGameResponse implements Response {
	
	/** The saved file name. */
	private String savedFileName;

	/**
	 * Gets the saved file name.
	 *
	 * @return the saved file name
	 */
	public String getSavedFileName() {
		return savedFileName;
	}

	/**
	 * Sets the saved file name.
	 *
	 * @param savedFileName the new saved file name
	 */
	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

}
