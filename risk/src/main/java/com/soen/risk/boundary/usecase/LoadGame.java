package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.LoadGameRequest;
import com.soen.risk.boundary.response.LoadGameResponse;
import com.soen.risk.interactor.GamePlay;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * The Class LoadGame.
 *
 * @author Hina
 */
public class LoadGame implements Usecase {

    /**
     * The request.
     */
    private LoadGameRequest request;

    /**
     * The response.
     */
    private LoadGameResponse response;

    /**
     * Instantiates a new load game.
     *
     * @param args the args
     */
    public LoadGame(String... args) {
        request = new LoadGameRequest(args);
        response = new LoadGameResponse();
    }


    /**
     * Loads the saved game from the folder placed beside the project directory's src folder.
     * This retains the phase and current player status. However, refreshes the views by attaching
     * them again.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public LoadGameResponse execute() {
        Path parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
        String relativePath = FileSystems.getDefault().getSeparator() + "saved-games" + FileSystems.getDefault().getSeparator() + request.getFileName();

        FileInputStream fileIn;
        ObjectInputStream objectIn = null;

        try {
            fileIn = new FileInputStream(parentPath + relativePath);
            objectIn = new ObjectInputStream(fileIn);
            GamePlay.setInstance((GamePlay) objectIn.readObject());

            GamePlay gamePlay = GamePlay.getInstance();
            gamePlay.registerGame();
            gamePlay.registerPlayers();
            gamePlay.registerCountries();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
