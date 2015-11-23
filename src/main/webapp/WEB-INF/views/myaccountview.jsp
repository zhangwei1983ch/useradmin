<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<title>个人信息</title>
<link href="<c:url value="/resources/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<style>
html, body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}

.form-horizontal .control-group {
	margin-bottom: 16px;
}

.wrap {
	height: 100%;
	display: -webkit-box;
	-webkit-box-align: center;
	-webkit-box-pack: center;
	overflow: auto;
}

.main {
	width: 600px;
}

.info {
	margin-bottom: 20px;
	font-size: 21px;
	line-height: 40px;
	color: #333;
	border: 0;
	border-bottom: 1px solid #e5e5e5;
}
</style>
</head>
<body>
	<div class="">你正在访问 ${hostName}</div>

	<form class="form-horizontal" style="width: 80%;">
		<div class="form-group">
			<label class="col-sm-2 control-label">性别</label>
			<div class="col-sm-10">
				<c:choose>
					<c:when test="${user.gender == 'male'}">
						<p class="form-control-static">男</p>
					</c:when>
					<c:otherwise>
						<p class="form-control-static">女</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">姓名</label>
			<div class="col-sm-10">
				<p class="form-control-static">${user.username}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">办公电话</label>
			<div class="col-sm-10">
				<p class="form-control-static">${user.phone}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">邮箱</label>
			<div class="col-sm-10">
				<p class="form-control-static">${user.email}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地址</label>
			<div class="col-sm-10">
				<p class="form-control-static">${user.address}</p>
			</div>
		</div>
	</form>

	<button style="margin-top:30px;">
		<a href="logout" style="text-decoration: none; color: black;">注销</a>
	</button>

</body>
</html>