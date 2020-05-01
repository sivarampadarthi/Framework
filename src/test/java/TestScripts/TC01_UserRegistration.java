package TestScripts;

import org.testng.annotations.Test;

import SupportLibraries.BusinessLibrary;
import SupportLibraries.Util;



public class TC01_UserRegistration extends BusinessLibrary{
	
	@Test
	public void registration() {
		try {
			browserLaunch();
			navigateToUrl(Util.getProperty("URL"));
			System.out.println("URL Launched");
			
			
		}catch(Exception e) {
			System.out.println("Unable to register the user "+e.getMessage());
		}
	}
	
	

}
