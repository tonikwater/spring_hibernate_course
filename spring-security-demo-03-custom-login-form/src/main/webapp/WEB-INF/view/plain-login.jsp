<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Custom Login Page</title>
		<style>
		
			.failed {
				color: red;
			}
		
		</style>
	</head>

	<body>
		<h3>My Custom Login Form</h3>
		<form:form action="${pageContext.request.c ontextPath}/authenticateTheUser"
			method="POST">
			
			<!-- check for login error -->
			<c:if test="${param.error != null}">
				<i class="failed">Sorry you entered an invalid username or password</i>
			</c:if>
			
			
			<!-- the spring security filters will read values ".username" and
				".password" values from post req fired by this form  -->
			<p>
				User name: <input type="text" name="username"/>
			</p>
			<p>
				Password: <input type="password" name="password"/>
			</p>
			<input type="submit" value="Login">
			</form:form>
	
	</body>
</html>