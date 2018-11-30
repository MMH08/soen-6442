/**
 * 
 */
package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.request.SaveGameRequest;
import com.soen.risk.boundary.response.LoadGameInfoResponse;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * @author fly
 *
 */
public class SaveGameTest {
	SaveGame saveGame;
	SaveGameRequest request;
	LoadGameInfo loadGame;
	LoadGameInfoResponse response;
	List fileName=new ArrayList();
	Path parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "saved-games";
    File directory = new File(parentPath + relativePath);
	
	
	@Before
	public void setUp(){
		request=new SaveGameRequest("test.ser");
				
		saveGame=new SaveGame("test.ser");
		
		
		
	}
	
	@Test
	public void executeTest(){
		saveGame.execute();
		Arrays.toString(directory.listFiles());
		Assert.assertTrue(Arrays.toString(directory.listFiles()).contains("test"));
		
	}
	

}
