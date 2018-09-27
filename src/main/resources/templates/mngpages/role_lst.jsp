<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="role.title"/></h2>
  		<div class="row">
        	<div class="col-sm-3 col-md-2"><button class="btn btn-md btn-primary glyphicon glyphicon-plus" onClick="openNewAccountPage()"> <spring:message code="common.new"/></button></div>
        </div>
        <br/>
        <div class="table-responsive">
          <table id="table" class="table table-striped" style="width:100%">
          </table>
        </div>
  </body>
  	<script type="text/javascript" >
		var TableParam = {
				header:['#','<spring:message code="role.name"/>','<spring:message code="role.parentId"/>','<spring:message code="role.startTime"/>','<spring:message code="role.endTime"/>'],
				column:['id','name','parentId','startTime','endTime'],
				url:'./role',
				httpType:'GET',
				target:'table',
				needOpration:true,
				operations:['detail','edit','grant','delt'],
				methods:[function(obj){
					Shaunz.showDetail('3',obj.data.id);
				},
				function(obj){
					Shaunz.load("./mngpages/role_edit.html?id="+obj.data.id);
				},
				function(obj){
					Shaunz.load("./mngpages/role_grant.html?id="+obj.data.id);
				},
				function(obj){
					$.confirm({
					    title: 'Delete ' + obj.data.name +' ?',
					    autoClose: 'cancelAction|80000',
					    buttons: {
					        deleteUser: {
					            text: 'Delete',
					            action: function () {
					            	Shaunz.ajaxRequest(null,'./role/'+obj.data.id,'DELETE');
					            	Shaunz.load("./mngpages/role_lst.html");
					            }
					        },
					        cancelAction: function () {
					        	
					        }
					    }
					});
				}]
		};
		$(function(){
			Shaunz.generateTable(TableParam);
		});
		
		openNewAccountPage = function(){
			Shaunz.load("./mngpages/role_add.html");
		}
	</script>
</html>
