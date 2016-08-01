package com.xjtu.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTester {  
    private static Logger logger = LogManager.getLogger("HelloLog4j");  
    public static void main(String[] args) {  
         
       
        logger.info("Hello, World!");   
        logger.error("Hello, World!");  
   
    }  
}  