package com.soen.risk.boundary;

import java.util.logging.Logger;

/**
 * The Interface Usecase.
 *
 * @param <T> the generic type
 */
public interface Usecase<T> {
    
    /** The logger. */
    static Logger logger = Logger.getLogger(Usecase.class.getName());

    /**
     * Execute.
     *
     * @return the t
     */
    public T execute();

}
