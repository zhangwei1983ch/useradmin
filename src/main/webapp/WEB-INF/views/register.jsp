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
	<div class="">你正在访问&nbsp;${hostName}</div>
	<div class="wrap">
		<form class="main form-horizontal" onsubmit="return check()"
			action="updateUser" method="post">
			<fieldset>
				<legend>个人信息</legend>
				<div class="control-group">
					<label class="control-label">姓名</label>
					<div class="controls">
						<input name="username" type="text" placeholder="必填项，实名"
							class="input-xlarge" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码</label>
					<div class="controls">
						<input name="password" type="password" placeholder="必填项，密码"
							class="input-xlarge" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别</label>
					<div class="controls">
						<label class="radio"> <input type="radio" value="male"
							name="gender" checked="checked">男
						</label> <label class="radio"> <input type="radio" value="female"
							name="gender">女
						</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">办公电话</label>
					<div class="controls">
						<input name="phone" type="text" placeholder="必填项"
							class="input-xlarge" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮箱</label>
					<div class="controls">
						<input name="email" type="text" placeholder="必填项"
							class="input-xlarge" required
							pattern="^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$"
							title="">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">地址</label>
					<div class="controls">
						<input name="address" type="text" placeholder="必填项"
							class="input-xlarge" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"> </label>
					<div class="controls">
						<button class="btn" type="submit" id="ok">提交</button>
						<button class="btn" type="reset">重置</button>
					</div>
				</div>
			</fieldset>
			<input type="hidden" name="_csrf" value="${XSRF}">
		</form>
	</div>

	<script>
		function check() {

			return true;
		}
	</script>

</body>
</html>