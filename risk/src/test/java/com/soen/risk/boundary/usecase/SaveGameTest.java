package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.response.LoadGameInfoResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;


public class SaveGameTest {
    int initialCountOfFiles;

    @Before
    public void setUp(){
        LoadGameInfoResponse response = new LoadGameInfo().execute();
        initialCountOfFiles = response.getFilenames().size();
    }

    @Test
    public void SaveGame_ShouldWriteToDisk() {
        new SaveGame("test.ser").execute();
        LoadGameInfoResponse response = new LoadGameInfo().execute();
        assertEquals(initialCountOfFiles + 1, response.getFilenames().size());
        new File("./saved-games/test.ser").delete();
    }


}
