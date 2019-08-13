<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.perscholas.dealfinder.entities.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous" />
<link href="<c:url value="/resources/CSS/deal_finder.css" />"
	rel="stylesheet">
<!--link rel="stylesheet" href="CSS/deal_finder.css" type="text/css" /  -->
<title>DEAL FINDER</title>
</head>
<body>
	<!-- Header -->
	<%
		User u = (User) session.getAttribute("suser");
		//System.out.println(u.getsEmail()); 
		if (u.getsEmail().equalsIgnoreCase("guest@guest.com")) {
	%>
	<%@include file="guest_header.jsp"%>
	<%
		} else {
	%>
	<%@include file="user_header.jsp"%>
	<%
		}
	%>
	<section class="mainbody">
		<br />
		<h2>PRODUCT SEARCH</h2>
		<br />
		<br />
		<br />
		<h5>Enter a product and click the button to search the list. To
			view the whole list, leave blank and click the button.</h5>
		<br />
		<h6 class="text-primary font-weight-bold pl-5 userMessage">${userMessage}</h6>
		<br />
		<form action="getProducts" method="post">
			<div class="form-group">
				<label for="mainSearchBar">Search for a Product</label> <input
					class="form-control" type="text" name="name" id="mainSearchBar"
					placeholder="Search for a product..." /><br />
				<button type="submit" class="btn btn-success">Search</button>
				<br />
				<br />
			</div>
		</form>
	</section>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<!-- Footer -->
	<footer class="footer"> </footer>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous">
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
		<script src="<c:url value="/resources/js/deal_finder.js"/>" type="text/javascript"></script>
</body>
</html>