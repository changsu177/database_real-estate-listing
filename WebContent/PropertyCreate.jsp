<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Property</title>
</head>
<body>
	<h1>Create Property</h1>
	<form action="propertycreate" method="post">
		<p>
			<label for="PropertyKey">ID</label>
			<input id="PropertyKey" name="PropertyKey" value="">
		</p>
		<p>
			<label for="PropertyName">Property Name</label>
			<input id="PropertyName" name="PropertyName" value="">
		</p>
		<p>
			<label for="PropertyDescription">Description</label>
			<input id="PropertyDescription" name="PropertyDescription" value="">
		</p>
		<p>
			<label for="PropertySize">Size</label>
			<input id="PropertySize" name="PropertySize" value="">
		</p>
		<p>
			<label for="AddTime">Add</label>
			<input id="AddTime" name="AddTime" value="">
		</p>
		<p>
			<label for="UpdateTime">Update</label>
			<input id="UpdateTime" name="UpdateTime" value="">
		</p>
		<p>
			<label for="PropertyContactFK">ContactID</label>
			<input id="PropertyContactFK" name="PropertyContactFK" value="">
		</p>
		<p>
			<label for="PropertyLocationFK">LocationID</label>
			<input id="PropertyLocationFK" name="PropertyLocationFK" value="">
		</p>
		<p>
			<label for="DefaultPrice">Price</label>
			<input id="DefaultPrice" name="DefaultPrice" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>