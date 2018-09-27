<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="user.grant.title"/> ${role.name}(${role.id})</h2>
  		<div class="row">
  		<form id="blogOrderForm">
  			<input type="hidden" value="${pageId}" name="pageId"/>
  			<input type="hidden" value="${pageType}" name="pageType"/>
  			<div class="row">
			    <div class="col-sm-12">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
			    	<button type="reset" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.reset"/></button>
			    </div>
			</div>
			<br/>
	          <table class="table table-bordered">
	            <thead>
	              <tr>
	                <th>Blog Order</th>
	                <th>Blog Id</th>
	                <th>Blog Name</th>
	              </tr>
	            </thead>
	            <tbody>
	            <c:forEach items = "${choosedBlogMaps}" var = "choosedBlogMap">
		            <tr>
		            	<td style="width: 90px;">
		            		<input type="input" style="width: 40px;" value="${choosedBlogMap.orderId}" name="${choosedBlogMap.blogId}_orderId"/>
		            	</td>
		            	<td style="width: 180px;">${choosedBlogMap.blogId}</td>
		            	<td>${choosedBlogMap.blogNm}</td>
	            	</tr>
	            </c:forEach>
	            </tbody>
	          </table>
          </form>
		</div>
  </body>
  	<script type="text/javascript" >
		$(function(){
			$('#blogOrderForm').validate({
	        	debug: false,
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
					Shaunz.submitForm(form,'./blogmap/order','POST');
				}
	  		});
		});
	</script>
</html>
