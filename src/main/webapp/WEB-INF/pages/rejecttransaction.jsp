<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reject Transaction</title>
</head>
<body>	
	<form:form id="rejectTransactionProcessForm" modelAttribute="transaction" action="rejectTransactionProcess" method="post">
			<h1 style="color: #5e9ca0;">
					Reject Transaction
			</h1>
			<form:input path="id" name="id" id="id" hidden="true"/>
			<table class="editorDemoTable" style="height: 428px;" width="373">
				<tbody>
					<tr>
						<td><form:label path="name">Name: </form:label></td>
						<td><form:input path="name" name="name" id="name" disabled="true"/></td>
					</tr>
					<tr>
						<td><form:label path="accountNumber">Account Number: </form:label></td>
						<td><form:input path="accountNumber" name="accountNumber" id="accountNumber" disabled="true"/></td>
					</tr>
					<tr>
						<td><form:label path="type">Transaction Type: </form:label></td>
						<td><form:select path="type" name="type" id="type" disabled="true">
	  							<form:option value="1">Credit</form:option>
	  							<form:option value="2">Debit</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label path="amount">Amount: </form:label></td>
						<td><form:input path="amount" name="amount" id="amount" disabled="true" /></td>
					</tr>
					<tr>
						<td><form:label path="remark">Remark: </form:label></td>
						<td><form:input path="remark" name="remark" id="remark" disabled="true"/></td>
					</tr>
					<tr>
						<td><form:label path="apprejnote">
									Rejection Note:
							</form:label>
						</td>
						<td><form:textarea path="apprejnote" name="apprejnote" id="apprejnote"/></td>
					</tr>
					<tr>
						<td></td>
						<td align="left">
							<form:button id="approveTransationBtn" name="approveTransationBtn">
									Reject
							</form:button>
						</td>
					</tr>
				</tbody>
			</table>
	</form:form>
	<a href="login">Home</a>
</body>
</html>