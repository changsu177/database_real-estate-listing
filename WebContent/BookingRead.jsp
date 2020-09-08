<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search by Booking</title>
</head>
<body>
	<form action="bookingread" method="post">
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
                <th>Property ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Price</th>
                <th>UID</th>
                <th>RID</th>
            </tr>
                <tr>
                    <td><c:out value="${feat.getId()}" /></td>
                    <td><c:out value="${feat.getPropId()}" /></td>
					<td><c:out value="${feat.getStart_date()}" /></td>
					<td><c:out value="${feat.getEnd_date()}" /></td>
					<td><c:out value="${feat.getPrice()}" /></td>
					<td><c:out value="${feat.getUID()}" /></td>
					<td><c:out value="${feat.getRID()}" /></td>
                </tr>
       </table>
</body>
</html>
