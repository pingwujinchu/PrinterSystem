define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var Baas = require('$UI/system/lib/baas');

	var Model = function(){
		this.callParent();
	};


	Model.prototype.mainDataCustomRefresh = function(event){
		/*
		var data = event.source;
		data.defCols['calcCheckBox'].relation = "EXPRESS";
		var params = {
			"columns" : Baas.getDataColumns(data),
			"limit" : event.limit,
			"offset" : event.offset,
		};
		var success = function(resultData) {
			var append = event.options && event.options.append;
			data.loadData(resultData, append);
		};
		Baas.sendRequest({
			"url" : "/test",  //要和web.xml中的配置一致
			"action" : "queryTest",
			"params" : params,
			"success" : success
		});
	*/};

	

	Model.prototype.cancelEditClick = function(event){
		var data = this.comp("mainData");
		data.each(function(options){
			if (data.getValue("calcCheckBox", options.row)){
				data.setValue('calcCheckBox',false,options.row);
			}
		});
	};

	Model.prototype.deleteBtnClick = function(event){
		var data = this.comp("mainData");
		data.directDeleteMode = false;
		var rows = [];
		data.each(function(options){
			if (data.getValue("calcCheckBox", options.row)){
				rows.push(options.row);
			}
		});
		if (rows.length > 0){
			data.deleteData(rows);
		}
	
	};

	Model.prototype.button1Click = function(event){
             var username = this.comp("userName");
             var password = this.comp("password");
             if(username == null||password == null){
               return;
             }
            var params = {
			"userName" : username.val(),
			"password" : password.val()
		};
	
		var success = function(resultData) {
			var tip = event.data;
			var tipinfo = tip.getString("tip");
			if(tipinfo== "success"){
			   window.location.href = "mainActivity.w?userName="+username.val();
			}else if(tipinfo == "1"){
			  justep.Util.hint("您所输入的用户名不存在。\n提示：用户名是自己的学号");
			}else if(tipinfo == "2"){
				justep.Util.hint("您输入的密码有误，请核对密码。\n提示：密码是自己身份证后六位");
			}
		};

		Baas.sendRequest({
			"url" : "/login",  //要和web.xml中的配置一致
			"params" : params,
			"success" : success
		});
	};

	Model.prototype.loginButClick = function(event){
		     var username = document.getElementById("userName").value;
		     console.log(username);
             var password = document.getElementById("password").value;
             if(username == null||password == null){
               return;
             }
            var params = {
			"userName" : username,
			"password" : password
		};
		var self = this;
		var success = function(event) {
			var tip = event.tip;
			var tipinfo = tip;
			var name = event.name;
			if(tipinfo== "success"){
			   window.location.href = "mainActivity.w?userID="+username+"&name="+name;
			}else if(tipinfo == "1"){
			  justep.Util.hint("您所输入的用户名不存在。\n提示：用户名是自己的学号");
			}else if(tipinfo == "2"){
				justep.Util.hint("您输入的密码有误，请核对密码。\n提示：密码是自己身份证后六位");
			}
		};

		Baas.sendRequest({
			"url" : "/login",  //要和web.xml中的配置一致
			"params" : params,
			"success" : success
		});
	};

	return Model;
});