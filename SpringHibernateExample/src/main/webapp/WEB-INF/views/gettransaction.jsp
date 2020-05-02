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
      <form:form id="gettransform" modelAttribute="gettransaction" action="gettransactionDetails"
		method="post">
		<table align="center">
			<tr>
				<td><form:label path="transactionRefId">transactionRefId: </form:label></td>
				<td><form:input path="transactionRefId" name="transactionRefId" id="transactionRefId" />
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td align="left"><form:button id="gettransaction" name="gettransaction">Get Transaction</form:button>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
		<a href="/WEB-INF/views/home.jsp">Home</a>
	</form:form>
    </body>
</html>