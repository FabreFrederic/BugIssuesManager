<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search for issues</title>
	</head>
	<body>
		<frm:form method="POST" action="/bim/issueSearchResult" modelAttribute="issueForm">
			<table>
				<tr>
					<td><frm:label path="name">Name</frm:label></td>
					<td><frm:input path="name" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</frm:form>
	</body>
</html>