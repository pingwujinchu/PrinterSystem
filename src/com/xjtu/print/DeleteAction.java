package com.xjtu.print;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.xjtu.bean.PrintJob;
import com.xjtu.util.DBUtil;

public class DeleteAction implements Action,ServletRequestAware,ServletResponseAware{

    private HttpServletRequest request;
    private HttpServletResponse response;
    
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String act = request.getParameter("action");
		if("deleteList".equals(act)){
			JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
			String jobID = params.getString("jobID");
			this.deleteFromList(jobID);
		}else if("deleteRecord".equals(act)){
			JSONObject params = (JSONObject) JSONObject.parse(request.getParameter("params"));
			JSONArray data =  (JSONArray) params.get("data");
			for(int i = 0 ; i < data.size() ; i++){
				JSONObject job = (JSONObject) data.get(i);
				String jid = job.getString("value");
				this.deleteFromList(jid);
			}
		}
		return "success";
	}
	
	private void deleteFromList(String jobID){
		Queue q = PrintAction.jobList;
		Iterator it = q.iterator();
		while(it.hasNext()){
			PrintJob pj = (PrintJob) it.next();
			if(pj.getJobID().equals(jobID)){
				q.remove(pj);
				break;
			}
		}
		Map condition = new HashMap();
		condition.put("jid", "'"+jobID+"'");
	    DBUtil.deleteWithCondition(DBUtil.getConnection(), "printjob", condition);
	}
	
//	private void deleteFromRecord(String jobID){
//		Map condition = new HashMap();
//		condition.put("jid", "'"+jobID+"'");
//	    DBUtil.deleteWithCondition(DBUtil.getConnection(), "printjob", condition);
//	}

}
