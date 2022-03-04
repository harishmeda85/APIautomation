package com.DC.API.Tests;

import java.io.File;

public class Ctests {

public static void main(String[] args) {
	
	
	
	File file = new File(System.getProperty("user.dir") + "\\response.json");

	if (file.delete()) {
		System.out.println("response.json file deleted successfully");
	} else {
		System.out.println("Failed to delete the response.json file");
	}
}
	
}
	


