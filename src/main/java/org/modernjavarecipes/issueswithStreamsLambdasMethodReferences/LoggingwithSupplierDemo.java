package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoggingwithSupplierDemo {
    private static Logger logger = Logger.getLogger(this.getClass().getName());
    public static void main(String[] args) {
        //Using a Supplier in the info method
        List<String> data = new ArrayList<>();
        // ... populate list with data ...
        logger.info("The data is " + data.toString());
        logger.info(() -> "The data is " + data.toString());
    }
}
