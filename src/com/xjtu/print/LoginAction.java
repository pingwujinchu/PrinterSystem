package com.xjtu.print;

import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.xjtu.bean.User;
import com.xjtu.thread.LoginThread;
import com.xjtu.util.Util;

public class LoginAction implements Action,ServletContextAware,ServletRequestAware,ServletResponseAware,SessionAware{
	private ServletContext context;
	private ServletRequest request;
	private ServletResponse response;
	private Map<String, Object> session;
	static ExecutorService loginExecutor;
	private String userName;
	private String password;
	static{
		 loginExecutor = Executors.newCachedThreadPool();
	}
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	        // 只有用户名为wwfy，密码为123456方可成功登陆
		    request.setCharacterEncoding("utf-8");
		    response.setCharacterEncoding("utf-8");
		    JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
		    userName = params.getString("userName");
		    password = params.getString("password");
		    Writer writer = response.getWriter();
		    response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		    if(context.getAttribute("user")!=null){
		    	JSONObject json = new JSONObject();json.put("tip", "success");
		    	json.put("name", ((User)context.getAttribute("user")).getUserName());
				Util.writeJsonToResponse(writer, json);
		    }else{
			LoginThread lt = new LoginThread(userName,password);
			loginExecutor.execute(lt);
			while(!lt.isTerm){
				Thread.sleep(100);
			}
			User user = lt.getUser();
			
			if(user == null||user.getStudentId() == null){
				JSONObject json = new JSONObject();
				json.put("tip", "1");
				
				Util.writeJsonToResponse(writer, json);
			}
			else if(password.equals(lt.getUser().getIdCard().substring(lt.getUser().getIdCard().length()-6, lt.getUser().getIdCard().length()))){
			     context.setAttribute("user", lt.getUser());
			     request.setAttribute("userName", lt.getUser().getUserName());
			     request.setAttribute("id", lt.getUser().getStudentId());
					JSONObject json = new JSONObject();json.put("tip", "success");
					json.put("name", user.getUserName());
					Util.writeJsonToResponse(writer, json);
			}else{
				JSONObject json = new JSONObject();
				json.put("tip", "2");
				Util.writeJsonToResponse(writer, json);
			}
		    }
		    writer.flush();
		    writer.close();
			return "success";
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		context = arg0;
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
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session = arg0;
	}
    
}
