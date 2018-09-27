<%@ include file="../common/common.jsp"%>
<html lang="en">
  <body>
  		<h2 class="sub-header">System Log</h2>
        <br/>
        <div class="table-responsive">
          <table id="table" class="table table-striped" style="width:100%">
          </table>
        </div>
  </body>
  	<script type="text/javascript" >
		var TableParam = {
				header:['#','Operator','Function','Opration Type','Time'],
				column:['id','userId','functionId','optType','optTime'],
				url:'./sysLog',
				httpType:'GET',
				target:'table',
				needOpration:false
		};
		$(function(){
			Shaunz.generateTable(TableParam);
		});
	</script>
</html>
