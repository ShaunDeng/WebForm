<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header">Function</h2>
        <br/>
        <div class="table-responsive">
          <table id="table" class="table table-striped" style="width:100%">
          </table>
        </div>
  </body>
  	<script type="text/javascript" >
		var TableParam = {
				header:['#','Function Name','Parent Id','URL','Table Name'],
				column:['id','name','parentId','url','tableNm'],
				url:'./function',
				httpType:'GET',
				target:'table',
				needOpration:true,
				operations:['detail'],
				methods:[function(obj){
					Shaunz.showDetail('13',obj.data.id);
				}]
		};
		$(function(){
			Shaunz.generateTable(TableParam);
		});
	</script>
</html>
