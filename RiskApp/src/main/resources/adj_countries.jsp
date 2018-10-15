<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Adjacent Countries</title>
</head>
<body>
<form:form method="post" action="addAdj" modelAttribute="continentForm">
<h3>${continentForm.countryNames}</h3>
<table>


<tr>

<c:forEach items="${continentForm.countryNames}" var="countryName" varStatus="status">
<tr>
<td><input name="countryNames[${status.index}]" value="${countryName}" /></td>
<td>
<form:select multiple="true" path="countryList[${status.index}].countryName">
    <form:options items="${continentForm.countryNames}" />
</form:select>
</td>
</tr>
</c:forEach>





</table>

<input type="submit" value="Click Ok to add countries" />
</form:form>
</body>
</html>