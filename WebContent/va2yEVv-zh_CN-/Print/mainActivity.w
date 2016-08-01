<!DOCTYPE HTML>
<html style="width:100%;height:100%" class="no-js">
    <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="../system/components/pc.addon.min.css" include="$model/UI2/system/components/justep/cellLayout/css/cellLayout,$model/UI2/system/components/justep/pagerLimitSelect/css/pagerLimitSelect,$model/UI2/system/components/justep/widgets/css/widgets,$model/UI2/system/components/justep/smartContainer/css/smartContainer,$model/UI2/system/components/justep/absoluteLayout/css/absoluteLayout,$model/UI2/system/components/justep/pagerBar/css/pagerBar"><link rel="stylesheet" href="../system/components/bootstrap.min.css" include="$model/UI2/system/components/bootstrap/lib/css/bootstrap,$model/UI2/system/components/bootstrap/lib/css/bootstrap-theme"><link rel="stylesheet" href="../system/components/comp.min.css" include="$model/UI2/system/components/justep/row/css/row,$model/UI2/system/components/justep/attachment/css/attachment,$model/UI2/system/components/justep/barcode/css/barcodeImage,$model/UI2/system/components/bootstrap/form/css/form,$model/UI2/system/components/justep/panel/css/panel,$model/UI2/system/components/bootstrap/accordion/css/accordion,$model/UI2/system/components/justep/common/css/scrollable,$model/UI2/system/components/bootstrap/pager/css/pager,$model/UI2/system/components/justep/scrollView/css/scrollView,$model/UI2/system/components/justep/input/css/datePickerPC,$model/UI2/system/components/bootstrap/navs/css/navs,$model/UI2/system/components/justep/contents/css/contents,$model/UI2/system/components/justep/popMenu/css/popMenu,$model/UI2/system/components/justep/lib/css/icons,$model/UI2/system/components/justep/titleBar/css/titleBar,$model/UI2/system/components/justep/dataTables/css/dataTables,$model/UI2/system/components/justep/dialog/css/dialog,$model/UI2/system/components/justep/messageDialog/css/messageDialog,$model/UI2/system/components/bootstrap/navbar/css/navbar,$model/UI2/system/components/justep/toolBar/css/toolBar,$model/UI2/system/components/justep/popOver/css/popOver,$model/UI2/system/components/justep/loadingBar/loadingBar,$model/UI2/system/components/justep/input/css/datePicker,$model/UI2/system/components/justep/dataTables/css/dataTables,$model/UI2/system/components/bootstrap/dialog/css/dialog,$model/UI2/system/components/justep/wing/css/wing,$model/UI2/system/components/bootstrap/scrollSpy/css/scrollSpy,$model/UI2/system/components/justep/menu/css/menu,$model/UI2/system/components/justep/numberSelect/css/numberList,$model/UI2/system/components/justep/list/css/list,$model/UI2/system/components/bootstrap/carousel/css/carousel,$model/UI2/system/components/bootstrap/dropdown/css/dropdown,$model/UI2/system/components/justep/common/css/forms,$model/UI2/system/components/justep/bar/css/bar,$model/UI2/system/components/bootstrap/tabs/css/tabs,$model/UI2/system/components/bootstrap/pagination/css/pagination"></head>
	
    <body style="width:100%;height:100%;margin: 0;">
        <script intro="none"></script>
    	<script>
    	
    	    window.__justep = {};
		 
    	</script>
        <div id="applicationHost" class="applicationHost" style="width:100%;height:100%;" __component-context__="block"><div component="$model/UI2/system/components/justep/window/window" xid="window" design="device:pc;" data-bind="component:{name:'$model/UI2/system/components/justep/window/window'}" __justepbasexid__="__baseID__" __cid="cmMVNzi" class="cmMVNzi" components="$model/UI2/system/components/justep/loadingBar/loadingBar,$model/UI2/system/components/justep/pagerLimitSelect/pagerLimitSelect,$model/UI2/system/components/justep/labelEdit/labelEdit,$model/UI2/system/components/justep/row/row,$model/UI2/system/components/justep/pagerBar/pagerBar,$model/UI2/system/components/justep/windowDialog/windowDialog,$model/UI2/system/components/justep/list/list,$model/UI2/system/components/justep/window/window,$model/UI2/system/components/bootstrap/pagination/pagination,$model/UI2/system/components/justep/data/data,$model/UI2/system/components/justep/toolBar/toolBar,$model/UI2/system/components/justep/smartFilter/smartFilter,$model/UI2/system/components/justep/select/select,$model/UI2/system/components/justep/model/model,$model/UI2/system/components/justep/button/button,$model/UI2/system/components/justep/output/output,">  
  <div component="$model/UI2/system/components/justep/model/model" xid="model" style="display:none" data-bind="component:{name:'$model/UI2/system/components/justep/model/model'}" data-events="onLoad:modelLoad" __cid="cmMVNzi" class="cmMVNzi"></div>  
  <span component="$model/UI2/system/components/justep/windowDialog/windowDialog" xid="chooseDialog" style="left:204px;top:89px;" data-bind="component:{name:'$model/UI2/system/components/justep/windowDialog/windowDialog'}" data-config="{&#34;src&#34;:&#34;$model/UI2/Print/choose.w&#34;,&#34;status&#34;:&#34;normal&#34;}" __cid="cmMVNzi" class="cmMVNzi">
    <div class="x-dialog-overlay cmMVNzi" __cid="cmMVNzi"></div>
    <div class="x-dialog cmMVNzi" style="display:none;" __cid="cmMVNzi">
      <div class="x-dialog-title cmMVNzi" __cid="cmMVNzi">
        <button class="close cmMVNzi" __cid="cmMVNzi">
          <span __cid="cmMVNzi" class="cmMVNzi">×</span>
        </button>
        <div class="x-dialog-title-text cmMVNzi" __cid="cmMVNzi"></div>
      </div>
      <div class="x-dialog-body cmMVNzi" __cid="cmMVNzi"></div>
    </div>
  </span>  
  <span component="$model/UI2/system/components/justep/windowDialog/windowDialog" xid="stateDialog" data-bind="component:{name:'$model/UI2/system/components/justep/windowDialog/windowDialog'}" data-config="{&#34;height&#34;:&#34;90%&#34;,&#34;src&#34;:&#34;$model/UI2/Print/state.w&#34;,&#34;status&#34;:&#34;normal&#34;,&#34;width&#34;:&#34;90%&#34;}" __cid="cmMVNzi" class="cmMVNzi">
    <div class="x-dialog-overlay cmMVNzi" __cid="cmMVNzi"></div>
    <div class="x-dialog cmMVNzi" style="display:none;" __cid="cmMVNzi">
      <div class="x-dialog-title cmMVNzi" __cid="cmMVNzi">
        <button class="close cmMVNzi" __cid="cmMVNzi">
          <span __cid="cmMVNzi" class="cmMVNzi">×</span>
        </button>
        <div class="x-dialog-title-text cmMVNzi" __cid="cmMVNzi"></div>
      </div>
      <div class="x-dialog-body cmMVNzi" __cid="cmMVNzi"></div>
    </div>
  </span>
  <div xid="view" __cid="cmMVNzi" class="cmMVNzi" data-bind="event:{unload:$model._callModelFn.bind($model, 'viewUnload'),load:$model._callModelFn.bind($model, 'viewLoad')}"> 
    <div component="$model/UI2/system/components/justep/toolBar/toolBar" class="x-toolbar x-toolbar-spliter form-inline cmMVNzi" xid="bar" data-bind="component:{name:'$model/UI2/system/components/justep/toolBar/toolBar'}" __cid="cmMVNzi"> 
      <div component="$model/UI2/system/components/justep/smartFilter/smartFilter" xid="smartFilter1" filterData="mainData" filterCols="" class="pull-right cmMVNzi" __cid="cmMVNzi"> 
        <div component="$model/UI2/system/components/justep/labelEdit/labelEdit" class="x-label-edit x-label30 center-block cmMVNzi" xid="labelOutput1" style="vertical-align:middle;text-align:center;margin-right:20px;" data-bind="component:{name:'$model/UI2/system/components/justep/labelEdit/labelEdit'}" __cid="cmMVNzi"> 
          <label xid="label1" style="vertical-align:middle;" __cid="cmMVNzi" class="cmMVNzi">欢迎您：</label>  
          <div component="$model/UI2/system/components/justep/output/output" class="x-output text-info x-edit cmMVNzi" xid="output2" style="color:#0080C0;" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'},text:$model.user.val(&#34;userName&#34;)+&#34;(&#34;+ $model.user.val(&#34;id&#34;)+&#34;)&#34;,value:$model.user.val(&#34;userName&#34;)" __cid="cmMVNzi"></div>
        </div>
      </div>  
      <a component="$model/UI2/system/components/justep/button/button" class="btn btn-link btn-icon-left cmMVNzi" xid="saveBtn" data-bind="component:{name:'$model/UI2/system/components/justep/button/button'}" data-events="onClick:saveBtnClick" data-config="{&#34;icon&#34;:&#34;glyphicon glyphicon-floppy-disk&#34;,&#34;label&#34;:&#34;保存&#34;}" __cid="cmMVNzi"> 
        <i xid="i3" class="glyphicon glyphicon-floppy-disk cmMVNzi" __cid="cmMVNzi"></i>  
        <span xid="label7" __cid="cmMVNzi" class="cmMVNzi">保存</span> 
      </a>  
      <a component="$model/UI2/system/components/justep/button/button" class="btn btn-link btn-icon-left cmMVNzi" xid="refreshBtn" data-bind="component:{name:'$model/UI2/system/components/justep/button/button'}" data-events="onClick:[{operation:'mainData.refresh'},{operation:'allData.refresh'}]" data-config="{&#34;label&#34;:&#34;刷新&#34;}" __cid="cmMVNzi"> 
        <i xid="i2" __cid="cmMVNzi" class="cmMVNzi"></i>  
        <span xid="span2" __cid="cmMVNzi" class="cmMVNzi">刷新</span> 
      </a>  
      <a component="$model/UI2/system/components/justep/button/button" class="btn btn-link btn-icon-left cmMVNzi" xid="printbut" data-bind="component:{name:'$model/UI2/system/components/justep/button/button'}" data-events="onClick:printbutClick" data-config="{&#34;icon&#34;:&#34;icon-ios7-printer&#34;,&#34;label&#34;:&#34;打印&#34;}" __cid="cmMVNzi"> 
        <i xid="i1" class="icon-ios7-printer cmMVNzi" __cid="cmMVNzi"></i>  
        <span xid="span7" __cid="cmMVNzi" class="cmMVNzi">打印</span>
      </a>  
      <a component="$model/UI2/system/components/justep/button/button" class="btn btn-link btn-icon-left cmMVNzi" xid="stateBut" data-bind="component:{name:'$model/UI2/system/components/justep/button/button'}" data-events="onClick:stateButClick" data-config="{&#34;icon&#34;:&#34;glyphicon glyphicon-duplicate&#34;,&#34;label&#34;:&#34;状态&#34;}" __cid="cmMVNzi"> 
        <i xid="i5" class="glyphicon glyphicon-duplicate cmMVNzi" __cid="cmMVNzi"></i>  
        <span xid="span10" __cid="cmMVNzi" class="cmMVNzi">状态</span>
      </a>
      <a component="$model/UI2/system/components/justep/button/button" class="btn btn-link btn-icon-left cmMVNzi" xid="exitBut" data-bind="component:{name:'$model/UI2/system/components/justep/button/button'}" data-events="onClick:exitButClick" data-config="{&#34;icon&#34;:&#34;glyphicon glyphicon-log-out&#34;,&#34;label&#34;:&#34;退出&#34;}" __cid="cmMVNzi"> 
        <i xid="i4" class="glyphicon glyphicon-log-out cmMVNzi" __cid="cmMVNzi"></i>  
        <span xid="span8" __cid="cmMVNzi" class="cmMVNzi">退出</span>
      </a> 
    </div>  
    <div xid="mainList" class="x-list center-block cmMVNzi" component="$model/UI2/system/components/justep/list/list" style="width:98%;" data-bind="component:{name:'$model/UI2/system/components/justep/list/list'}" data-config="{&#34;data&#34;:&#34;mainData&#34;}" __cid="cmMVNzi"> 
      <div class="x-list-head cmMVNzi" style="text-align:center;vertical-align:middle;" __cid="cmMVNzi">
        <div component="$model/UI2/system/components/justep/row/row" class="x-row center-block x-col cmMVNzi" xid="row2" style="text-align:center;vertical-align:middle;" data-bind="component:{name:'$model/UI2/system/components/justep/row/row'}" __cid="cmMVNzi"> 
          <div class="x-row center-block cmMVNzi" component="$model/UI2/system/components/justep/row/row" xid="row3" style="text-align:center;vertical-align:middle;width:98%;" data-bind="component:{name:'$model/UI2/system/components/justep/row/row'}" __cid="cmMVNzi"> 
            <div class="x-col cmMVNzi" xid="col4" __cid="cmMVNzi"> 
              <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output9" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'},text:'课程属性'" __cid="cmMVNzi"></div>
            </div>  
            <div class="x-col cmMVNzi" xid="col5" __cid="cmMVNzi"> 
              <div component="$model/UI2/system/components/justep/output/output" class="x-output  cmMVNzi" xid="output8" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'},text:'课程名'" __cid="cmMVNzi"></div>
            </div>  
            <div class="x-col cmMVNzi" xid="col6" __cid="cmMVNzi"> 
              <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output7" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
            </div>  
            <div class="x-col cmMVNzi" xid="col7" __cid="cmMVNzi"> 
              <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output6" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'},text:'学分'" __cid="cmMVNzi"></div>
            </div>  
            <div class="x-col cmMVNzi" xid="col8" __cid="cmMVNzi"> 
              <div component="$model/UI2/system/components/justep/output/output" class="x-output  cmMVNzi" xid="output10" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'},text:'成绩'" __cid="cmMVNzi"></div>
            </div> 
          </div> 
        </div>
      </div>  
      <ul class="x-list-template x-min-height hide cmMVNzi" componentname="$UI/system/components/justep/list/list#listTemplateUl" __cid="cmMVNzi" data-bind="foreach:{data:$model.foreach_mainList($element),afterRender:$model.foreach_afterRender_mainList.bind($model,$element)}"> 
        <li class="x-min-height list-group-item cmMVNzi" componentname="li(html)" style="text-align:center;vertical-align:middle;" __cid="cmMVNzi"> 
          <div component="$model/UI2/system/components/justep/row/row" class="x-row x-row-center center-block cmMVNzi" style="text-align:center;vertical-align:middle;height:42px;" data-bind="component:{name:'$model/UI2/system/components/justep/row/row'},style:'background-color:rgb(153,204,255)'" __cid="cmMVNzi"> 
            <div component="$model/UI2/system/components/justep/row/row" class="x-col  cmMVNzi" data-bind="component:{name:'$model/UI2/system/components/justep/row/row'}" __cid="cmMVNzi"> 
              <div class="x-row cmMVNzi" component="$model/UI2/system/components/justep/row/row" data-bind="component:{name:'$model/UI2/system/components/justep/row/row'}" __cid="cmMVNzi"> 
                <div class="x-col cmMVNzi" __cid="cmMVNzi"> 
                  <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output1" data-bind="component:{ref:ref('class_attr'),name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
                </div>  
                <div class="x-col cmMVNzi" __cid="cmMVNzi"> 
                  <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output16" data-bind="component:{ref:ref('class_name'),name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
                </div>  
                <div class="x-col cmMVNzi" __cid="cmMVNzi"> 
                  <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output3" data-bind="component:{name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
                </div>  
                <div class="x-col cmMVNzi" __cid="cmMVNzi"> 
                  <div component="$model/UI2/system/components/justep/output/output" class="x-output cmMVNzi" xid="output17" data-bind="component:{ref:ref('credit'),name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
                </div>  
                <div class="x-col cmMVNzi" __cid="cmMVNzi"> 
                  <div component="$model/UI2/system/components/justep/output/output" class="x-output  cmMVNzi" xid="output5" data-bind="component:{ref:ref('grade'),name:'$model/UI2/system/components/justep/output/output'}" __cid="cmMVNzi"></div>
                </div> 
              </div> 
            </div> 
          </div> 
        </li> 
      </ul> 
    </div> 
  </div>  
  <div component="$model/UI2/system/components/justep/pagerBar/pagerBar" class="x-pagerbar container-fluid cmMVNzi" xid="pagerBar" data-bind="component:{name:'$model/UI2/system/components/justep/pagerBar/pagerBar'}" data-config="{&#34;data&#34;:&#34;mainData&#34;}" __cid="cmMVNzi"> 
    <div class="row cmMVNzi" xid="div1" __cid="cmMVNzi"> 
      <div class="col-sm-3 cmMVNzi" xid="div2" __cid="cmMVNzi"> 
        <div class="x-pagerbar-length cmMVNzi" xid="div3" __cid="cmMVNzi"> 
          <label component="$model/UI2/system/components/justep/pagerLimitSelect/pagerLimitSelect" class="x-pagerlimitselect cmMVNzi" xid="pagerLimitSelect1" data-bind="component:{name:'$model/UI2/system/components/justep/pagerLimitSelect/pagerLimitSelect'}" data-config="{&#34;data&#34;:&#34;mainData&#34;,&#34;defaultValue&#34;:10}" __cid="cmMVNzi"> 
            <span xid="span1" __cid="cmMVNzi" class="cmMVNzi">显示</span>  
            <select component="$model/UI2/system/components/justep/select/select" class="form-control input-sm cmMVNzi" xid="select1" data-bind="component:{name:'$model/UI2/system/components/justep/select/select'}" __cid="cmMVNzi"> 
              <option value="10" xid="default2" __cid="cmMVNzi" class="cmMVNzi">10</option>  
              <option value="20" xid="default3" __cid="cmMVNzi" class="cmMVNzi">20</option>  
              <option value="50" xid="default4" __cid="cmMVNzi" class="cmMVNzi">50</option>  
              <option value="100" xid="default5" __cid="cmMVNzi" class="cmMVNzi">100</option> 
            </select>  
            <span xid="span3" __cid="cmMVNzi" class="cmMVNzi">条</span> 
          </label> 
        </div> 
      </div>  
      <div class="col-sm-3 cmMVNzi" xid="div4" __cid="cmMVNzi"> 
        <div class="x-pagerbar-info cmMVNzi" xid="div5" __cid="cmMVNzi">当前显示1-10条，共16条</div> 
      </div>  
      <div class="col-sm-6 cmMVNzi" xid="div6" __cid="cmMVNzi"> 
        <div class="x-pagerbar-pagination cmMVNzi" xid="div7" __cid="cmMVNzi"> 
          <ul class="pagination cmMVNzi" component="$model/UI2/system/components/bootstrap/pagination/pagination" xid="pagination1" data-bind="component:{name:'$model/UI2/system/components/bootstrap/pagination/pagination'}" data-config="{&#34;data&#34;:&#34;mainData&#34;}" __cid="cmMVNzi"> 
            <li class="prev cmMVNzi" xid="li1" __cid="cmMVNzi"> 
              <a href="#" xid="a1" __cid="cmMVNzi" class="cmMVNzi"> 
                <span aria-hidden="true" xid="span4" __cid="cmMVNzi" class="cmMVNzi">«</span>  
                <span class="sr-only cmMVNzi" xid="span5" __cid="cmMVNzi">Previous</span> 
              </a> 
            </li>  
            <li class="next cmMVNzi" xid="li2" __cid="cmMVNzi"> 
              <a href="#" xid="a2" __cid="cmMVNzi" class="cmMVNzi"> 
                <span aria-hidden="true" xid="span6" __cid="cmMVNzi" class="cmMVNzi">»</span>  
                <span class="sr-only cmMVNzi" xid="span9" __cid="cmMVNzi">Next</span> 
              </a> 
            </li> 
          </ul> 
        </div> 
      </div> 
    </div> 
  </div> 
</div></div>
        
        <div id="downloadGCF" style="display:none;padding:50px;">
        	<span>您使用的浏览器需要下载插件才能使用, </span>
        	<a id="downloadGCFLink" href="#">下载地址</a>
        	<p>(安装后请重新打开浏览器)</p>
        </div>
    	<script>
    	
    	            //判断浏览器, 判断GCF
    	 			var browser = {
    			        isIe: function () {
    			            return navigator.appVersion.indexOf("MSIE") != -1;
    			        },
    			        navigator: navigator.appVersion,
    			        getVersion: function() {
    			            var version = 999; // we assume a sane browser
    			            if (navigator.appVersion.indexOf("MSIE") != -1)
    			                // bah, IE again, lets downgrade version number
    			                version = parseFloat(navigator.appVersion.split("MSIE")[1]);
    			            return version;
    			        }
    			    };
    				function isGCFInstalled(){
    			      try{
    			        var i = new ActiveXObject('ChromeTab.ChromeFrame');
    			        if (i) {
    			          return true;
    			        }
    			      }catch(e){}
    			      return false;
    				}
    	            //判断浏览器, 判断GCF
    	            var __continueRun = true;
    				if (browser.isIe() && (browser.getVersion() < 10) && !isGCFInstalled()) {
    					document.getElementById("applicationHost").style.display = 'none';
    					document.getElementById("downloadGCF").style.display = 'block';
    					var downloadLink = "/" + location.pathname.match(/[^\/]+/)[0] + "/v8.msi";
    					document.getElementById("downloadGCFLink").href = downloadLink; 
    					__continueRun = false;
    	            }
		 	
    	</script>
        <script src="../system/config/meta.js"> </script>
        <script id="_requireJS" src="../system/lib/require/require.2.1.10.js"> </script>
        <script src="../system/core.min.js"></script><script src="../system/common.min.js"></script><script src="../system/components/comp.min.js"></script><script src="../system/components/pc.addon.min.js"></script><script src="../system/components/comp2.min.js"></script><script id="_mainScript">
        
			if (__continueRun) {
                window.__justep.isDebug = false;     
                
                window.__justep.cssReady = function(fn){
                	var promises = [];
                	for (var p in window.__justep.__ResourceEngine.__loadingCss){
                		if(window.__justep.__ResourceEngine.__loadingCss.hasOwnProperty(p))
                			promises.push(window.__justep.__ResourceEngine.__loadingCss[p].promise());
                	}
                	$.when.apply($, promises).done(fn);
                };
                
            	window.__justep.__ResourceEngine = {
            		readyRegExp : navigator.platform === 'PLAYSTATION 3' ? /^complete$/ : /^(complete|loaded)$/,
            		url: window.location.href,	
            		/*contextPath: 不包括语言 */
            		contextPath: "",
            		serverPath: "",
            		__loadedJS: [],
            		__loadingCss: {},
            		onLoadCss: function(url, node){
            			if (!this.__loadingCss[url]){
            				this.__loadingCss[url] = $.Deferred();	
                			if (node.attachEvent &&
                                    !(node.attachEvent.toString && node.attachEvent.toString().indexOf('[native code') < 0) &&
                                    !(typeof opera !== 'undefined' && opera.toString() === '[object Opera]')) {
                                node.attachEvent('onreadystatechange', this.onLinkLoad.bind(this));
                            } else {
                                node.addEventListener('load', this.onLinkLoad.bind(this), false);
                                node.addEventListener('error', this.onLinkError.bind(this), false);
                            }
            			}
            		},
            		
            		onLinkLoad: function(evt){
            	        var target = (evt.currentTarget || evt.srcElement);
            	        if (evt.type === 'load' ||
                                (this.readyRegExp.test(target.readyState))) {
            	        	var url = target.getAttribute("href");
            	        	if (url && window.__justep.__ResourceEngine.__loadingCss[url]){
            	        		window.__justep.__ResourceEngine.__loadingCss[url].resolve(url);
            	        	}
                        }
            		},
            		
            		onLinkError: function(evt){
            	        var target = (evt.currentTarget || evt.srcElement);
        	        	var url = target.getAttribute("href");
        	        	if (url && window.__justep.__ResourceEngine.__loadingCss[url]){
        	        		window.__justep.__ResourceEngine.__loadingCss[url].resolve(url);
        	        	}
            		},
            		
            		initContextPath: function(){
            			var baseURL = document.getElementById("_requireJS").src;
            			var before = location.protocol + "//" + location.host;
            			var after = "/system/lib/require/require.2.1.10.js";
            			var i = baseURL.indexOf(after);
            			if (i !== -1){
    	        			var middle = baseURL.substring(before.length, i);
    						var items = middle.split("/");
    						
    						
    						if ((items[items.length-1].indexOf("v_") === 0) 
    								&& (items[items.length-1].indexOf("l_") !== -1)
    								&& (items[items.length-1].indexOf("s_") !== -1)
    								&& (items[items.length-1].indexOf("d_") !== -1)){
    							items.splice(items.length-1, 1);
    						}
    						
    						
    						if (items.length !== 1){
    							window.__justep.__ResourceEngine.contextPath = items.join("/");
    						}else{
    							window.__justep.__ResourceEngine.contextPath = before;
    						}
    						var index = window.__justep.__ResourceEngine.contextPath.lastIndexOf("/");
    						if (index != -1){
    							window.__justep.__ResourceEngine.serverPath = window.__justep.__ResourceEngine.contextPath.substr(0, index);
    						}else{
    							window.__justep.__ResourceEngine.serverPath = window.__justep.__ResourceEngine.contextPath;
    						}
            			}else{
            				throw new Error(baseURL + " hasn't  " + after);
            			}
            		},
            	
            		loadJs: function(urls){
            			if (urls && urls.length>0){
            				var loadeds = this._getResources("script", "src").concat(this.__loadedJS);
    	       				for (var i=0; i<urls.length; i++){
    	       					var url = window.__justep.__ResourceEngine.contextPath + urls[i];
    	        				if(!this._isLoaded(url, loadeds)){
    	        					this.__loadedJS[this.__loadedJS.length] = url;
    	        					/*
    	        					var script = document.createElement("script");
    	        					script.src = url;
    	        					document.head.appendChild(script);
    	        					*/
    	        					//$("head").append("<script  src='" + url + "'/>");
    	        					$.ajax({
    	        						url: url,
    	        						dataType: "script",
    	        						cache: true,
    	        						async: false,
    	        						success: function(){}
    	        						});
    	        				} 
    	       				}
            			}
            		},
            		
            		loadCss: function(styles){
           				var loadeds = this._getResources("link", "href");
            			if (styles && styles.length>0){
            				for (var i=0; i<styles.length; i++){
    	       					var url = window.__justep.__ResourceEngine.contextPath + styles[i].url
    	        				if(!this._isLoaded(url, loadeds)){
    	        					var include = styles[i].include || "";
    	        					var link = $("<link type='text/css' rel='stylesheet' href='" + url + "' include='" + include + "'/>");
    	        					this.onLoadCss(url, link[0]);
    	        					$("head").append(link);
    	        				} 
            				}
            			}
            			
            		},
            		
            		
            		_isLoaded: function(url, loadeds){
            			if (url){
            				var newUrl = "";
            				var items = url.split("/");
            				var isVls = false;
            				for (var i=0; i<items.length; i++){
            					if (isVls){
                					newUrl += "/" + items[i];
            					}else{
                					if (items[i] && (items[i].indexOf("v_")===0)
            								&& (items[i].indexOf("l_")!==-1)
            								&& (items[i].indexOf("s_")!==-1)
            								&& (items[i].indexOf("d_")!==-1)){
                						isVls = true;
                					}
            					}
            				}
            				if (!newUrl)
            					newUrl = url;
            				
            				for (var i=0; i<loadeds.length; i++){
    							if (loadeds[i] && (loadeds[i].indexOf(newUrl)!==-1)){
    								return true;
    							}
    						}
            			}
    					return false;
            		},
            		
            		_getResources: function(tag, attr){
    					var result = [];
    					var scripts = $(tag);
    					for (var i=0; i<scripts.length; i++){
    						var v = scripts[i][attr];
    						if (v){
    							result[result.length] = v;
    						}
    					}
    					return result;
            		}
            	};
            	
            	window.__justep.__ResourceEngine.initContextPath();
            	window.__justep.__isPackage = true;;
    			requirejs.config({
    				baseUrl: window.__justep.__ResourceEngine.contextPath + '/Print',
    			    paths: {
    			    	'modernizr': window.__justep.__ResourceEngine.contextPath + '/system/lib/base/modernizr-2.8.3.min',
    			    	'$model/UI2': window.__justep.__ResourceEngine.contextPath + '',
    			    	'$model': window.__justep.__ResourceEngine.serverPath,
    			        'text': window.__justep.__ResourceEngine.contextPath + '/system/lib/require/text.2.0.10',
    			        'bind': window.__justep.__ResourceEngine.contextPath + '/system/lib/bind/bind',
    			        'jquery': window.__justep.__ResourceEngine.contextPath + '/system/lib/jquery/jquery-1.11.1.min'
    			    },
    			    shim: {
    			    	'modernizr': {
    			    		exports: 'Modernizr'
    			    	}
    			    },
    				map: {
    				        '*': {
    				            res: window.__justep.__ResourceEngine.contextPath + '/system/lib/require/res.js',
    				            cordova: window.__justep.__ResourceEngine.contextPath + '/system/lib/require/cordova.js',
    				            w: window.__justep.__ResourceEngine.contextPath + '/system/lib/require/w.js',
    				            css: window.__justep.__ResourceEngine.contextPath + '/system/lib/require/css.js'
    				        }
    				},
    				deps: ['modernizr'],
    				waitSeconds: 30
    			});
    			
    			requirejs(['require', 'jquery', '$model/UI2/system/lib/base/composition', '$model/UI2/system/components/justep/loadingBar/loadingBar', '$model/UI2/system/lib/jquery/domEvent', '$model/UI2/system/lib/cordova/cordova','$model/UI2/activity2WindowURL'],  function (require, $, composition) { 
    	            document.addEventListener('deviceready', function() {
    	                if (navigator && navigator.splashscreen && navigator.splashscreen.hide) {
    	                	/*延迟隐藏，视觉效果更理想*/
    	                	setTimeout(function() {navigator.splashscreen.hide();}, 800);
    	                }
    	            }, false);
    				var context = {};
    				context.model = '$model/UI2/Print/mainActivity.w' + (document.location.search || "");
    				context.view = $('#applicationHost').children()[0];
    				var element = document.getElementById('applicationHost');
    				composition.compose(element, context);
    			});    
            }
		 	
        </script>
    </body>
</html>