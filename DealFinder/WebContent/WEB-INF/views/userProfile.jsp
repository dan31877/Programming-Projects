<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		if (u.getsEmail().equalsIgnoreCase("guest@guest.com")) {
			response.sendRedirect("index");
		} else {
	%>
	<%@include file="user_header.jsp"%>
	<%
		}
	%>
	<section class="mainbody">
		<br />
		<h2>UPDATE USER PROFILE</h2>
		<br /> <br /> <br />
		<h5>Update the text below and click the update button. To Delete
			your profile click the Delete button below. You will be logged out
			and returned to the Search page.</h5>
		<br />
		<h6 class="text-primary font-weight-bold pl-5 userMessage">${userMessage}</h6>
		<h6 class="text-danger font-weight-bold pl-5 userMessage">${errorMessage}</h6>
		<h6 class="text-danger font-weight-bold pl-5">${validationMessage}</h6>
		<br />
		<form action="updateUser" method="post" class="was-validated">
			<div class="form-group">
				<label for="inputEmail">Email Address: </label> <input type="email"
					class="form-control" name="sEmail" id="inputEmail"
					value=<%=u.getsEmail()%> readonly="true">
				<!-- small id="emailHelp" class="form-text text-muted">We'll never
					share your email with anyone else.</small-->
			</div>
			<div class="form-group">
				<label for="inputFirstName">First Name: </label> <input type="text"
					class="form-control" name="sFirstName" id="inputFirstName"
					minlength="2" maxlength="20" required value=<%=u.getsFirstName()%>>
				<div class="valid-feedback">Valid input, matches requirement.</div>
				<div class="invalid-feedback">First name length must be
					between 2 and 20.</div>
			</div>
			<div class="form-group">
				<label for="inputLastName">Last Name: </label> <input type="text"
					class="form-control" name="sLastName" id="inputLarstName"
					minlength="2" maxlength="30" required value=<%=u.getsLastName()%>>
				<div class="valid-feedback">Valid input, matches requirement.</div>
				<div class="invalid-feedback">Last name length must be between
					2 and 30.</div>
			</div>
			<div class="form-group">
				<label for="inputPassword">Password: </label> <input type="password"
					class="form-control" name="sPass" id="inputPassword"
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" required
					value=<%=u.getsPass()%>>
				<div class="valid-feedback">Valid input, matches requirement.</div>
				<div class="invalid-feedback">Passwords must have a minimum of
					five characters, at least one uppercase letter, one lowercase
					letter and one number</div>
			</div>
			<button type="submit" class="btn btn-success">Update</button>
			<button type="button"
				onclick="window.location.href = '${pageContext.request.contextPath}/index'"
				class="btn btn-success">Cancel</button>
			<button type="button"
				onclick="window.location.href = '${pageContext.request.contextPath}/deleteUser'"
				class="btn btn-danger  float-right">Delete User</button>
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
	<script src="<c:url value="/resources/js/deal_finder.js"/>"
		type="text/javascript"></script>
</body>
</html>