package com.teklab.b7spotify.utilities.reportManagers;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jManager {
	
	
	public static Logger log =  LogManager.getLogger(Log4jManager.class);
	
	/**
	 * Logs info text message to the log file & Extent report
	 * 
	 * @param message
	 */
     public static void info(String message) {
    	 log.info(message);
     }
     
     /**
      * Logs debug text message to log file
      * 
      * @parama message
      */
     
    public static void debug(String message) {
    	log.debug( message);
    }
    /**
     * Logs error text message to the log file
     * 
     * @param message
     */
    public static void error(String message) {
    	log.error( message);
    }
    public static void warn (String message) {
    	log.warn(message);
    }
    public static void fatal (String message) {
    	log.fatal(message);
    }
    //this is to print log for the beginning of the test case , as we usually run so many test case as a test suit 
    public static void starTestCase (String sTestCaseName) {
    	log.info("*************************************************");
    	log.info("$$$$$$$$$$$$$$$           "  + sTestCaseName   + "      $$$$$$$$$$$$$$$$$$$$$$$$$");
    	log.info("*************************************************");
    	
    }
    
    //this is to print log for  the ending of the test case
    public static void endTestCase () {
    	log.info("XXXXXXXXXXXXXXX         "  +  "----END of test case  " + "               XXXXXXXXXXXXXX");
    	log.info("X");
    	log.info("X");
    	
    }

	
    
}
