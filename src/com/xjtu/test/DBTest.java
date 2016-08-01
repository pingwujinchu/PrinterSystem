package com.xjtu.test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.xjtu.bean.User;
import com.xjtu.thread.LoginThread;
import com.xjtu.util.DBUtil;
import com.xjtu.util.Table;
import com.xjtu.util.Util;

public class DBTest {
   public static void main(String []args) throws UnsupportedEncodingException{
	   ExecutorService loginExecutor = Executors.newCachedThreadPool();
	   User user = new User();
	   LoginThread lt = new LoginThread("15610401150916","12345");
	   loginExecutor.execute(lt);
	   try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   System.out.println(new String(lt.getUser().getUserName().getBytes("utf-8")));
   }
}
