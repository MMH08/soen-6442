package com.soen.risk.interactor;

import java.util.logging.Logger;

public interface Phase {
    static Logger logger = Logger.getLogger(Phase.class.getName());

    public boolean isValid();

    public void begin();

    public void execute();

    public void exit();

    public String getName();

}