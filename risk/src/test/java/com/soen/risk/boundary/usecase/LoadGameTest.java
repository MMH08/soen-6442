/**
 * 
 */
package com.soen.risk.boundary.usecase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.soen.risk.interactor.GamePlay;

/**
 * @author fly
 *
 */
public class LoadGameTest {
	 LoadGame loadGame;
	 GamePlay gamePlay;
	 
	 @Before
	 public void setUp(){
		 loadGame=new LoadGame("demo.ser");
		 
	 }
	 
	 
	 @Test
	 public void executeTest(){
		 loadGame.execute();
		 Assert.assertNotNull(gamePlay.getInstance());
	 }

}
