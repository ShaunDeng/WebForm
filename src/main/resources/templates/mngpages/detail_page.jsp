<%@ include file="../common/common.jsp"%>
<html lang="en">
	<body>
		<div class="row">
	          <table class="table table-bordered">
	            <thead>
	              <tr>
	                <th><spring:message code="common.FieldName"/></th>
	                <th><spring:message code="common.value"/></th>
	              </tr>
	            </thead>
	            <tbody id='detailPage'>
	            </tbody>
	          </table>
		</div>
	</body>
	<script type="text/javascript" >
		$(function(){
			$.ajax({
				url:'./object/${functionId}/${objId}',
				type:'GET',
				success:function(data,status){
					var result = jQuery.parseJSON(data);
					var length = 0;
					var tbodyHtml = '';
					$.each(result,function(name,value){
						length++;
						var html = '';
						//if(length%2 != 0){
							html += '<tr>';
						//}
						html += ('<td>'+jQuery.i18n.prop('dbField.'+name)+'</td>');
						html += ('<td>'+value+'</td>');
						//if(length%2 == 0){
							html += '</tr>';
						//}
						tbodyHtml += html;
					});
					$('#detailPage').html(tbodyHtml);
				},
				error:function(e){
					Shaunz.showError('Error',e);
				}
			});
		});
	</script>
</html>