<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search by State</title>
</head>
<body>
	<form action="featureread" method="post">
		<h1>Search Features by Key</h1>
		<p>
			<label for="id">Key</label>
			<input id="id" name="id" value="${fn:escapeXml(param.id)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Feature</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Description</th>
            </tr>
                <tr>
                    <td><c:out value="${feat.getKey()}" /></td>
                    <td><c:out value="${feat.getType()}" /></td>
					<td><c:out value="${feat.getDescription()}" /></td>
                </tr>
       </table>
</body>
</html>
