<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Booking</title>
</head>
<body>
	<h1>Create Booking</h1>
	<form action="bookingcreate" method="post">
		<p>
			<label for="id">ID</label>
			<input id="id" name="id" value="">
		</p>
		<p>
			<label for="property_id">Property ID</label>
			<input id="property_id" name="property_id" value="">
		</p>
		<p>
			<label for="start_date">Start Date</label>
			<input id="start_date" name="start_date" value="">
		</p>
		<p>
			<label for="end_date">End Date</label>
			<input id="end_date" name="end_date" value="">
		</p>
		<p>
			<label for="price">Price</label>
			<input id="price" name="price" value="">
		</p>
		<p>
			<label for="user_id">UserID</label>
			<input id="user_id" name="user_id" value="">
		</p>
		<p>
			<label for="review_id">ReviewID</label>
			<input id="review_id" name="review_id" value="">
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