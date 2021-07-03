<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page - Yohoo - Nice man</h2>
	<hr>
	<p>
		Welcome to the luv2code company home page!
	</p>
	
	<hr>
	<!-- display username and role -->
	
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
		
	<security:authorize access="hasRole('MANAGER')">
		<!-- show link for page for managers ONLY for managers-->
		<p>
		<a href="${pageContext.request.contextPath}/leaders"> Leadership Meeting</a>
		(Only for Managers Peeps)
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<!-- show link for page for systems ONLY for admins -->
		<p>
			<a href="${pageContext.request.contextPath}/systems"> IT Systems Meeting</a>
			(Only for Managers Peeps)
		</p>
	</security:authorize>
	<hr>
	
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		
		<input type="submit" value="Logout">
	</form:form>
</body>

</html>