<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="user.title"/></h2>
  		<div class="row">
        	<div class="col-sm-3 col-md-2"><button class="btn btn-md btn-primary glyphicon glyphicon-plus"> <spring:message code="common.new"/></button></div>
        </div>
        <br/>
        <div class="table-responsive">
			<table id="table" class="table table-striped"></table>
        </div>
  </body>
  	<script type="text/javascript" >
		var TableParam = {
				header:['#','<spring:message code="user.aliasNm"/>','<spring:message code="user.gender"/>','<spring:message code="user.email"/>',
					'<spring:message code="user.startTime"/>','<spring:message code="user.endTime"/>'],
				column:['id','aliasNm','gender','email','startTime','endTime'],
				translate:['gender'],
				url:'./user',
				httpType:'GET',
				target:'table',
				needOpration:true,
				operations:['detail','edit','grant','delt'],
				methods:[function(obj){
					Shaunz.showDetail('2',obj.data.id);
				},
				function(obj){
					Shaunz.load("./mngpages/account_edit.html?id="+obj.data.id);
				},
				function(obj){
					Shaunz.load("./mngpages/account_grant.html?userId="+obj.data.id);
				},
				function(obj){
					Shaunz.deleteConfirmBox(obj.data.aliasNm,function () {
		            	Shaunz.ajaxRequest(null,'./user/'+obj.data.id,'DELETE');
						Shaunz.load("./mngpages/account_lst.html");
		            },function(){});
				}]
		};
		$(function(){
			Shaunz.generateTable(TableParam);
			$('button.glyphicon-plus').click(function(){
				Shaunz.load("./mngpages/account_add.html");
			});
		});
	</script>
</html>
