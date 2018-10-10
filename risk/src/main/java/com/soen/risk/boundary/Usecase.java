package com.soen.risk.boundary;

import java.util.logging.Logger;

public interface Usecase<T> {
    static Logger logger = Logger.getLogger(Usecase.class.getName());

    public void execute();

    public T getResponse();

}
