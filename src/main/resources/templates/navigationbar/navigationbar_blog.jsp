<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="user.grant.title"/> ${navigationBar.name}(${navigationBar.id})</h2>
		<form id="blogRelateForm">
			<div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="user.grant.roleIds"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="blogIds" name="blogIds" value="${selectedBlogIds}"/>
		    	<input type="hidden" name="id" value="${navigationBar.id}"/>
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
				header:['#','name','header','Post Time','Author'],
				column:['id','name','header','postTime','authorId'],
				url:'./blog',
				httpType:'GET',
				target:'table',
				needOpration:false,
				selectable:true,
				multiselect:true,
				relatedInput:'blogIds'
		};
		$(function(){
			Shaunz.generateTable(TableParam);
			$('#goBack').click(function(){
	        	Shaunz.load('./navigationbar/navigationbar_lst.html');
	        });
			$('#setting').click(function(){
				Shaunz.load('./blogmap/blog_order.html?pageId=${navigationBar.id}&pageType=tb_navigation_bar');
			});
			
			$('#blogRelateForm').validate({
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
					Shaunz.submitForm(form,'./navigationbar/relate','POST');
				}
	  		});
		});
	</script>
</html>
