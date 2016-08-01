define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var Baas = require('$UI/system/lib/baas');
	
	
	var userID;
	var Model = function(){
		this.callParent();
	};



	Model.prototype.modelLoad = function(event){
		
	};

	Model.prototype.taskDataCustomRefresh = function(event){
		var data = event.source;
		var params = {
			"columns" : Baas.getDataColumns(data),
			"limit" : event.limit,
			"offset" : event.offset,
			"id":userID
		};
		var success = function(resultData) {
			var append = event.options && event.options.append;
			data.loadData(resultData, append);
		};
		Baas.sendRequest({
			"url" : "/state",  //要和web.xml中的配置一致
			"action" : "getData",
			"params" : params,
			"success" : success
		});
		console.log(this.comp("taskData").getCount());
	};

	Model.prototype.wReceiverReceive = function(event){
		userID = event.data.userID;
		this.comp("taskData").refreshData(null);
	};
	
	Model.prototype.showOkButton = function(){
	    
	}

	Model.prototype.delButClick = function(event){
			var data = this.comp("taskData");
			var row = [];
			var jsonstr="{data:[";
		    
		    data.each(function(options){
			if (data.getValue("calBut", options.row)){
				row.push(data.getValue("jID", options.row));
			}
		    });
		    
		    for(var rindex in row){
		         data.remove(data.getRowByID(row[rindex]));
		    	jsonstr += "{value :'"+row[rindex]+"'}";
		    	if(rindex<row.length-1){
		    	  jsonstr+=",";
		    	}
		    }
		    jsonstr += "]}";
		    var jsonobj = eval(jsonstr);
	        var params = {'data' : jsonobj};
	     var success = function(resultData) {
			justep.Util.hint("记录删除成功！");
		};
		Baas.sendRequest({
			"url" : "/delete",  //要和web.xml中的配置一致
			"action" : "deleteRecord",
			"params" : params,
			"success" : success
		});
	};

	Model.prototype.calButClick = function(event){
		var row = event.bindingContext.$object;
		this.comp("taskData").remove(row);
		var params = {
			"jobID":row.val("jID")
		};
		var success = function(resultData) {
			justep.Util.hint("任务取消成功");
		};
		Baas.sendRequest({
			"url" : "/delete",  //要和web.xml中的配置一致
			"action" : "deleteList",
			"params" : params,
			"success" : success
		});
	};

	return Model;
});