package com.xjtu.print;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.xjtu.util.DBUtil;
import com.xjtu.util.Table;
import com.xjtu.util.Util;

public class StateAction implements Action,ServletRequestAware,ServletResponseAware{
	
    private HttpServletRequest request;
    private HttpServletResponse response;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		quaryJob();
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
	
	private void quaryJob() throws SQLException{
		JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
        String userID = params.getString("id");
        response.setContentType("text/html;charset=UTF-8");
        Writer writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 获取参数
		Object columns = params.get("columns");
		Integer limit = params.getInteger("limit");
		Integer offset = params.getInteger("offset");
		
		Table table = null;
		Connection conn = DBUtil.getConnection();
		List<String> filters = new ArrayList<String>();
		List<Object> sqlParams = new ArrayList<Object>();
		filters.add("id = ?");
		sqlParams.add(userID);
		try {
			table = Util.queryData(conn, "printjob", columns, filters, "time ASC",sqlParams, offset, limit);
//			System.out.println(table.getTotal());
		}catch(Exception e){
			e.printStackTrace();
		}
			finally {
			conn.close();
		}
		try {
			Util.writeTableToResponse(writer, table);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
