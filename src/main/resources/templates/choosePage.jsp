<%@ include file="./common/common.jsp"%>
<html lang="en">
  <body>
        <div class="table-responsive">
        	<input type="hidden" class="form-control" id="ChooseInput" name="ChooseInput" value="${selectedIds}"/>
          <table id="ChooseTable" class="table table-striped" style="width:100%">
          </table>
        </div>
  </body>
  	<script type="text/javascript" >
		var TableParam = {
				header:${tableHeader},
				column:${tableColumn},
				url:'./choose/${functionId}',
				httpType:'GET',
				target:'ChooseTable',
				needOpration:false,
				selectable:true,
				multiselect:${multiselect},
				relatedInput:'ChooseInput'
		};
		$(function(){
			Shaunz.generateTable(TableParam);
		});
	</script>
</html>
