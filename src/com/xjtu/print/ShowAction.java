package com.xjtu.print;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xjtu.thread.QuaryGradeThread;
import com.xjtu.util.DBUtil;
import com.xjtu.util.Table;
import com.xjtu.util.Util;

public class ShowAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	static ExecutorService executor;
	
    static{
   	 executor = Executors.newCachedThreadPool();
   }
	private String TABLE_GRADE = "clainfo";
    private HttpServletRequest request;
    private HttpServletResponse response;
    
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String act = request.getParameter("action");
		if("getData".equals(act)){

			// 获取参数
			QuaryGradeThread qthread = new QuaryGradeThread(request, response);
			 executor.execute(qthread);
			 while(!qthread.isTerm){
				 Thread.sleep(100);
			 }
			// 输出返回结果
		}
		return "success";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
    
    
}
