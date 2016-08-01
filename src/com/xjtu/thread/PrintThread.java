package com.xjtu.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.DocumentException;
import com.xjtu.bean.Course;
import com.xjtu.bean.PrintJob;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;
import com.xjtu.print.PrintAction;
import com.xjtu.util.DBUtil;
import com.xjtu.util.PdfBuilt;
import com.xjtu.util.Printer;
import com.xjtu.util.Util;

public class PrintThread implements Runnable{
	private User user;
	public  boolean isTerm = false;
	public int exitValue = 0;
	public static int state =0;
	private static String total;       //等待系统响应的总时间
	private static String wait;         //每次循环判断界面是否出现的时间
	private static String load;           //等待页面显示完全的时间
	private static String waitn;         //等待“不在显示”窗口的时间
	private static String waitnc;           //等待“无法连接窗口”的时间
	private static String waitle;             //查询登录失败窗口的总时间
	private static String waitlec;               //查询登录失败窗口每次的循环时间
    
	static{
		Properties pro = new Properties();
		File file= new File("webapps/PrinterSystem/config/config.properties");
        Properties props=new Properties(); 
        try{
        InputStream is = new FileInputStream(file);
        props.load(is);
        is.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        total =  props.getProperty("total");
        wait = 	 props.getProperty("wait");
        load = props.getProperty("load");
        waitn = props.getProperty("waitn");
        waitnc = props.getProperty("waitnc");
        waitle = props.getProperty("waitle");
        waitlec = props.getProperty("waitlec");

	}
	
	public PrintThread() {
		super();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
     		while(true){
			if(PrintAction.jobList.size()>0){
				PrintJob pj ;
				synchronized(PrintAction.jobList) {
				     pj  = (PrintJob) PrintAction.jobList.poll();
				}
				print(pj);
				if(exitValue==2||exitValue==3||exitValue==4){
					print(pj);
					if(exitValue ==2||exitValue == 3||exitValue == 4){
						Map newValue = new HashMap();
						Map condition = new HashMap();
						newValue.put("state", "'打印失败'");
						condition.put("jid", "'"+pj.getJobID()+"'");
						DBUtil.changValue(DBUtil.getConnection(), "printjob", newValue, condition);
						state = 0;
					}
				}
				if(exitValue == 1){
					try {
						state = 1;
						Map newValue = new HashMap();
						Map condition = new HashMap();
						newValue.put("state", "'打印失败'");
						condition.put("jid", "'"+pj.getJobID()+"'");
						DBUtil.changValue(DBUtil.getConnection(), "printjob", newValue, condition);
						Thread.sleep(1000);
						state = 0;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						isTerm = true;
					}
				}
			}else{
				try {
//					System.out.println(PrintAction.jobList.size());
					synchronized(PrintAction.jobList) {
					PrintAction.jobList.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isTerm = true;
				}
			}
		}
		
	}
	
	public  void print(PrintJob pj ){
		try{
		Process process = this.closeThread("UniOPMClient.exe");
		while(process.isAlive()){
			Thread.sleep(10);
		}
		user = pj.getUser();
		PdfBuilt.BuiltPdf(user,pj.getGradeList());
		String filePath = "webapps/PrinterSystem/temp/"+user.getStudentId()+".pdf";
		File f = new File(filePath);
		Printer printer = new Printer(f.getAbsolutePath());
		printer.doPrint(f.getAbsolutePath());
		Runtime rn = Runtime.getRuntime();
		Process pro = null;
	
		String command = "login.exe "+user.getStudentId()+" "+user.getStudentId()+" "+total+" "+wait+" "+load+" "+waitn+" "+waitnc+" "+waitle+" "+waitlec;
//		String command= "login.exe \"3115034020\" \"111111\" 3000 10 500 100 100 1000 10";
		pro = rn.exec(command);
          int i = 0;
          
          while(pro.isAlive()){
        	  Thread.sleep(10);
          }
          exitValue = pro.exitValue();
		}catch(Exception e){
			exitValue = 4;
		}
		
		if(exitValue == 0){
			state = 0;
			Map newValue = new HashMap();
			Map condition = new HashMap();
			newValue.put("state", "'打印成功'");
			condition.put("jid", "'"+pj.getJobID()+"'");
			DBUtil.changValue(DBUtil.getConnection(), "printjob", newValue, condition);
			String fileName = "webapps/PrinterSystem/temp/"+user.getStudentId()+".pdf";
			File f = new File(fileName);
			f.delete();
		}
	}
	
	private Process closeThread(String threadName){
		String command = "taskkill /f /im "+threadName;  
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return process;
	}
	
}
