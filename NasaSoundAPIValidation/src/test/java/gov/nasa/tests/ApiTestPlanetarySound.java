/**
 * 
 */
package gov.nasa.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import gov.nasa.generics.ConnectToApi;

/**
 * @author Krishnendu Daripa
 * This Test class tests all the valid and invalid scenarios for the Planetary sound API 
 *
 */
public class ApiTestPlanetarySound 
{
	Properties curl = new Properties(); 
	String inputString = "";
	int responseCode = 0;
	FileInputStream curlFile = null;
	{
		try {
			curlFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\gov\\nasa\\apis\\soundsapicalls.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			curl.load(curlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	//This test tests Planetory sound URI with all valid parameters for q, limit, and api_key.
	@Test(priority=1)
	public void checkPlanatorySoundResponse()
	{
		int responseCode = ConnectToApi.ReadResponseData(curl.getProperty("PlanetarySoundsValid"));
		if (responseCode>=200&& responseCode<=299)
		{
			System.out.println("Test Cases is Passed with expected statuscode: " + responseCode);
		}
		else
		{
			Assert.fail("Test cases is Failed. statuscode: "+ responseCode);
		}
			
	}
	//This test tests the parameter with invalid api_key
	@Test(priority=2)
	public void checkPlanatorySoundResponseForInvalidKey()
	{
		int responseCode = ConnectToApi.ReadResponseData(curl.getProperty("PlanetarySoundsInvalidKey"));
		if (!(responseCode>=1&& responseCode<=399))
		{
			System.out.println("Test cases is Passed with expected statuscode: " + responseCode);
		}
		else
		{
			Assert.fail("Test cases is Failed with statuscode: "+ responseCode);
		}
			
	}
	//This test tests the parameter with invalid negative limit
	@Test(priority=3)
	public void checkPlanatorySoundResponseForInvalidLimit()
	{
		int responseCode = ConnectToApi.ReadResponseData(curl.getProperty("PlanetarySoundsInvalidLimitNegative"));
		if (!(responseCode>=1&& responseCode<=399))
		{
			System.out.println("Test cases is Passed with expected statuscode: " + responseCode);
		}
		else
		{
			Assert.fail("Test cases is Failed with statuscode: "+ responseCode);
		}
			
	}

	//This test tests the parameter with invalid limit character instead of an integer
	@Test(priority=4)
	public void checkPlanatorySoundResponseForInvalidLimitChar()
	{
		int responseCode = ConnectToApi.ReadResponseData(curl.getProperty("PlanetarySoundsInvalidLimitChar"));
		if (!(responseCode>=1&& responseCode<=399))
		{
			System.out.println("Test cases is Passed with expected statuscode: " + responseCode);
		}
		else
		{
			Assert.fail("Test cases is Failed with statuscode: "+ responseCode);
		}
			
	}
}
