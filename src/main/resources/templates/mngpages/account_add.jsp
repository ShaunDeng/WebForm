<%@ include file="../common/common.jsp"%>
<html lang="en">
<style>
</style>
  <body>
  		<h2 class="sub-header"><spring:message code="user.title"/></h2>
       	<form id="accAddForm">
       	  <div class="form-group row">
		    <label for="loginName" class="col-sm-2 col-form-label"><spring:message code="user.userNm"/></label>
		    <div class="nopadding col-sm-6">
		    	<input type="text" class="form-control" id="loginName" name="loginName" 
		    		placeholder="<spring:message code="user.userNm.placeHolder"/>" required  maxlength="100"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		    <label for="email" class="col-sm-2 col-form-label"><spring:message code="user.email"/></label>
		    <div class="nopadding col-sm-6">
		    	<input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" 
		    		placeholder="<spring:message code="user.email.placeHolder"/>" required  maxlength="50"/>
		    	<small id="emailHelp" class="form-text text-muted"><spring:message code="user.email.help"/></small>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		    <label for="password" class="col-sm-2 col-form-label"><spring:message code="user.password"/></label>
		    <div class="nopadding col-sm-6">
		    	<input type="password" class="form-control" id="password" name="password" 
		    		placeholder="<spring:message code="user.password.placeHolder"/>" required  maxlength="100"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		    <label for="aliasName" class="col-sm-2 col-form-label"><spring:message code="user.aliasNm"/></label>
		    <div class="nopadding col-sm-6">
		    	<input type="text" class="form-control" id="aliasName" name="aliasNm" 
		    		placeholder="<spring:message code="user.aliasNm.placeHolder"/>" required  maxlength="100"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		    <label for="gender" class="col-sm-2 col-form-label"><spring:message code="user.gender"/></label>
		    <div class="nopadding col-sm-6">
		    	<select class="form-control" id="gender" name="gender">
				  <option selected><spring:message code="common.selectOption"/></option>
				  <option value="Male"><spring:message code="user.gender.male"/></option>
				  <option value="Female"><spring:message code="user.gender.female"/></option>
				</select>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		    <label for="orgPath" class="col-sm-2 col-form-label"><spring:message code="user.organization"/></label>
		    <div class="nopadding col-sm-6">
		    	<input type="text" class="form-control" id="orgPath" name="orgPath" aria-describedby="emailHelp" 
		    		placeholder="<spring:message code="user.organization.placeHolder"/>" maxlength="100">
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		  	<label for="startTime" class="col-sm-2 col-form-label"><spring:message code="user.startTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerFrom'>
               	<input type="text" class="form-control" id="startTime" name="startTime" placeholder="<spring:message code="user.startTime.placeHolder"/>"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <div class="col-sm-4">
		    	<span></span>
		    </div>
          </div>
          <br/>
          <div class="form-group row">
          	<label for="endTime" class="col-sm-2 col-form-label"><spring:message code="user.endTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerTo'>
               	<input type="text" class="form-control" id="endTime" name="endTime" placeholder="<spring:message code="user.endTime.placeHolder"/>"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <div class="col-sm-4">
		    	<span></span>
		    </div>
          </div>
          <br/>
          <div class="form-group row">
		    <label for="lockUpN" class="col-sm-2 col-form-label"><spring:message code="user.lockUp"/></label>
		    <div class="nopadding col-sm-6">
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
		  <br/>
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
        
        $('#accAddForm').validate({
        	debug: false,
        	onfocusout: true,
        	rules: {
				loginName: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				email: {
					required: true,
					email: true
				},
				password: {
					required: true,
					minlength: 8,
					maxlength: 100
				},
				aliasNm: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				orgPath: {
					maxlength: 100
				}
			},
			messages: {
				loginName: {
					required: '<spring:message code="validation.user.loginName.required"/>',
					minlength: '<spring:message code="validation.user.loginName.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.user.loginName.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				email: '<spring:message code="validation.user.email"/>',
				password: {
					required: '<spring:message code="validation.user.password.required"/>',
					minlength: '<spring:message code="validation.user.password.minlength" arguments="8;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.user.password.maxlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				aliasNm: {
					required: '<spring:message code="validation.user.aliasNm.required"/>',
					minlength: '<spring:message code="validation.user.aliasNm.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.user.aliasNm.maxlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				orgPath: {
					maxlength: '<spring:message code="validation.user.orgPath" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				}
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./user','POST');
				form.reset();
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./mngpages/account_lst.html');
        });
    });
	</script>
</html>
