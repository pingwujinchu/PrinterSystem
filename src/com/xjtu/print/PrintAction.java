package com.xjtu.print;

import java.io.File;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.xjtu.bean.Course;
import com.xjtu.bean.PrintJob;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;
import com.xjtu.thread.PrintThread;
import com.xjtu.util.DBUtil;
import com.xjtu.util.Util;

public class PrintAction implements Action,ServletRequestAware,ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public static Queue jobList;
	public static int totalNum = 0;
    
	static{
		 jobList = new LinkedList<PrintJob>();
	  	
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		User user = (User)(request.getServletContext().getAttribute("user"));
		List gradeList = this.getGradeList(user);
		PrintJob pj = new PrintJob(user,gradeList,new Date());
		synchronized(jobList) {
		if(PrintThread.state == 0){
		jobList.add(pj);
//		}
		Map <String ,String> value = new HashMap();
		 java.sql.Timestamp st = new java.sql.Timestamp(pj.getTime().getTime());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String datestr = sdf.format(st);
		value.put("state", "'正在等待'");
		value.put("jid", "'"+pj.getJobID()+"'");
		value.put("filename", "'"+pj.getUser().getStudentId()+".pdf'");
		value.put("time","'"+datestr+"'");
		value.put("id", "'"+user.getStudentId()+"'");
		DBUtil.insert(DBUtil.getConnection(), "printjob", value);
		}
		
//		executor.execute(pthread);
		JSONObject json = new JSONObject();
		if(PrintThread.state ==0){
			int size = 0;
			if(jobList.size()>=1){
				size = (jobList.size()-1);
			}
			json.put("tip", "任务已经提交到服务器，正在等待将文件发送到打印机，\n您前面尚有"+size+"个打印任务等待使用打印机。\n"+"您可以点击状态查看");
		}else{
			json.put("tip", "网络故障请您稍后再打印。");
		}
        Util.writeJsonToResponse(writer, json);
//		User user = (User) request.getServletContext().getAttribute("user");
		writer.flush();
		writer.close();
		
//		String fileName = "webapps/PrinterSystem/temp/"+user.getStudentId()+".pdf";
//		File f = new File(fileName);
//		f.delete();
		jobList.notifyAll();
		TestAction.printExecutor.notifyAll();
		}
		return "success";
	}
	
	private List getGradeList(User user){
		List gradeList = new ArrayList();
		JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
		JSONArray data =  (JSONArray) params.get("data");
		for(int i = 0 ; i < data.size() ; i++){
			JSONObject termi = (JSONObject) data.get(i);
			String term = termi.getString("value");
//			System.out.println(term);
			JSONArray termdata = termi.getJSONArray("data");
			Record record = new Record();
			List<Course> courseList = new ArrayList<Course>();
			record.setSemester(term);
			double takenCredit = 0;
			double deservedCredit = 0;
			double deservedPoint = 0;
			for(int j = 0 ; j < termdata.size() ; j++){
				JSONObject classdata = (JSONObject) termdata.get(j);
				String class_attr = classdata.getString("class_attr");
				String class_name = classdata.getString("class_name");
				double credit = classdata.getDoubleValue("credit");
				double grade = classdata.getDoubleValue("grade");
				double point = classdata.getDoubleValue("point");
				double dcredit = classdata.getDoubleValue("deservedCredit");
				takenCredit += credit;
				deservedPoint += point;
				deservedCredit += dcredit;
				Course course = new Course(class_attr,class_name,credit,grade,point,dcredit);
				courseList.add(course);
			}
			record.setClasses(courseList);
			record.setDeservedCredit(deservedCredit);
			record.setTakenCredit(takenCredit);
			record.setDeservedPoint(deservedPoint);
			gradeList.add(record);
		}
		return gradeList;
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
