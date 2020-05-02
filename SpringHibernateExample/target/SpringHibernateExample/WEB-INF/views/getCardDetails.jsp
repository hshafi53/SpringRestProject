<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <form:form id="getCardsform" modelAttribute="getCardDetails" action="/SpringHibernateExample/validatepinforCC"
		method="post">
		<table align="center">
			<tr>
				<td><form:label path="pin">PIN: </form:label></td>
				<td><form:input path="pin" name="pin" id="pin" />
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td align="left"><form:button id="getCardDetails" name="VerifyCard">Validate</form:button>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form:form>
</body>
</html>