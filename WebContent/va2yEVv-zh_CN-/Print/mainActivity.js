define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var Baas = require('$UI/system/lib/baas');


	var userID;
	var userName;
	var Model = function(){
		this.callParent();
		this._deviceType = "pc"; // pc || wx || app

	};

 
	Model.prototype.saveCommit = function(event){
		justep.Util.hint("保存成功！", {type: 'success', parent: this.getRootNode()});
	};
	
	Model.prototype.mainDataCustomRefresh = function(event){
		var context = this.getContext();
        userID = this.getContext().getRequestParameter('userID');
        userName = this.getContext().getRequestParameter('name');
        if(userID == null||userID == ""){
		    window.location.href = "loginActivity.w";
		    return;
        }
        
        this.comp("user").refreshData(null);
        
//		alert("查询数据：请参考注释代码或者baas资料，手动进行修改")
		/*
		data.defCols['calcCheckBox'].relation = "EXPRESS";*/
		
		var data = event.source;
		var params = {
			"columns" : Baas.getDataColumns(data),
			"limit" : event.limit,
			"offset" : event.offset,
			 "id": userID
		};
		var success = function(resultData) {
			var append = event.options && event.options.append;
			data.loadData(resultData, append);
		};
		Baas.sendRequest({
			"url" : "/show",  //要和web.xml中的配置一致
			"action" : "getData",
			"params" : params,
			"success" : success
		});

	};

	Model.prototype.mainDataCustomSave = function(event){
	    
//		alert("数据保存：请参考注释代码或者baas资料，手动进行修改")
		
		// 获取当前数据对象
	    var data = event.source;
	    data.defCols['calcCheckBox'].relation = "EXPRESS";
	    // 构造请求参数
	    var params = {
	    	'data' : data.toJson(true,true) // 将数据集中已变更数据导出为JSON数据
	    };
	    // 请求成功后的回调方法
	    var success = function(resultData) {
	        // 保存成功后，必须调用data.applyUpdates()，用于数据集确认数据已更新
	        data.applyUpdates();
	    };
	    // 发送请求
	    Baas.sendRequest({
	        'url' : '/test', // servlet请求地址，url要和web.xml中的配置一致
	        'action' : 'saveTest', // action
	        'params' : params, // action对应的参数
	        'success' : success // 请求成功后的回调方法
	    });
	};

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

	Model.prototype.modelLoad = function(event){

		var self = this;
		// 获取url上的code参数 - 微信授权code，用于获取微信用户信息
		var weixinCode = this.getContext().getRequestParameter("code");

		// 判断运行环境是否在X5移动客户端中，如果在移动客户端中，则当deviceready后取手机设备uuid作为用户唯一标识
		if (justep.Browser.isX5App) {
			this._deviceType = "app";

			CommonUtils.attachDoubleClickExitApp(function() {
				if (self.comp('contents').getActiveIndex() === 0) {
					return true;
				}
				return false;
			});
			document.addEventListener("deviceready", function() {
				self._userID = window.device.uuid;
				self._userDefaultName = "新用户（来自X5APP的用户）";
			}, false);

		} else if (weixinCode !== "") {
			this._deviceType = "wx";
			if (justep.Browser.isWeChat) {
				this.wxApi = new navigator.WxApi("wx617b69a84c5f638c");
			}

			$.getJSON("/baas/weixin/userinfo?code=" + weixinCode, function(weixinUser) {
				self._userID = weixinUser.openid;
				self._userDefaultName = weixinUser.nickname + "（来自微信的用户）";
				self._userDefaultAddress = weixinUser.country + weixinUser.province + weixinUser.city;
				self._userPhotoURL = weixinUser.headimgurl;
			});
		}
		
		document.addEventListener("deviceready", function() {
		  var context = self.getContext();
          userID = self.getContext().getRequestParameter('userID');
          userName = self.getContext().getRequestParameter('name');
         if(userID == null||userID == ""){
		    window.location.href = "loginActivity.w";
		    return;
          }else{
            self.comp("user").setValue("id",userID);
            self.comp("user").setValue("userName",userName);
          }
         alert(userName);
			}, false);
	};

    Model.prototype.getUserName = function(){
        return userName;
    };
	Model.prototype.printbutClick = function(event){
	     var mainData = this.comp("allData");
	     this.comp("chooseDialog").open({data:{data:mainData.toJson(),action:'print'}});
	};

	Model.prototype.allDataCustomRefresh = function(event){
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
			"url" : "/show",  //要和web.xml中的配置一致
			"action" : "getData",
			"params" : params,
			"success" : success
		});
		
	};

	Model.prototype.viewUnload = function(event){

	};

	Model.prototype.viewLoad = function(event){

	};

	Model.prototype.modelModelConstruct = function(event){
		
	};

	Model.prototype.modelActive = function(event){
				
	};

	Model.prototype.exitButClick = function(event){

		var success = function(resultData) {
		     justep.Util.hint("成功退出");
		     window.close();
		     window.location.href = "loginActivity.w";
		};
		Baas.sendRequest({
			"url" : "/exit",  //要和web.xml中的配置一致
			"success" : success
		});
	};

	Model.prototype.allDataCustomSave = function(event){

	};

	Model.prototype.saveBtnClick = function(event){
		 var mainData = this.comp("allData");
	     this.comp("chooseDialog").open({data:{data:mainData.toJson(),action:'save'}});
	};

	Model.prototype.userCustomRefresh = function(event){
	     var termD = this.comp("user");
		 while(termD.getCount()>0){
		    termD.remove(0);
		} 
		termD.add({id:userID,userName:userName});
		
	};

	Model.prototype.stateButClick = function(event){
		this.comp("stateDialog").open({data:{userID:userID}});
	};

	return Model;
});