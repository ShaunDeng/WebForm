<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="image.title"/></h2>
       	<form id="imageEditForm" enctype="multipart/form-data">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="image.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="image.name.placeHolder"/>" value="${image.name}"/>
		    	<input type="hidden" name="id" value = "${image.id}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="image.cssclass"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="cssClass" name="cssClass" placeholder="<spring:message code="image.cssclass.placeHolder"/>" value="${image.cssClass}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<label class="col-sm-2 col-form-label"><spring:message code="image.image"/></label>
            <div class="input-group col-sm-2"> 
                <div class="img-picker">
                </div>
            </div>
            <div class="col-sm-8">
		    	<span></span>
		    </div>
          </div>
          <div class="row">
			    <div class="col-sm-3 col-sm-offset-5">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
		  			<button id="reset" type="reset" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.reset"/></button>
		  			<button id="goBack" type="button" class="btn btn-primary glyphicon glyphicon-share-alt"> <spring:message code="common.goBack"/></button>
			    </div>
			</div>
		</form>
        <br/>
  </body>
  	<script type="text/javascript" >
  	$(function () {
  		var srcUrl = '${image.url}';
  		if(srcUrl.indexOf("/") == 0){
  			srcUrl = '${ctxPath}' +srcUrl;
  		}
  		$('.img-picker').imageUploader({name: 'file',preview: true,src:srcUrl});
        $('#imageEditForm').validate({
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
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.navigationbar.name.required"/>',
					minlength: '<spring:message code="validation.navigationbar.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.navigationbar.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				cssClass: '<spring:message code="validation.navigationbar.type.required"/>'
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				Shaunz.SubmitMultipartForm(form,'./image/edition','POST');
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./image/image_lst.html');
        });
        $('#reset').click(function(){
        	$('.img-picker').imagePicker({name: 'file',preview: true,src:srcUrl});
        });
    });
	</script>
</html>
