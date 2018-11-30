/**
 * 
 */
package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.response.LoadGameInfoResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class LoadGameInfoTest {
	
	LoadGameInfo loadGameInfo;
	LoadGameInfoResponse response;
	
	@Before
	public void setUp(){
		loadGameInfo=new LoadGameInfo("");
		response=new LoadGameInfoResponse();
	}
	
	@Test
	public void executeTest(){
		loadGameInfo.execute();
		Assert.assertNotNull(response.getFilenames().size());
	}

}
