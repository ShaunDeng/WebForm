<%@ include file="./common/common.jsp"%>
<html lang="en">
  <head>
    <%@ include file="./common/header.jsp"%>

    <!-- Bootstrap core CSS -->
    <link href="${webResPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${projectResPath}/css/dashboard.css" rel="stylesheet">
    <link href="${webResPath}/css/bootstrap-treeview.min.css" rel="stylesheet">
    <link href="${webResPath}/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${webResPath}/css/select.dataTables.min.css" rel="stylesheet">
    <link href="${webResPath}/css/dataTables.checkboxes.css" rel="stylesheet">
    <link href="${webResPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${webResPath}/css/jquery-confirm.min.css" rel="stylesheet">
    <link href="${webResPath}/css/shaunz.css" rel="stylesheet">
    <link href="${webResPath}/css/bootstrap3-wysihtml5.min.css" rel="stylesheet">
    <link href="${webResPath}/css/image-picker.css" rel="stylesheet">
    <%@ include file="./common/jsReference.jsp"%>
  </head>
	<script type="text/javascript" > 
		$('document').ready(function(){
			$.ajax({
				   url: './functions',
				   type: 'GET',
				   success: function(data,status) {
					   $('#TreeMenu').treeview(jQuery.parseJSON(data));
					   $('#TreeMenu').treeview('collapseAll', { silent: true });
				   },
				   error: function(e) {
					   console.log(e);
				   }
				});
			Shaunz.load('./mngpages/dashboard.html');
			
			$.ajax({
				url:'./crrentUser',
				type:'GET',
				success: function(data,status) {
					   var result = jQuery.parseJSON(data);
					   $('#userInterface').append(result.aliasNm);
					   $('#userName').append(result.aliasNm);
			   	},
			    error: function(e) {
				   console.log(e);
			    }
			});
			$('#userSettings').hide();
			$('#userInterface').click(function(){
				if($('#userSettings').is(":visible")){
					$('#userSettings').fadeOut("slow");
				} else {
					$('#userSettings').fadeIn("slow");
				}
			});
			$('#Dashboard').click(function(){
				Shaunz.load('./mngpages/dashboard.html');
			});
		});
	</script>
  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${applicationScope.homePageObject.homeUrl}"><i class="glyphicon glyphicon-home"></i> ${applicationScope.homePageObject.projectNm}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a id="Dashboard" href="#">Dashboard</a></li>
            <li><a href="#">Help</a></li>
            <li></i><a id="userInterface" href="#"><i class="glyphicon glyphicon-user"></i> </a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar nopadding">
        	<div id="TreeMenu"></div>
        	<div></div>
        </div>
        <div id="FeatureContainer" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"></div>
        <div id="userSettings" class="settings">
        	<div class="container-fluid">
        		<div class="row col-sm-offset-2 col-md-offset-3"><i class="glyphicon glyphicon-user gi-5x"></i></div>
        		<div class="row col-sm-offset-2 col-md-offset-3">
        			<div id="userName" class="gi-2x"></div>
        		</div>
        		<br/>
        		<div class="row">
        			<div class="row">
	        			<a href="#"><i class="glyphicon glyphicon-file"></i> Profile</a>
				        <br/>
				        <a href="#"><i class="glyphicon glyphicon-file"></i> Change Password</a>
				        <br/>
				        <br/>
	        			<a href="./signout"><i class="glyphicon glyphicon-log-out"></i> SignOut</a>
			        </div>
        		</div>
        	</div>
        </div>
      </div>
    </div>
  </body>
</html>
