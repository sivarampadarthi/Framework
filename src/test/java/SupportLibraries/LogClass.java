package SupportLibraries;

import org.testng.log4testng.Logger;

public class LogClass {

	public static Logger log = Logger.getLogger(LogClass.class);
	public static String filename;

	
	/**
	 * This method is going to log the log at each test case beginning as we usually execute test cases as batch
	 * @param testCaseName
	 */
	public static void startTestCase(String testCaseName) {

		log.info("*******************************************************");
		log.info("************* " + testCaseName + "**********************");
		log.info("*******************************************************");
	}
	
	/**
	 * This method is going to log the log at each test case ending as we usually execute test cases as batch
	 * @param testCaseName
	 */

	public static void endTestCase(String testCaseName) {
		log.info("*****" + "E--N--D" + "**********");
		log.info("X");
		log.info("X");
		log.info("X");

	}

	/**
	 * This method will log the info log
	 * @param message
	 */
	public static void info(String message) {
		log.info(message);
	}

	/**
	 * This method will log the warning message in Logs
	 * @param message
	 */
	public static void warn(String message) {
		log.warn(message);
		log.warn("Warning in file :" + Thread.currentThread().getStackTrace()[1].getFileName() + "At Line no :"
				+ Thread.currentThread().getStackTrace()[1].getLineNumber());
	}
	
	/**
	 * This method will log Error message in Logs
	 * @param message
	 */

	public static void error(String message) {
		log.error(message);
		log.error("Error in file :" + Thread.currentThread().getStackTrace()[1].getFileName() + "At Line no :"
				+ Thread.currentThread().getStackTrace()[1].getLineNumber());
	}
	
	/**
	 * This method will log Fatal messages in Logs
	 * @param message
	 */
	
	public static void fatal(String message) {
		log.fatal(message);
		log.fatal("Fatal in file :" + Thread.currentThread().getStackTrace()[1].getFileName() + "At Line no :"
				+ Thread.currentThread().getStackTrace()[1].getLineNumber());
	}
	
	/**
	 * This method will log debug message
	 * @param message
	 */
	public static void debug(String message) {
		log.debug(message);
	}
}
