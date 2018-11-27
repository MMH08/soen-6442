/**
 * 
 */
package com.soen.risk.boundary.usecase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.ResourceUtils;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.response.CreateMapResponse;
import com.soen.risk.boundary.response.SaveGameResponse;
import com.soen.risk.interactor.GamePlay;

/**
 * @author fly
 *
 */
public class SaveGame implements Usecase
{

	private GamePlay gamePlay;
	private  SaveGameResponse response;
	private String savedFileName;
	
	public SaveGame(){
		response = new SaveGameResponse();
	}
		
		@Override
		public SaveGameResponse execute() {
			gamePlay=GamePlay.getInstance();
			
			savedFileName=(new SimpleDateFormat("yyyyMMddHHmm'.ser'").format(new Date()));
			
			FileOutputStream fileOutputStream = null;
			ObjectOutputStream objectOutputStream = null;
			
			try{
		
				File file=ResourceUtils.getFile("classpath:");
				String tempLocation=file.getAbsolutePath();
				String[] parts = tempLocation.split("risk");
				
				String fileLocation=parts[0]+"docs\\ser\\"+savedFileName;
				System.out.println("Saving file"+fileLocation);
				
				fileOutputStream = new FileOutputStream(new File(fileLocation));
				
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(gamePlay);
			}
			catch(IOException ioException)
			{
				System.out.println(ioException.getMessage());
			}
			finally{
				IOUtils.closeQuietly(objectOutputStream);
			}
			response.setSavedFileName(savedFileName);
			return response;
		}
		
	
}
