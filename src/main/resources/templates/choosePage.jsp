<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="https://www.pollix.at/thymeleaf/shiro">
  <body>
        <div class="table-responsive">
        	<input type="hidden" class="form-control" id="ChooseInput" name="ChooseInput" th:value="${selectedIds}"/>
          <table id="ChooseTable" class="table table-striped" style="width:100%">
          </table>
        </div>
  </body>
  	<script type="text/javascript" th:inline="javascript">
		var TableParam = {
				header:[[${tableHeader}]],
				column:[[${tableColumn}]],
				url:'./choose/${functionId}',
				httpType:'GET',
				target:'ChooseTable',
				needOpration:false,
				selectable:true,
				multiselect:[[${multiselect}]],
				relatedInput:'ChooseInput'
		};
		$(function(){
			Shaunz.generateTable(TableParam);
		});
	</script>
</html>
