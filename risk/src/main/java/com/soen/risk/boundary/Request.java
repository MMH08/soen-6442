package com.soen.risk.boundary;

import java.util.logging.Logger;

/**
 * The Interface Request.
 * 
 * The implementation of request is responsible to convert the HTML based input string
 * into Java data structures which are consumed by the use case implementation layer.
 */
public interface Request {
    
    /** The logger. */
    Logger logger = Logger.getLogger(Request.class.getName());

}
