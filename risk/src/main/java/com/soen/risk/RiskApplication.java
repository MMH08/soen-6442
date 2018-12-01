/*
 * SOEN - 6441 - Risk game
 */
package com.soen.risk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

/**
 * The Class RiskApplication.
 */
@SpringBootApplication
public class RiskApplication {

    private static Logger logger;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RiskApplication.class, args);
    }
}
