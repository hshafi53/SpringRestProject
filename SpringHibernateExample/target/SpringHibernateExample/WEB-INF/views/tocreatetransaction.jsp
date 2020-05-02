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
      <form:form id="createtransform" modelAttribute="tocreatetransaction" action="createtransaction"
		method="post">
		<table align="center">
			<tr>
				<td><form:label path="transactionRefId">transactionRefId: </form:label></td>
				<td><form:input path="transactionRefId" name="transactionRefId" id="transactionRefId" />
				</td>
			</tr>
			<tr>
				<td><form:label path="transType">transType: </form:label></td>
				<td><form:input path="transType" name="transType" id="transType" />
				</td>
			</tr>
			<tr>
				<td><form:label path="paymentType">paymentType: </form:label></td>
				<td><form:input path="paymentType" name="paymentType" id="paymentType" />
				</td>
			</tr>
			<tr>
				<td><form:label path="currencyCode">currencyCode: </form:label></td>
				<td><form:input path="currencyCode" name="currencyCode" id="currencyCode" />
				</td>
			</tr>
			<tr>
				<td><form:label path="itemTotal">itemTotal: </form:label></td>
				<td><form:input path="itemTotal" name="itemTotal" id="itemTotal" />
				</td>
			</tr>
			<tr>
				<td><form:label path="totalAmount">totalAmount: </form:label></td>
				<td><form:input path="totalAmount" name="totalAmount" id="totalAmount" />
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td align="left"><form:button id="tocreatetransaction" name="tocreatetransaction">Create Transaction</form:button>
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