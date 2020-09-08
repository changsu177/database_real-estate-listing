<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View all features</title>
</head>
<body>
<br/>
<br/>
<h1>Features</h1>
<table border="1">
    <tr>
        <th>Feature Key</th>
        <th>Feature Type</th>
        <th>Count</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${blogUsers}" var="features" >
        <tr>
            <td><c:out value="${blogUser.getFeatureKey()}" /></td>
            <td><c:out value="${blogUser.getFeatureType().toString()}" /></td>
            <td><c:out value="${blogUser.getCount()}" /></td>
            <td><c:out value="${blogUser.getDescription()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
