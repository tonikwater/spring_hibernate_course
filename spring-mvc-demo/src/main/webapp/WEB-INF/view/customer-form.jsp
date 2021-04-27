<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>


<head>
<title>Customer Registration</title>
<style type="text/css">
.error {color:red}
</style>
</head>


<body>

<i>Fill out the form. Asterisk (*) means required.</i>

<form:form action="processForm" modelAttribute="customer">

First name: <form:input path="firstName"/>
<br><br>
Last name (*): <form:input path="lastName"/>
<br><br>
<form:errors path="lastName" cssClass="error"/>
<br><br>
Free Passes: <form:input path="freePasses"/>
<br><br>
<form:errors path="freePasses" cssClass="error"/>
<br><br>
Postal Code: <form:input path="postalCode"/>
<br><br>
<form:errors path="postalCode" cssClass="error"/>
<br><br>
<input type="submit" value="Submit">

</form:form>


</body>



</html>