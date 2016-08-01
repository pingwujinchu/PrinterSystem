package com.xjtu.test;

import java.io.IOException;

import com.xjtu.util.Printer;

public class PrinterTest {
		public static void main(String []args) throws InterruptedException, IOException{
			Printer.doPrint("f:/cs229-cvxopt.pdf");
			Thread.sleep(2000);
			Process pro = null;
//			  String command = "D:/soft/tomcat9/login.exe "+"fan"+" 111111";
			  Runtime rn = Runtime.getRuntime();
//            try {
//				pro = rn.exec(command);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            int i = 0;
//            while(pro.isAlive()&&i<40){
//          	  Thread.sleep(10);
//          	  i++;
//            }
			  
			  String command= "D:/soft/tomcat9/login.exe \"3115034020\" \"111111\" 4000 2000";
				pro = rn.exec(command);
		          int i = 0;
		          while(pro.isAlive()){
		        	  Thread.sleep(10);
		          }
		          System.out.println(pro.exitValue());

		}
}
