<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="user.grant.title"/> ${role.name}(${role.id})</h2>
  		<div class="row">
  		<form id="roleGrantForm">
  			<input type="hidden" value="${role.id}" name="id"/>
  			<div class="row">
			    <div class="col-sm-12">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
			    	<button type="reset" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.reset"/></button>
		  			<button id="setting" type="button" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.setting"/></button>
		  			<button id="goBack" type="button" class="btn btn-primary glyphicon glyphicon-share-alt"> <spring:message code="common.goBack"/></button>
			    </div>
			</div>
			<br/>
	          <table class="table table-bordered">
	            <thead>
	              <tr>
	                <th>#</th>
	                <th>Function Name</th>
	                <th>Query</th>
	                <th>Add</th>
	                <th>Edit</th>
	                <th>Delete</th>
	              </tr>
	            </thead>
	            <tbody>
	            <c:forEach items = "${functions}" var = "function">
		            <tr>
		            	<td>${function.id}</td>
		            	<c:choose>
		            		<c:when test="${not empty function.grantedAuthority}">
		            			<td><input type="checkbox" id="${function.id}" name="${function.id}" value="${function.id}" checked/>${function.name}</td>
		            			<c:set var = "add" value = "false"/>
		            			<c:set var = "delete" value = "false"/>
		            			<c:set var = "update" value = "false"/>
		            			<c:set var = "query" value = "false"/>
		            			<c:forEach items = "${function.grantedAuthority}" var = "authority">
		            				<c:choose>
		            					<c:when test="${authority eq '4'}">
		            						<c:set var = "query" value = "true"/>
		            					</c:when>
		            					<c:when test="${authority eq '1'}">
		            						<c:set var = "add" value = "true"/>
		            					</c:when>
		            					<c:when test="${authority eq '3'}">
		            						<c:set var = "update" value = "true"/>
		            					</c:when>
		            					<c:when test="${authority eq '2'}">
		            						<c:set var = "delete" value = "true"/>
		            					</c:when>
		            				</c:choose>
		            			</c:forEach>
		            			<td><input type="checkbox" id="${function.id}_query" name="${function.id}_query" value="${function.id}_query" <c:if test="${query eq true}">checked</c:if>/></td>
				            	<td><input type="checkbox" id="${function.id}_add" name="${function.id}_add" value="${function.id}_add" <c:if test="${add eq true}">checked</c:if>/></td>
				            	<td><input type="checkbox" id="${function.id}_update" name="${function.id}_update" value="${function.id}_update" <c:if test="${update eq true}">checked</c:if>/></td>
				            	<td><input type="checkbox" id="${function.id}_delete" name="${function.id}_delete" value="${function.id}_delete" <c:if test="${delete eq true}">checked</c:if>/></td>
		            		</c:when>
		            		<c:otherwise>
		            			<td><input type="checkbox" id="${function.id}" name="${function.id}" value="${function.id}"/>${function.name}</td>
				            	<td><input type="checkbox" id="${function.id}_query" name="${function.id}_query" value="${function.id}_query" /></td>
				            	<td><input type="checkbox" id="${function.id}_add" name="${function.id}_add" value="${function.id}_add" /></td>
				            	<td><input type="checkbox" id="${function.id}_update" name="${function.id}_update" value="${function.id}_update" /></td>
				            	<td><input type="checkbox" id="${function.id}_delete" name="${function.id}_delete" value="${function.id}_delete" /></td>
		            		</c:otherwise>
		            	</c:choose>
	            	</tr>
	            </c:forEach>
	            </tbody>
	          </table>
          </form>
		</div>
  </body>
  	<script type="text/javascript" >
		$(function(){
			$('#roleGrantForm :checkbox').change(function() {
				var id = $(this).attr('id');
				var parentCheckBox = id.indexOf('_')<0;
			    if (this.checked) {
			        if(parentCheckBox){
			        	$('#'+id+"_query").prop( "checked", true );
		  				$('#'+id+"_add").prop( "checked", true );
		  				$('#'+id+"_update").prop( "checked", true );
		  				$('#'+id+"_delete").prop( "checked", true );
			        } else {
			        	var parentId = id.split('_')[0];
			        	$('#'+parentId).prop( "checked", true );
			        }
			        
			    } else {
			    	if(parentCheckBox){
			    		$('#'+id+"_query").prop( "checked", false );
		  				$('#'+id+"_add").prop( "checked", false );
		  				$('#'+id+"_update").prop( "checked", false );
		  				$('#'+id+"_delete").prop( "checked", false );
			    	} else {
			    		var parentId = id.split('_')[0];
			    		if(($('#'+parentId+"_query").is(':checked'))
		  						||($('#'+parentId+"_add").is(':checked'))
		  						||($('#'+parentId+"_update").is(':checked'))
		  						||($('#'+parentId+"_delete").is(':checked'))){
			    			$('#'+parentId).prop( "checked", true );
		  				} else {
		  					$('#'+parentId).prop( "checked", false );
		  				}
			    	}
			    }
			});
			
			$('#roleGrantForm').validate({
	        	debug: true,
	        	onfocusout: true,
	        	rules: {
				},
				messages: {
				},
				errorPlacement: function(error, element) {
					var nextElement = element.parent().parent().find(".col-sm-4").find("span");
				    error.addClass("text-danger").insertAfter(nextElement);
				},
				submitHandler:function(form){
					console.log($(form).serialize());
					Shaunz.submitForm(form,'./role/grant','POST');
				}
	  		});
			$('#goBack').click(function(){
	        	Shaunz.load('./mngpages/role_lst.html');
	        });
		});
	</script>
</html>
