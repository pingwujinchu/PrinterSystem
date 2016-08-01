package com.xjtu.thread;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;
import com.xjtu.util.DBUtil;
import com.xjtu.util.EUUserUtil;
import com.xjtu.util.Row;
import com.xjtu.util.Table;
import com.xjtu.util.UserUtil;
import com.xjtu.util.Util;

public class QuaryGradeThread implements Runnable{
      private HttpServletRequest request;
      private HttpServletResponse response;
      public boolean isTerm = false;
      private static Logger logger = LogManager.getLogger("HelloLog4j");  
      

	public QuaryGradeThread(HttpServletRequest request,HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.quaryData(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("+++++++++++++++++++++++++++"+"quaryFinished");
		isTerm = true;
	}
      
	public void quaryData(ServletRequest request, ServletResponse response) throws SQLException, NamingException{
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
//		Connection conn = DBUtil.getConnection();
//		List<String> filters = new ArrayList<String>();
//		List<Object> sqlParams = new ArrayList<Object>();
//		filters.add("id = ?");
//		sqlParams.add(userID);
//		try {
//			table = Util.queryData(conn, "clainfo", columns, filters, "fid ASC",sqlParams, offset, limit);
//		} finally {
//			conn.close();
//		}
		List<Record> gradeList = EUUserUtil.getGrades(userID);
		table = Util.getTableFromObject(gradeList, columns, offset, limit);
//		List rl = table.getRows();
//		for(int i = 0 ; i <rl.size() ; i++){
//			Row r = (Row) rl.get(i);
//			System.out.println(" "+r.getDate("time")+r.getInteger("fID")+r.getString("class_name"));
//		}
		// 输出返回结果
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
