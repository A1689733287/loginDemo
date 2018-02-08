<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>密码</td>
			<td>操作</td>
		</tr>
		<s:iterator var="user" value="#request.users" status="vs">
			<tr>
				<td><s:property value="#user.id" /></td>
				<td><s:property value="#user.userName" /></td>
				<td><s:property value="#user.pwd" /></td>
				<td>
					<s:a href = "user_del.action?id =%{#user.id}">删除</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>