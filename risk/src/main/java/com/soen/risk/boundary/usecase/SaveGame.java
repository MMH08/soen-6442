package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.SaveGameRequest;
import com.soen.risk.boundary.response.SaveGameResponse;
import com.soen.risk.interactor.GamePlay;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * The Class SaveGame.
 *
 * @author Hina
 */
public class SaveGame implements Usecase {

    /**
     * The request.
     */
    private SaveGameRequest request;

    /**
     * The response.
     */
    private SaveGameResponse response;

    /**
     * Instantiates a new save game.
     *
     * @param args the args
     */
    public SaveGame(String... args) {
        request = new SaveGameRequest(args);
        response = new SaveGameResponse();
    }

    /**
     * Use case which is responsible to save the game by using the serialization.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public SaveGameResponse execute() {
        Path parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
        String relativePath = FileSystems.getDefault().getSeparator() + "saved-games" + FileSystems.getDefault().getSeparator() + request.getFilename();
        FileOutputStream fileOut;
        ObjectOutputStream objectOut = null;
        try {
            fileOut = new FileOutputStream(parentPath + relativePath);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(GamePlay.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }


}
