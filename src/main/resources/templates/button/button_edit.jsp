<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="button.title"/></h2>
       	<form id="buttonEditForm" enctype="multipart/form-data">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="button.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="button.name.placeHolder"/>" value="${button.name}"/>
		    	<input type="hidden" name="id" value="${button.id}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="button.cssclass"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="cssClass" name="cssClass" placeholder="<spring:message code="button.cssclass.placeHolder"/>" value="${button.cssClass}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="url" class="col-sm-2 col-form-label"><spring:message code="button.url"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="url" name="url" placeholder="<spring:message code="button.url.placeHolder"/>" value="${button.url}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
          <div class="row">
			    <div class="col-sm-3 col-sm-offset-5">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
		  			<button type="reset" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.reset"/></button>
		  			<button id="goBack" type="button" class="btn btn-primary glyphicon glyphicon-share-alt"> <spring:message code="common.goBack"/></button>
			    </div>
			</div>
		</form>
        <br/>
  </body>
  	<script type="text/javascript" >
  	
  	$(function () {
        $('#buttonEditForm').validate({
        	debug: true,
        	onfocusout: true,
        	rules: {
				name: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				cssClass: {
					required: true
				},
				url:{
					required: true
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.button.name.required"/>',
					minlength: '<spring:message code="validation.button.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.button.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				cssClass: '<spring:message code="validation.button.cssclass.required"/>',
				url: '<spring:message code="validation.button.url.required"/>'
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./button','PUT');
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./button/button_lst.html');
        });
    });
	</script>
</html>
