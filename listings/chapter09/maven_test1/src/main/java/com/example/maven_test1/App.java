package com.example.maven_test1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world with logging
 *
 */
public class App {
    private static final Logger LOG = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("Will print 'Hello World!'");
        System.out.println( "Hello World!" );
        LOG.info("Finished printing 'Hello World!'");
    }
}
