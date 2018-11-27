/**
 * 
 */
package com.soen.risk.boundary.usecase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.util.ResourceUtils;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.CreateMapRequest;
import com.soen.risk.boundary.request.LoadSavedGameRequest;
import com.soen.risk.boundary.response.CreateMapResponse;
import com.soen.risk.boundary.response.LoadSavedGameResponse;
import com.soen.risk.interactor.GamePlay;

/**
 * @author fly
 *
 */
public class LoadSavedGame implements Usecase
{
	GamePlay gamePlay;
	/** The request. */
    private LoadSavedGameRequest request;
    
    /** The response. */
    private LoadSavedGameResponse response;
	
	 public LoadSavedGame(String... args) {
	        request = new LoadSavedGameRequest(args);
	        response = new LoadSavedGameResponse();
	    }
	

	@Override
	public LoadSavedGameResponse execute() 
	{
		
		FileInputStream fileInputStream;
		ObjectInputStream objInputStream;
		String savedFileName=request.getFileName();
		File file;
		try {
			file = ResourceUtils.getFile("classpath:");
			String tempLocation=file.getAbsolutePath();
			String[] parts = tempLocation.split("risk");
			String fileLocation=parts[0]+"docs\\ser\\"+savedFileName;
			
			System.out.println("Getting file from"+fileLocation);
			
			fileInputStream = new FileInputStream(new File(fileLocation));
			objInputStream = new ObjectInputStream(fileInputStream);
			
			GamePlay.setInstance((GamePlay)objInputStream.readObject());
			if ((GamePlay)objInputStream.readObject()!=null)
				response.setStatus(true);
				
			objInputStream.close();
			fileInputStream.close();
		} 
		catch (FileNotFoundException fileNotfound) {
			System.out.println(fileNotfound.getMessage());
		} 
		catch (IOException ioException) {
		
			System.out.println(ioException.getMessage());
		} 
		catch (ClassNotFoundException classNotFound) {
			
			System.out.println(classNotFound.getMessage());
		}
		
		
		
		
		return response;
	}

}
