package com.xjtu.print;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.xjtu.bean.Course;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;
import com.xjtu.util.PdfBuilt;
import com.xjtu.util.Util;

public class SaveAction implements Action,ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user = (User) request.getServletContext().getAttribute("user");
		File dir = new File("webapps/PrinterSystem/temp/");
		if(!dir.exists()){
			dir.mkdirs();
		}
		String fileName = "webapps/PrinterSystem/temp/"+user.getStudentId()+".pdf";
		File f = new File(fileName);
		String url = request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/")).substring(0,request.getRequestURL().lastIndexOf("/"))+"/temp/"+user.getStudentId()+".pdf";
		
			user = (User)(request.getServletContext().getAttribute("user"));
			List gradeList = new ArrayList();
			JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
			JSONArray data =  (JSONArray) params.get("data");
			for(int i = 0 ; i < data.size() ; i++){
				JSONObject termi = (JSONObject) data.get(i);
				String term = termi.getString("value");
//				System.out.println(term);
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
				System.out.println(deservedCredit);
				gradeList.add(record);
		}
			PdfBuilt.BuiltPdf(user,gradeList);
		
		JSONObject json = new JSONObject();
		json.put("url", url);
		Writer writer =response.getWriter();
		Util.writeJsonToResponse(writer, json);
	    writer.flush();
	    writer.close();
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
