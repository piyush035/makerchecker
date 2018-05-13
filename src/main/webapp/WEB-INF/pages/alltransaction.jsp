<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Transaction</title>
</head>
<body>
<h1 style="color: #5e9ca0;">View All My Transactions Approved and Rejected</h1>
	<table border="1" style="border-collapse: collapse; width: 100%;">
		<tbody>
			<tr>
				<td style="width: 16.6667%;">Name</td>
				<td style="width: 16.6667%;">Account Number</td>
				<td style="width: 16.6667%;">Transaction Type</td>
				<td style="width: 16.6667%;">Amount</td>
				<td style="width: 16.6667%;">Comment</td>
				<td style="width: 16.6667%;">Status</td>
				<td style="width: 16.6667%;">Approval/Rejection Note</td>
				<td style="width: 16.6667%;">Approved/Rejected By</td>
			</tr>
			<c:forEach var="listValue" items="${transactions}">
				<tr>
					<td style="width: 16.6667%;">${listValue.name}</td>
					<td style="width: 16.6667%;">${listValue.accountNumber}</td>
					<td style="width: 16.6667%;">
						<c:if test="${listValue.type == 1}">
							CREDIT
						</c:if>
						<c:if test="${listValue.type == 2}">
							DEBIT
						</c:if>
					</td>
					<td style="width: 16.6667%;">${listValue.amount}</td>
					<td style="width: 16.6667%;">${listValue.remark}</td>
					<td style="width: 16.6667%;">
						<c:if test="${listValue.status == 1}">
							APPROVED
						</c:if>
						<c:if test="${listValue.status == 2}">
							REJECTED
						</c:if>
					</td>
					<td style="width: 16.6667%;">${listValue.apprejnote}</td>
					<td style="width: 16.6667%;">${listValue.apprejnote}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="login">Home</a>
</body>
</html>