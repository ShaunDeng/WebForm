<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="user.grant.title"/> ${user.aliasNm}(${user.id})</h2>
		<form id="roleGrantForm">
			<div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="user.grant.roleIds"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="roleIds" name="roleIds" value="${selectedRoleIds}"/>
		    	<input type="hidden" name="id" value="${user.id}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		      
		      <div class="row">
			    <div class="col-sm-3 col-sm-offset-5">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
		  			<button id="setting" type="button" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.setting"/></button>
		  			<button id="goBack" type="button" class="btn btn-primary glyphicon glyphicon-share-alt"> <spring:message code="common.goBack"/></button>
			    </div>
			</div>
		</form>
        <br/>
        <div class="table-responsive">
			<h3 class="sub-header"><spring:message code="user.grant.tableTitle"/></h3>
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
				needOpration:false,
				selectable:true,
				multiselect:true,
				relatedInput:'roleIds'
		};
		$(function(){
			Shaunz.generateTable(TableParam);
			$('#goBack').click(function(){
	        	Shaunz.load('./mngpages/account_lst.html');
	        });
			
			$('#roleGrantForm').validate({
	        	debug: true,
	        	onfocusout: true,
	        	rules: {
	        		selectedRoleIds: {
						required: true
					}
				},
				messages: {
					loginName: {
						required: '<spring:message code="validation.user.grant.roleIds.required"/>'
					}
				},
				errorPlacement: function(error, element) {
					var nextElement = element.parent().parent().find(".col-sm-4").find("span");
				    error.addClass("text-danger").insertAfter(nextElement);
				},
				submitHandler:function(form){
					Shaunz.submitForm(form,'./user/grant','POST');
				}
	  		});
		});
	</script>
</html>
