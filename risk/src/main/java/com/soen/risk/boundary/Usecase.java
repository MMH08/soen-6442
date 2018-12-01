package com.soen.risk.boundary;

import java.util.logging.Logger;

/**
 * The Interface Usecase.
 * 
 * All use cases are high level user functionalities which are implemented using the following 
 * layers:
 *  1. Request
 *  2. Response
 *  3. Interactor
 *  4. Entity
 *
 * @param <T> the response type
 */
public interface Usecase<T> {
    
    /** The logger. */
    static Logger logger = Logger.getLogger(Usecase.class.getName());

    /**
     * Execute the use case.
     *
     * @return the t
     */
    public T execute();

}
