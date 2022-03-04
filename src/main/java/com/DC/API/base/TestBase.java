package com.DC.API.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestBase {

	public static String bearerToken = "3e3689d1c642d0e792ef3fdb8dadd611ae506efd67c54c2235e635f71722cc2a";



	public static String baseURI = "https://gorest.co.in/public/v2/users";
	public static String basePath = "/users";
	public static String post_baseURI = "https://gorest.co.in/public-api/users";



	public static void jsonFileWriter(String responseBody) throws IOException {

		File fileObj = new File(System.getProperty("user.dir") + "\\response.json");

		if (fileObj.createNewFile()) {

			FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "\\response.json");
			myWriter.write(responseBody);
			myWriter.close();

		} else {
			System.out.println("Failed");

		}

	}

	public static JSONObject jsonFileReader() throws Exception {
		String absolutePath = System.getProperty("user.dir") + "\\response.json";
		JSONObject jsonObject = (JSONObject) readJsonFile(absolutePath);
		return jsonObject;

	}


	public static Object readJsonFile(String filename) throws Exception {

		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}

	public static void deleteJsonFile() throws Exception{

		File file = new File(System.getProperty("user.dir") + "\\response.json");
try {
		if (file.delete()) {
			System.out.println("response.json file deleted successfully");
		} else {
			System.out.println("Failed to delete the response.json file");
		}
	}
catch(Exception e) {
	
e.getMessage();
}
}

}
