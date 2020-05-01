package SupportLibraries;

import TestSetup.BaseClass;

public class TechnicalLibrary extends BaseClass{
	
	
	public static void navigateToUrl(String url) {
		try {
			
			if(url != "" && Util.isNull_Empty_WhiteSpace(url) == false) {
				driver.get(url);
				Reports.passTest("URL launched successfully :" + url);
			}
			
		}catch(Exception e) {
			Reports.failTest("Unable to navigate To URL" + url);
			System.out.println("Unable to navigate to URL" + e.getMessage());
		}
	}
	
	

}
