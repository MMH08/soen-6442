package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;

import java.util.logging.Level;

public class Default implements Usecase {
    public Default(String... args) {
        logger.log(Level.INFO, "Default use case : ", args);
    }


    @Override
    public String execute() {
        return "default";
    }
}
