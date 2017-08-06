/**
 * 
 */
package gov.nasa.generics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * @author Krishnendu Daripa
 * This class connects to the API URI's with parameters to get the response
 *
 */
public class ConnectToApi 
{

	public static int ReadResponseData(String uri) 
	{
		Properties curl = new Properties(); 
		String inputString = "";
		int responseCode = 0;
		FileInputStream curlFile = null;
		try 
		{
			curlFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\gov\\nasa\\apis\\soundsapicalls.properties");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			curl.load(curlFile);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}

		try
		{
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			responseCode = conn.getResponseCode();
			System.out.println(responseCode + conn.getResponseMessage());
			if (conn.getResponseCode() == 200) 
			{
				Scanner scan = new Scanner(url.openStream());
				String entireResponse = new String();
				while (scan.hasNext())
					entireResponse += scan.nextLine();
				System.out.println("Response : " + entireResponse);
				scan.close();

				JSONObject obj = new JSONObject(entireResponse );

				JSONArray arr = obj.getJSONArray("results");
				for (int i = 0; i < arr.length(); i++) {
				}

			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return responseCode;
	}



}
