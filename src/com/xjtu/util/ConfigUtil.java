package com.xjtu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigUtil {
	private static String dbUrl = "";
	private static String location = "";
	private static String total;
	
	public static String getConfig(String str){
		File file= new File("./webapps/PrinterSystem/config/config.properties");
        Properties props=new Properties(); 
        try{
        InputStream is = new FileInputStream(file);
        props.load(is);
        is.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        if(!props.isEmpty()){
        	dbUrl = props.getProperty("url");
        	location = props.getProperty("location");
        
        }
        System.out.println(dbUrl+"\n"+location);
		return "";
	}
	
	
	public static String getUrl() {
		return dbUrl;
	}


	public static void setUrl(String url) {
		ConfigUtil.dbUrl = url;
	}


	public static void main(String []args){
		ConfigUtil.getConfig("");
	}

}
