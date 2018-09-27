<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="carousel.title"/></h2>
       	<form id="carouselAddForm" enctype="multipart/form-data">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="carousel.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="carousel.name.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="header" class="col-sm-2 col-form-label"><spring:message code="carousel.header"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="header" name="header" placeholder="<spring:message code="carousel.header.placeHolder"/>"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  
		  <div class="form-group row">
		    <label for="img_id" class="col-sm-2 col-form-label"><spring:message code="carousel.image"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="img_id" name="img_id" placeholder="<spring:message code="carousel.image.placeHolder"/>"/>
		    	<span class="input-group-addon">
                    <span class="glyphicon glyphicon-th pop-picker"></span>
                </span>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="buttonId" class="col-sm-2 col-form-label"><spring:message code="carousel.button"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="buttonId" name="buttonId" placeholder="<spring:message code="carousel.button.placeHolder"/>"/>
		    	<span class="input-group-addon">
                    <span class="glyphicon glyphicon-th pop-picker"></span>
                </span>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="authorId" class="col-sm-2 col-form-label"><spring:message code="carousel.author"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="authorId" name="authorId" placeholder="<spring:message code="carousel.author.placeHolder"/>"/>
		    	<span class="input-group-addon">
                    <span class="glyphicon glyphicon-th pop-picker"></span>
                </span>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  
  		  <div class="form-group row">
		  	<label for="postTime" class="col-sm-2 col-form-label"><spring:message code="carousel.postTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerPostTime'>
               	<input type="text" class="form-control" id="postTime" name="postTime"/>
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
		  	<label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="carousel.content"/></label>
		  	<div class="input-group col-sm-6">
	  		    <textarea id="content" name ="content" class="form-control" rows="10" placeholder="<spring:message code="carousel.content.placeHolder"/>"></textarea>
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
  			'buttonId':'9',
  			'img_id':'10',
  			'authorId':'14'
  	}
  	$(function () {
  		Shaunz.preparePopChooseBox(inputIdFunctionMap);
  		$('#datetimepickerPostTime').datetimepicker({
            useCurrent: true 
        });
  		$('#content').wysihtml5();
        $('#carouselAddForm').validate({
        	debug: false,
        	onfocusout: false,
        	rules: {
				name: {
					maxlength: 100
				},
				header: {
					maxlength: 50
				},
				img_id: {
					maxlength: 10
				},
				buttonId: {
					maxlength: 10
				},
				authorId: {
					maxlength: 10
				},
				content:{
					maxlength: 2000
				}
			},
			messages: {
				name: {
					required: '<spring:message code="validation.button.name.required"/>',
					minlength: '<spring:message code="validation.button.name.minlength" arguments="2;" htmlEscape="false" argumentSeparator=";"/>',
					maxlength: '<spring:message code="validation.button.name.minlength" arguments="100;" htmlEscape="false" argumentSeparator=";"/>'
				},
				header: '<spring:message code="validation.button.cssclass.required"/>'
			},
			errorPlacement: function(error, element) {
				var nextElement = element.parent().parent().find(".col-sm-4").find("span");
			    error.addClass("text-danger").insertAfter(nextElement);
			},
			submitHandler:function(form){
				
				Shaunz.submitForm(form,'./carousel','POST');
				form.reset();
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./carousel/carousel_lst.html');
        });
    });
	</script>
</html>
