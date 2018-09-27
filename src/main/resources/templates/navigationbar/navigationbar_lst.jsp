<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="navigationbar.title"/></h2>
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
				header:['#','<spring:message code="navigationbar.name"/>','<spring:message code="navigationbar.type"/>','<spring:message code="navigationbar.url"/>',
					'<spring:message code="navigationbar.active"/>'],
				column:['id','name','type','url','active'],
				url:'./navigationbar',
				httpType:'GET',
				target:'table',
				needOpration:true,
				operations:['detail','edit','relate','delt'],
				methods:[function(obj){
					Shaunz.showDetail('5',obj.data.id);
				},
				function(obj){
					Shaunz.load("./navigationbar/navigationbar_edit.html?id="+obj.data.id);
				},
				function(obj){
					Shaunz.load("./navigationbar/navigationbar_blog.html?id="+obj.data.id);
				},
				function(obj){
					$.confirm({
					    title: 'Delete ' + obj.data.name +' ?',
					    autoClose: 'cancelAction|8000',
					    buttons: {
					        deleteUser: {
					            text: 'Delete',
					            action: function () {
					            	console.log(obj.data.id);
					            	Shaunz.ajaxRequest(null,'./navigationbar/'+obj.data.id,'DELETE');
									Shaunz.load("./navigationbar/navigationbar_lst.html");
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
			Shaunz.load("./navigationbar/navigationbar_add.html");
		}
		
	</script>
</html>
