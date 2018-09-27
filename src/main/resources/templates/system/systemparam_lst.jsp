<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header"><spring:message code="source.title"/></h2>
       	<form id="systemParamForm">
          <div id="submitDiv" class="row">
			    <div class="col-sm-3 col-sm-offset-5">
			    	<button id="submit" type="submit" class="btn btn-primary glyphicon glyphicon-ok black"> <spring:message code="common.submit"/></button>
		  			<button type="reset" class="btn btn-primary glyphicon glyphicon-repeat"> <spring:message code="common.reset"/></button>
			    </div>
			</div>
		</form>
        <br/>
  </body>
  	<script type="text/javascript" >
  	$(function () {
  		var objs = [];
  		$.ajax({
  			url:'./source?groupNms=homepage&groupNms=System',
  			type:'GET',
  			success:function(data,status){
  				var result = jQuery.parseJSON(data);
  				$.each(result,function(indexx){
  					$.each(result[indexx],function(index){
  	  					$('#submitDiv').before(createSystemParam(result[indexx][index].name,result[indexx][index].value,result[indexx][index].groupNm,result[indexx][index].type));
  	  				});
  				});
  				$('div > input.form-control').change(function(){
  					var obj = {};
  					obj.name = $(this).attr('name');
  					obj.value = $(this).val();
  					obj.groupNm = $(this).next('input').val();
  					objs[objs.length] = obj;
  		  		});
  			},
  			error:function(e){
  				Shaunz.showError('Error',e);
  			}
  		});
  		
        $("form").submit(function(){
            return false;
        });
  		
  		$('#submit').click(function(){
  			$.each(objs,function(index,value){
  				Shaunz.ajaxRequest({'value':value.value},'./source/'+value.groupNm+'/'+value.name+'','PUT');
  			});
  			objs = [];
  		});
    });
  	
  	function createSystemParam(name,value,groupNm,type){
  		var row = $('<div class="form-group row"></div>');
  		row.append(createLabel(name));
  		row.append(createInput(name,value,groupNm,type));
  		row.append(createErrArea());
  		return row;
  	}
  	
  	function createLabel(name){
  		var titleAfterI18n = ''+name;
  		return '<label for="'+name+'" class="col-sm-2 col-form-label">'+titleAfterI18n+'</label>';
  	}
  	
  	function createInput(name,value,groupNm,type){
  		var div = $('<div class="input-group col-sm-6"></div>');
  		var placeholderAfterI18n = ''+name;
  		var input = '<input type="text" class="form-control" id="'+name+'" name="'+name+'" placeholder="'+placeholderAfterI18n+'" value="'+value+'"/>';
  		var group = '<input type="hidden" name="'+name+'_group'+'" value="'+groupNm+'"/>';
  		div.append(input);
  		div.append(group);
  		return div;
  	}
  	
  	function createErrArea(){
  		return $('<div class="col-sm-4"></div>').append('<span></span>');
  	}
	</script>
</html>
