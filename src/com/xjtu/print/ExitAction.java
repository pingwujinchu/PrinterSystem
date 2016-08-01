package com.xjtu.print;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;

public class ExitAction  implements Action,ServletContextAware,ServletRequestAware{
    private ServletContext context;
	private ServletRequest request;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		context.removeAttribute("user");
		request.removeAttribute("user");
		request.removeAttribute("userName");
		request.removeAttribute("id");
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
		request = arg0;
	}

}
