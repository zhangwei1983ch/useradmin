<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<title>Login Page</title>
</head>
<body onload='document.f.username.focus();'>
	<h3>Login with Username and Password</h3>
	<form name='f' action='login' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="登录" /></td>
				<td>
					<button>
						<a href="register" style="text-decoration: none; color: black;">注册</a>
					</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>