package SupportLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

import TestSetup.BaseClass;

public class Util extends BaseClass {
	
	public static String strFileName = "config.properties";
	static Properties props = new Properties();
	static String strValue;

	/**
	 * This method will return CurrentDate with Hours, Seconds
	 * 
	 * @return
	 */

	public static String dateTimeString() {

		Util.hardWait(1000);
		int Month = LocalDateTime.now().getMonthValue();
		int Day = LocalDateTime.now().getDayOfMonth();
		int Year = LocalDateTime.now().getYear();
		int Hour = LocalDateTime.now().getHour();
		int Minutes = LocalDateTime.now().getMinute();
		int Seconds = LocalDateTime.now().getSecond();

		final String currentDtTime = String.valueOf(Month) + String.valueOf(Day) + String.valueOf(Year)
				+ String.valueOf(Hour) + String.valueOf(Minutes) + String.valueOf(Seconds);

		return currentDtTime;

	}

	/**
	 * This is the method to wait for Synchronization
	 * 
	 * @param milliSec
	 */

	public static void hardWait(int milliSec) {

		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/**
	 * This method will return currentDate
	 * @return
	 */

	public static String dateString_MM_DD_YYYY() {

		int Month = LocalDateTime.now().getMonthValue();
		int Date = LocalDateTime.now().getDayOfMonth();
		int Year = LocalDateTime.now().getYear();

		final String currentDate = String.valueOf(Month) + "_" + String.valueOf(Date) + "_" + String.valueOf(Year);

		return currentDate;
	}
	
	/**
	 * This method will check for folder created in specified path or it will create new folder in the specified path
	 * @param folderPath - Folder location
	 * @return
	 */
	
	public static boolean createFolder(String folderPath) {
			boolean result = false;
	
		try {
			
			File directory = new File(folderPath);
			if(!directory.exists()) {
				result = directory.mkdir();
			}
		}catch(Exception e) {
			System.out.println("Error while creating the foler in Location " + folderPath + "error Message" + e.getMessage());
		}
		
		return result;
	}
	
	public static Properties readProperty() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			
			input = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			prop.load(input);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static boolean isNull_Empty_WhiteSpace(String CmpVal) {
		try {
			if(CmpVal == null) {
				return true;	
			}else {
			CmpVal = CmpVal.replaceAll("\u00a0", "");
			CmpVal = CmpVal.replaceAll("&nbsp", "").trim();
			}
		} catch (Exception e) {
			System.out.println("Error occured isNull_Empty_WhiteSpace " + e.getMessage());
		}
		if (CmpVal.trim() != "" && CmpVal != null && (CmpVal.isEmpty()) == false) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Function to read config parameter from Configuration file.
	 * 
	 * @param strKey
	 *            - Configuration name
	 * @return - string value with configuration name, returns null in case
	 *         configuration parameter not found. @ in case of error.
	 * @throws Exception 
	 */
	public static String getProperty(String strKey) throws Exception {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				strValue = props.getProperty(strKey);
				in.close();
			} else
				throw new Exception("Configuration File not found.");
		} catch (Exception e) {
			throw new Exception("Unknown Error encountered while reading " + strKey
					+ " from configuration file. ---" + e.getClass() + "---" + e.getMessage());
		}
		if (strValue != null) {
			return strValue;
		} else {

			throw new Exception(
					"Value '" + strKey + "' not configured in config file. Contact automation team");
		}

	}
}
