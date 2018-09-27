<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="navigationbar.title"/></h2>
       	<form id="navigationBarAddForm">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="navigationbar.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="navigationbar.name.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
  		  <div class="form-group row">
		    <label for="type" class="col-sm-2 col-form-label"><spring:message code="navigationbar.type"/></label>
		    <div class="input-group col-sm-6">
		    	<select class="form-control" id="type" name="type">
				  <option value="normal" selected><spring:message code="navigationbar.type.normal"/></option>
				  <option value="dropdown"><spring:message code="navigationbar.type.dropdown"/></option>
				</select>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="url" class="col-sm-2 col-form-label"><spring:message code="navigationbar.url"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="url" name="url" placeholder="<spring:message code="navigationbar.url.placeHolder"/>" value="./navBar.html"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
          <div class="form-group row">
		    <label for="activeN" class="col-sm-2 col-form-label"><spring:message code="navigationbar.active"/></label>
		    <div class="input-group col-sm-6">
		    	<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="active" id="activeY" value="active"/>
				  <label class="form-check-label" for="lockUpY"><spring:message code="common.yes"/></label>
				  <input class="form-check-input" type="radio" name="active" id="activeN" value="inactive" checked/>
				  <label class="form-check-label" for="lockUpN"><spring:message code="common.no"/></label>
				</div>
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
        $('#navigationBarAddForm').validate({
        	debug: true,
        	onfocusout: true,
        	rules: {
				name: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				type: {
					required: true
				},
				url: {
					required: true
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.navigationbar.name.required"/>',
					minlength: '<spring:message code="validation.navigationbar.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.navigationbar.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				type: '<spring:message code="validation.navigationbar.type.required"/>',
				url: {
					required: '<spring:message code="validation.navigationbar.url.required"/>'
				}
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./navigationbar','POST');
				form.reset();
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./navigationbar/navigationbar_lst.html');
        });
    });
	</script>
</html>
