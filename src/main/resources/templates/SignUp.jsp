<%@ include file="./common/common.jsp"%>
<html>
<head>
	<%@ include file="./common/header.jsp"%>

    <!-- Bootstrap core CSS -->
    <link href="${projectResPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${projectResPath}/css/carousel.css" rel="stylesheet">
    <link href="${projectResPath}/css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
      <form class="form-signin" onsubmit="/signIn" method="post">
        <h2 class="form-signin-heading">Sign Up</h2>
        <label for="inputEmail" class="sr-only"><spring:message code="user.email"/></label>
        <input type="email" id="inputUserNM" name="inputUserNM" class="form-control" placeholder="<spring:message code="user.email.placeHolder"/>" required autofocus>
        <label for="inputPassword" class="sr-only"><spring:message code="user.password"/></label>
        <input type="password" id="inputPwd" name="inputPwd" class="form-control" placeholder="<spring:message code="user.password.placeHolder"/>" required>
        <label for="aliasNm" class="sr-only"><spring:message code="user.aliasNm"/></label>
        <input type="input" id="aliasNm" name="aliasNm" class="form-control" placeholder="<spring:message code="user.aliasNm.placeHolder"/>">
        <label for="gender" class="sr-only"><spring:message code="user.gender"/></label>
        <select class="form-control" id="gender" name="gender">
		  <option selected><spring:message code="common.selectOption"/></option>
		  <option value="Male"><spring:message code="user.gender.male"/></option>
		  <option value="Female"><spring:message code="user.gender.female"/></option>
		</select>
		<br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
      </form>

    </div> <!-- /container -->
</body>
</html>