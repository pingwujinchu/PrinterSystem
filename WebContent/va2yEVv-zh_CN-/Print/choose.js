define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var Baas = require('$UI/system/lib/baas');
	
	var labels = [];
	var terms =[];
	var type;
	var Model = function(){
		this.callParent();
	};
	
	var record = function(name,attr,cred,grad,pt,tim,desc){
		var class_name = name;
		var class_attr = attr;
		var credit = cred;
		var grade = grad;
		var point = pt;
		var time = tim;
		var deservedCredit = desc;
		
		this.getName = function(){
		
		  return class_name;
		
		}
		
		this.getAttr = function(){
		   return class_attr;
		}
		
		this.getCredit = function(){
		  return credit;
		}
		
		this.getGrade = function(){
		   return grade;
		}
		
		this.getTime = function(){
		
		   return time;
		}
		
		this.getPoint = function(){
		  return point;		
		}
		
		this.getDeservedCredit = function(){
		  return deservedCredit;
		}
		
	};

	Model.prototype.result = function(){
		//这里实现返回的逻辑
		var termdata = this.comp("termData");
		var params;
		var row = [];
		var index = 0;
		var isAll = true;
		var jsonstr ="{term:[";
	    
		termdata.each(function(options){
			if (termdata.getValue("calButton", options.row)){
				row.push(index);
			}else{
			    isAll = false;
			}
			index++;
		});
		
		for(var rindex in row){
	        jsonstr+="{value:'"+labels[row[rindex]]+"',data:[";
	        for(var dataindex in terms[row[rindex]]){
	             var temRecord = terms[row[rindex]][dataindex];
	             jsonstr+="{class_attr:'"+temRecord.getAttr()+"',";
	             jsonstr+="class_name:'"+temRecord.getName()+"',";
	             jsonstr+="credit:"+temRecord.getCredit()+",";
	             jsonstr+="grade:"+temRecord.getGrade()+",";
	             jsonstr+="point:"+temRecord.getPoint()+",";
	             jsonstr+="deservedCredit:"+temRecord.getDeservedCredit()+",";
	             jsonstr+="time:'"+temRecord.getTime()+"'}";
	             if(dataindex<terms[row[rindex]].length-1){
	             jsonstr+=",";
	             }
	        }
	        jsonstr+= "]}";
	        if(rindex < row.length-1){
	           jsonstr+=",";
	        }
	    }
	    jsonstr+="]}";
	    var jsonobj = eval(jsonstr);
	    params = {'data' : jsonobj};
	    if(type == 'print'){
		var success = function(resultData) {
		    var tip = resultData.tip;
//		    if(exitValue == 0){
//		    	justep.Util.hint("打印任务提交成功！");
//		    }else{
//		    	justep.Util.hint("打印任务提交失败，请重试！");
//		    }
		    justep.Util.hint(tip);
		};
		Baas.sendRequest({
			"url" : "/print",  //要和web.xml中的配置一致
			"action":"print",
			"params" :params,
			"success" : success
		});
		}else if(type == 'save'){
		var success = function(resultData) {
		    var url = resultData.url;
		    window.open(url);
			justep.Util.hint("文件保存成功");
		};
		Baas.sendRequest({
			"url" : "/save",  //要和web.xml中的配置一致
			"action":"save",
			"params" :params,
			"success" : success
		});
		}
	};

	Model.prototype.OKBtnClick = function(event){
		this.comp('wReceiver').windowEnsure(this.result());
	};
	

	Model.prototype.wReceiverReceive = function(event){
		var alldata = event.data.data;
		type = event.data.action;
	    var rows = [];
	    labels = [];
	    var years= [];
	    var rowsdata;
	    var singleData =[];
	    for(var dex =0 ; dex < 8 ; dex++){
	         singleData[dex]= [];
	    }
	    $.each(alldata,function(name,value) {
	    	if (name == 'rows'){
				rowsdata = value;
			}
	    });
	    $.each(rowsdata,function(name,value){
	       var index = name;
	       var rowdata = value;
	      
	       var class_attr;
	       var class_name;
	       var cred;
	       var grad;
	       var point;
	       var tim;
	       var deservedCredit;
	       var rlab;
	       $.each(rowdata,function(name,value){
	           if(name == 'class_attr'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     class_attr = value;
	               
	               });
	           }
	           if(name == 'class_name'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     class_name = value;
	               
	               });
	           }
	           if(name == 'credit'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     cred = value;
	               
	               });
	           }
	           
	          if(name == 'grade'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     grad = value;
	               
	               });
	           }
	          if(name == 'point'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     point = value;
	               
	               });
	           }
	           if(name == 'deservedCredit'){
	               var coldata = value;
	               $.each(coldata,function(name,value){
	                     deservedCredit = value;
	               
	               });
	           }
	           if(name == 'time'){
	            var coldata = value;
	             $.each(coldata,function(name,value){
	            	 
	            	 tim = value;
	            	 var date = justep.Date.fromString(value,'yyyy-MM-dd');
	            	 var year = date.getYear()+1900;
	            	 var month = date.getMonth()+1;		
	            	 var label = "";
	            	 var term ;
	            	 if(month==1){
	            		 label = label+(year-1)+"学年 第1学期 ("+(year-1)+"年9月至"+year+"年1月"+")";
	            		 term = (year-1)*10+1;
	            	 }
	            	 else if(9<=month&&month<=12){
	            		 label = year+"学年 第1学期"+"("+year+"年9月至"+(year+1)+"年1月)";
	            		 term = (year)*10+1;
	            	 }else{
	            		 label = (year-1)+"学年 第2学期"+"("+year+"年3月至"+(year)+"年7月)";
	            		 term = (year)*10+2;
	            	 }
	            	 var index = $.inArray(term, years);
	            	 if(index ==-1){
	            		 years.push(term);
	            		 labels.push(label);
	            	 }
	            	 rlab = label;
	                 });
                 }
                 
	       });
	       var temRecord = new record(class_name,class_attr,cred,grad,point,tim,deservedCredit);
	       var lindex =  $.inArray(rlab, labels);
	       if(lindex!=-1){
	    	    singleData[lindex].push(temRecord);
	       }
	    });
        for(var sindex=0;sindex < labels.length;sindex++ ){
            terms[sindex]=singleData[sindex];
        }
		var termD = this.comp("termData");
		var i =0;
		while(termD.getCount()>0){
		    termD.remove(0);
		} 
		for(var indx in labels){
		     var termdata = {
		    	 id:indx,
		    	 calButton:true,
		    	 term:labels[indx]	 
		     };
		     termD.add(termdata);
		     termD.applyUpdates();
		}
		this.comp("mainData").loadData(alldata);
	};

	return Model;
});