package com.xjtu.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class processTest {
	public static void main(String[]args) throws InterruptedException{
		String command = "taskkill /f /im notepad.exe";  
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
