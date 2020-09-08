<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Property</title>
</head>
<body>
	<form action="propertyread" method="post">
		<h1>Search properties by Key</h1>
		<p>
			<label for="PropertyKey">Key</label>
			<input id="PropertyKey" name="PropertyKey" value="${fn:escapeXml(param.propertyKey)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Property</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Size</th>
                <th>Price</th>
            </tr>
                <tr>
                    <td><c:out value="${prop.getPropertyKey()}" /></td>
                    <td><c:out value="${prop.getPropertyName()}" /></td>
					<td><c:out value="${prop.getPropertyDescription()}" /></td>
					<td><c:out value="${feat.getPropertySize()}" /></td>
					<td><c:out value="${feat.getDefaultPrice()}" /></td>
                </tr>
       </table>
</body>
</html>
