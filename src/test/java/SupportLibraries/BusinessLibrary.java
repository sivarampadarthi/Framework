package SupportLibraries;

public class BusinessLibrary extends TechnicalLibrary{
	
	public void browserLaunch() {
		try {
			
			startBrowser(Util.getProperty("browser"));
			Reports.passTest("*********URL Open**********");
			
		}catch(Exception e) {
			Reports.failTest("Unable to login to Application");
		}
	}

}
