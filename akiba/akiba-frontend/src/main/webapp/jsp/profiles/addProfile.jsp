<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Kind</title>
<link href="/akiba-frontend/resources/css/bootstrap.css"
	rel="stylesheet">

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-addkind {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-addkind .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="/akiba-frontend/resources/js/bootstrap.min.js"></script>

	<div class="container">

		<form:form action="./add" method="POST" class="form-addkind">
			<div class="control-group">
				<div class="controls">
					<h2 class="form-signin-heading">Add Profile</h2>
				</div>
			</div>
			<div class="input-prepend input-append control-group">
				<div class="controls">
					<span class="add-on">Nazwa:</span>
					<form:input path="name" class="span2" />

				</div>
			</div>
			<div class="control-group">
			<c:forEach items="${currentProfiles}" var="profile">
				<span>${profile}</span>
			</c:forEach>
</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" value="Add Profile"
						class="btn btn-large btn-primary">Add Profile</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>