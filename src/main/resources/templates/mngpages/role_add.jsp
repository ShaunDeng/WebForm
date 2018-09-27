<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="role.title"/></h2>
       	<form id="roleAddForm">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="role.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="role.name.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="parentId" class="col-sm-2 col-form-label"><spring:message code="role.parentId"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="parentId" name="parentId" aria-describedby="emailHelp" placeholder="<spring:message code="role.parentId.placeHolder"/>">
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<label for="startTime" class="col-sm-2 col-form-label"><spring:message code="role.startTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerFrom'>
               	<input type="text" class="form-control" id="startTime" name="startTime" placeholder="<spring:message code="role.startTime.placeHolder"/>"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <div class="col-sm-4">
		    	<span></span>
		    </div>
          </div>
          <div class="form-group row">
          	<label for="endTime" class="col-sm-2 col-form-label"><spring:message code="role.endTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerTo'>
               	<input type="text" class="form-control" id="endTime" name="endTime" placeholder="<spring:message code="role.endTime.placeHolder"/>"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
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
        
        $('#roleAddForm').validate({
        	debug: true,
        	onfocusout: true,
        	rules: {
        		name: {
					required: true,
					minlength: 2,
					maxlength: 100
				}
			},
			messages: {
				loginName: {
					required: '<spring:message code="validation.role.name.required"/>',
					minlength: '<spring:message code="validation.role.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.role.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				}
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.submitForm(form,'./role','POST');
				form.reset();
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./mngpages/role_lst.html');
        });
    });
	</script>
</html>
