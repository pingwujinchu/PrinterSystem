package com.xjtu.print;

import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.xjtu.thread.PrintThread;

public class TestAction implements Action,ServletRequestAware,ServletResponseAware{
	private ServletRequest request;
	private ServletResponse response;
	static PrintThread printThread1 = new PrintThread();
	static PrintThread printThread2 = new PrintThread();
	static ExecutorService printExecutor;
	
	static{
		printExecutor = Executors.newFixedThreadPool(1);
		printExecutor.execute(printThread1);
		printExecutor.execute(printThread2);
	}
    
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		String result= "UserName = 123456,PWD=45678";
//		OutputStream os = response.getOutputStream();
//		os.write(result.getBytes());
//		request.getRequestDispatcher("UI2/index.html").forward(request, response); 
		return "success";
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
