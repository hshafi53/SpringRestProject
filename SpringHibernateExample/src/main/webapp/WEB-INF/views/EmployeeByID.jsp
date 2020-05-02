<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University Enrollments</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>

</head>


<body>


	<h2>Details of Employees via ID</h2>
	${employeesbyid}

	<%-- <table>
		<tr>
			<td>ID</td>
			<td>NAME</td>
			<td>AMOUNT</td>
		</tr>
		
		<c:forEach items="${employeesbyid}" var="employee">
			<tr>
				<td>${employeesbyid.id}</td>
				<td>${employeesbyid.name}</td>
				<td>${employeesbyid.amount}</td>


			</tr>
		</c:forEach>
	</table>
	<br /> --%>

</body>
</html>