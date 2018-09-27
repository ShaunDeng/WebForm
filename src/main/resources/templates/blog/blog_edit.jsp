<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="blog.title"/></h2>
       	<form id="blogEditForm" enctype="multipart/form-data">
       	  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label"><spring:message code="blog.name"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="blog.name.placeHolder"/>" value="${blog.name}"/>
		    	<input type="hidden" name="id" value="${blog.id}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="header" class="col-sm-2 col-form-label"><spring:message code="blog.headerType"/></label>
		    <div class="input-group col-sm-6">
		    	<select class="form-control" id="headerType" name="headerType">
				  <option value="h1" <c:if test="${blog.headerType eq 'h1'}">selected</c:if>><spring:message code="blog.headerType.h1"/></option>
				  <option value="h2" <c:if test="${blog.headerType eq 'h2'}">selected</c:if>><spring:message code="blog.headerType.h2"/></option>
				  <option value="h3" <c:if test="${blog.headerType eq 'h3'}">selected</c:if>><spring:message code="blog.headerType.h3"/></option>
				  <option value="h4" <c:if test="${blog.headerType eq 'h4'}">selected</c:if>><spring:message code="blog.headerType.h4"/></option>
				  <option value="h5" <c:if test="${blog.headerType eq 'h5'}">selected</c:if>><spring:message code="blog.headerType.h5"/></option>
				</select>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="header" class="col-sm-2 col-form-label"><spring:message code="blog.header"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="header" name="header" placeholder="<spring:message code="blog.header.placeHolder"/>" value="${blog.header}"/>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<label for="postTime" class="col-sm-2 col-form-label"><spring:message code="blog.postTime"/></label>
            <div class='input-group date col-sm-6' id='datetimepickerPostTime'>
               	<input type="text" class="form-control" id="postTime" name="postTime" placeholder="<spring:message code="blog.postTime.placeHolder"/>" value="${blog.postTime}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <div class="col-sm-4">
		    	<span></span>
		    </div>
          </div>
		  <div class="form-group row">
		    <label for="authorId" class="col-sm-2 col-form-label"><spring:message code="blog.authorId"/></label>
		    <div class="input-group col-sm-6">
		    	<input type="text" class="form-control" id="authorId" name="authorId" placeholder="<spring:message code="blog.authorId.placeHolder"/>" value="${blog.authorId}"/>
		    	<span class="input-group-addon">
                    <span class="glyphicon glyphicon-th pop-picker"></span>
                </span>
		    </div>
		    <div class="col-sm-4">
		    	<span></span>
		    </div>
		  </div>
		  <br/>
		  <div class="form-group row">
		  	<label for="cssClass" class="col-sm-2 col-form-label"><spring:message code="blog.content"/></label>
		  	<div class="input-group col-sm-6">
	  		    <textarea id="content" name ="content" class="form-control" rows="20" placeholder="<spring:message code="blog.content.placeHolder"/>" value="">${blog.content}</textarea>
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
  			'authorId':'14'
  	}
  	$(function () {
  		Shaunz.preparePopChooseBox(inputIdFunctionMap);
  		$('#datetimepickerPostTime').datetimepicker({
            useCurrent: true 
        });
  		$('#content').wysihtml5();
        $('#blogEditForm').validate({
        	debug: false,
        	onfocusout: false,
        	rules: {
				name: {
					maxlength: 100
				},
				header: {
					maxlength: 50
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
				Shaunz.submitForm(form,'./blog','PUT');
			}
  		});
        
        $('#goBack').click(function(){
        	Shaunz.load('./blog/blog_lst.html');
        });
    });
	</script>
</html>
