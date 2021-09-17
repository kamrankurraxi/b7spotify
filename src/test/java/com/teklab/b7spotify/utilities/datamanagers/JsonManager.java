package com.teklab.b7spotify.utilities.datamanagers;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;

public class JsonManager {

	//  property
	private String filePath = "";
	
	// constructor 
	public JsonManager(String fp) {
		filePath = fp;
	}
	// Method 
	//Step 1:open 	the json file and read its content 
	//Steo 2:return the content as a String
	
	private String jsonToString() {
		String stringform = null;
		try {
			JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader (filePath));
			JSONObject jsonObj = (JSONObject)object;
			stringform = (String)jsonObj.toJSONString();
			return stringform;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringform;
	}
	/**
	 * Enter your json path query to extract the user related data 
	 * 
	 * @param qurey
	 * @return string
	 */
	 
	public String data (String query ) {//public String executeQuery(String query)
		String jsonStr =jsonToString();
		return JsonPath.read(jsonStr,query);
	}
	
}
