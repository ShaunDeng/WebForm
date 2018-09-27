<%@ page language="java" contentType="text/html; charset=utf-8"  %>
<jsp:include page="../common/common.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="../common/header.jsp"/>
		<!-- Bootstrap core CSS -->
	    <link href="${projectResPath}/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body >
		<div class="page-header">
        <h1>ERROR No.${ReferenceNo}<</h1>
      </div>
      <div class="well">
        <p>${exception}</p>
      </div>
</html>