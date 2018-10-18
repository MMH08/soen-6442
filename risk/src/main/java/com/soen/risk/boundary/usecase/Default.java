package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;

import java.util.logging.Level;

/**
 * The Class Default.
 */
public class Default implements Usecase {
    
    /**
     * Instantiates a new default.
     *
     * @param args the args
     */
    public Default(String... args) {
        logger.log(Level.INFO, "Default use case : ", args);
    }


    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public String execute() {
        return "default";
    }
}
