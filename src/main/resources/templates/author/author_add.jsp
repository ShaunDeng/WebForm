<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="author.title"/></h2>
       	<form id="authorAddForm">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="author.userNm"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="author.userNm.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="email" class="col-sm-2 col-form-label"><spring:message code="author.email"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="<spring:message code="author.email.placeHolder"/>"/>
		    	<small id="emailHelp" class="form-text text-muted"><spring:message code="author.email.help"/></small>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="password" class="col-sm-2 col-form-label"><spring:message code="author.password"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="password" class="form-control" id="pwd" name="pwd" placeholder="<spring:message code="author.password.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="aliasName" class="col-sm-2 col-form-label"><spring:message code="author.aliasNm"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="aliasNm" name="aliasNm" placeholder="<spring:message code="author.aliasNm.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="gender" class="col-sm-2 col-form-label"><spring:message code="author.gender"/></label>
		    <div class="input-group col-sm-6">
		    	<select class="form-control" id="gender" name="gender">
				  <option selected><spring:message code="common.selectOption"/></option>
				  <option value="Male"><spring:message code="author.gender.male"/></option>
				  <option value="Female"><spring:message code="author.gender.female"/></option>
				</select>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
          <div class="form-group row">
		    <label for="lockUpN" class="col-sm-2 col-form-label"><spring:message code="author.lockUp"/></label>
		    <div class="input-group col-sm-6">
		    	<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="lockUp" id="lockUpY" value="Y"/>
				  <label class="form-check-label" for="lockUpY"><spring:message code="common.yes"/></label>
				  <input class="form-check-input" type="radio" name="lockUp" id="lockUpN" value="N" checked/>
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
        $('#datetimepickerFrom').datetimepicker();
        $('#datetimepickerTo').datetimepicker({
            useCurrent: false 
        });
        $("#datetimepickerFrom").on("dp.change", function (e) {
            $('#datetimepickerTo').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepickerTo").on("dp.change", function (e) {
            $('#datetimepickerFrom').data("DateTimePicker").maxDate(e.date);
        });
        
        $('#authorAddForm').validate({
        	debug: true,
        	onfocusout: true,
        	rules: {
				name: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				email: {
					required: true,
					email: true
				},
				pwd: {
					required: true,
					minlength: 8,
					maxlength: 100
				},
				aliasNm: {
					required: true,
					minlength: 2,
					maxlength: 100
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.author.name.required"/>',
					minlength: '<spring:message code="validation.author.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.author.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				email: '<spring:message code="validation.author.email"/>',
				pwd: {
					required: '<spring:message code="validation.author.password.required"/>',
					minlength: '<spring:message code="validation.author.password.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.author.password.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				aliasNm: {
					required: '<spring:message code="validation.author.aliasNm.required"/>',
					minlength: '<spring:message code="validation.author.aliasNm.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.author.aliasNm.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				}
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./author','POST');
				form.reset();
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./author/author_lst.html');
        });
    });
	</script>
</html>
