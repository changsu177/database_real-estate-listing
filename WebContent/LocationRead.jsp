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
	<form action="locationread" method="post">
		<h1>Search locations based off the state</h1>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="${fn:escapeXml(param.state)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Locations</h1>
        <table border="1">
            <tr>
                <th>State</th>
                <th>City</th>
                <th>Street1</th>
                <th>Street2</th>
                <th>Zip</th>
            </tr>
            <c:forEach items="${locations}" var="location" >
                <tr>
                    <td><c:out value="${location.getState()}" /></td>
                    <td><c:out value="${location.getCity()}" /></td>
					<td><c:out value="${location.getStreet1()}" /></td>
					<td><c:out value="${location.getStreet2()}" /></td>
					<td><c:out value="${location.getZip()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
