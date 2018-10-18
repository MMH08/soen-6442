<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>you </title>
</head>
<body>
<h2>ADD COUNTRIES</h2>
<form:form method="post" action="saveCountries" modelAttribute="continentForm">
<table width="50%">
	<tr>
		
		<th>Continent Name</th>
		
	</tr>
	<c:forEach items="${continentForm.continents}"  var="continent" varStatus="status">
		<tr>
			
			<td><input name="continents[${status.index}].continentName" value="${continent.continentName}" /></td>
			
			<td><input name="countryList[${status.index}].countryName" path="countryList[${status.index}].countryName"/></td>
			
			<td><input name="countryList[${status.index}].countryName" path="countryList[${status.index}].countryName"/></td>
			
			<td><input name="countryList[${status.index}].countryName" path="countryList[${status.index}].countryName"/></td>
		</tr>
		
	</c:forEach>
	
</table>	
<br/>
<input type="submit" value="Click Ok to add countries" />
</form:form>
<input type="button" value="Back" onclick="javascript:history.back()"/>
</body>
</html>