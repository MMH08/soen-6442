<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

  
<title>User Driven-Continents</title>
</head>
<body>
<%-- <form:form action="cont" method="post" modelAttribute="continentForm">
<div class="container1">
	

<button class="add_form_field" name="cont">Add New Field &nbsp; <span style="font-size:16px; font-weight:bold;">+ </span></button>


 <form:input type="label" value="Control value" path ="contList.controlValue"/>
 
<div><form:input type="text"   name="contList" path="contList.controlValue"/></div>


 <form:input type="label" value="Name" path ="continentName"/>
 
<div><form:input type="text"   name="contList" path="continentName"/></div>




</div>
<input type="submit">

</form:form> --%>

<form:form method="post" action="save" modelAttribute="continentForm">
	<table>
	<tr>
		<th>Control Value</th>
		<th>Continent Name</th>
		
	</tr>
	<c:forEach items="${continentForm.continents}" var="continent" varStatus="status">
		<tr>
			<td align="center">${status.count}</td>
			<td><input name="continents[${status.index}].controlValue" value="${continent.controlValue}"/></td>
			<td><input name="continents[${status.index}].continentName" value="${continent.continentName}"/></td>
			
		</tr>
	</c:forEach>
</table>	
<br/>
<input type="submit" value="Click Ok to add countries" />
	
</form:form>


</body>
</html>