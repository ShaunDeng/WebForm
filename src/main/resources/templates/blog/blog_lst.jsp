<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="blog.title"/></h2>
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
				header:['#','name','header','Post Time','Author'],
				column:['id','name','header','postTime','authorId'],
				url:'./blog',
				httpType:'GET',
				target:'table',
				needOpration:true,
				operations:['detail','edit','delt'],
				methods:[function(obj){
					Shaunz.showDetail('16',obj.data.id);
				},
				function(obj){
					Shaunz.load("./blog/blog_edit.html?id="+obj.data.id);
				},
				function(obj){
					$.confirm({
					    title: 'Delete ' + obj.data.name +' ?',
					    autoClose: 'cancelAction|8000',
					    buttons: {
					        deleteUser: {
					            text: 'Delete',
					            action: function () {
					            	Shaunz.ajaxRequest(null,'./blog/'+obj.data.id,'DELETE');
									Shaunz.load("./blog/blog_lst.html");
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
			Shaunz.load("./blog/blog_add.html");
		}
		
	</script>
</html>
