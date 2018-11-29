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

public class SaveGame implements Usecase {

    private SaveGameRequest request;
    private SaveGameResponse response;

    public SaveGame(String... args) {
        request = new SaveGameRequest(args);
        response = new SaveGameResponse();
    }

    @Override
    public SaveGameResponse execute() {
        Path parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
        String relativePath = FileSystems.getDefault().getSeparator() +"saved-games" + FileSystems.getDefault().getSeparator() + request.getFilename();
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
