<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Results</title>
	</head>
	<body>
		<h2>List of issues</h2>
		<core:if test="${not empty issueList}">
			<ul>
				<core:forEach var="issueValue" items="${issueList}">
					<li>${issueValue.name}</li>
				</core:forEach>
			</ul>
		</core:if>
	</body>
</html>