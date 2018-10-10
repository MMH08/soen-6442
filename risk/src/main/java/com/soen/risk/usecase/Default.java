package com.soen.risk.usecase;

import com.soen.risk.boundary.Usecase;

import java.util.logging.Level;

public class Default implements Usecase {
    public Default(String... args) {
        logger.log(Level.INFO, "Default use case : ", args);
    }


    @Override
    public void execute() {

    }

    @Override
    public Object getResponse() {
        return null;
    }
}
