<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookings</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>BookingId</th>
                <th>PropertyId</th>
                <th>start_date</th>
                <th>end_date</th>
                <th>price</th>
                <th>UserName</th>
            </tr>
            <c:forEach items="${bookings}" var="booking" >
                <tr>
                    <td><c:out value="${booking.getId()}" /></td>
                    <td><c:out value="${booking.getProperty().getPropertyKey()}" /></td>
                    <td><c:out value="${booking.getStart_date()}" /></td>
                    <td><c:out value="${booking.getEnd_date()}" /></td>
                    <td><c:out value="${booking.getPrice()}" /></td>
                    <td><c:out value="${booking.getUser().getUserName()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
