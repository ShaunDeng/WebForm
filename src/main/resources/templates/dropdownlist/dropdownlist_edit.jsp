<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="dropdownlist.title"/></h2>
       	<form id="dropDownListEditForm" enctype="multipart/form-data">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="dropdownlist.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="dropdownlist.name.placeHolder"/>" value="${dropdownlist.name}"/>
		    	<input type="hidden" name="id" value="${dropdownlist.id}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="dropdownlist.url"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="url" name="url" placeholder="<spring:message code="dropdownlist.url.placeHolder"/>" value="${dropdownlist.url}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="typeNormal" class="col-sm-2 col-form-label"><spring:message code="dropdownlist.type"/></label>
		    <div class="input-group col-sm-6">
		    	<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="type" id="typeNormal" value="normal" <c:if test="${dropdownlist.type eq 'normal'}">checked</c:if>/>
				  <label class="form-check-label" for="typeNormal"><spring:message code="dropdownlist.type.normal"/></label>
				  <input class="form-check-input" type="radio" name="type" id="typeSeparator" value="separator" <c:if test="${dropdownlist.type eq 'separator'}">checked</c:if>/>
				  <label class="form-check-label" for="typeSeparator"><spring:message code="dropdownlist.type.separator"/></label>
				  <input class="form-check-input" type="radio" name="type" id="typeDropdown_header" value="dropdown_header" <c:if test="${dropdownlist.type eq 'dropdown_header'}">checked</c:if>/>
				  <label class="form-check-label" for="typeDropdown_header"><spring:message code="dropdownlist.type.dropdown_header"/></label>
				</div>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="dropdownlist.parentId"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="parentId" name="parentId" placeholder="<spring:message code="dropdownlist.parentId.placeHolder"/>" value="${dropdownlist.parentId}"/>
		    	<span class="input-group-addon">
                    <span class="glyphicon glyphicon-th pop-picker"></span>
                </span>
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
  	var inputIdFunctionMap = {
  			'parentId':'5'
  	}
  	
  	$(function () {
  		Shaunz.preparePopChooseBox(inputIdFunctionMap);
        $('#dropDownListEditForm').validate({
        	debug: true,
        	onfocusout: false,
        	rules: {
				name: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				url: {
					required: true
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.button.name.required"/>',
					minlength: '<spring:message code="validation.button.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.button.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				url: '<spring:message code="validation.button.cssclass.required"/>'
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./dropdownlist','PUT');
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./dropdownlist/dropdownlist_lst.html');
        });
    });
	</script>
</html>
