<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  


<!DOCTYPE html>

<html>
	<head>
		<title>List Customers</title>
		<!-- reference css files -->
		<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resourceStuff/css/style.css"/>
	</head>
	
	<body>
	
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
			
				<!-- Button for adding a customer -->
				
				<input type="button" value="Add Customer"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button"
				/>
				
				<div style="clear; both"></div>
				
			   	<!--  add a search box -->
	            <form:form action="search" method="GET">
	                Search customer: <input type="text" name="theSearchName" />
	                <input type="submit" value="Search" class="add-button" />
	            </form:form>
				
				<!-- Table to display customers -->
				<table>
					<!-- Column names -->
					<tr>
						<th> First Name </th>
						<th> Last Name </th>
						<th> Email </th>
						<th> Action </th>
					</tr>
					<!-- Loop over customers from Model using JSTL tag -->
					<c:forEach var="tempCustomer" items="${customers}">
					
						<!-- this is just a variable with holds a url to
						update the customer with the current id -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
		
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						
						<tr>
							<td> ${tempCustomer.firstName} </td>
							<td> ${tempCustomer.lastName} </td>
							<td> ${tempCustomer.email} </td>
							<td>
								<!-- display the update link -->
								<a href="${updateLink}">Update</a>
								|
								<a href="${deleteLink}"
									onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false"
								>Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</div>
		</div>
		
	</body>
</html>