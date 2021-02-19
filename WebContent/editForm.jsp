<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mask Stories</title>
		
		<style>
			<%@ include file="css/editFormStyle.css" %>
		</style>
	</head>
	<body>
		<div class="navbar">
			  <h2 class="title">Mask Stories</h2>
			  <form action="logout">
			  	<input type="submit" value="Logout">
			  </form>
		</div>
		<div class="content">
			<c:if test="${user != null}">
				<h2>Edit Profile</h2>
				<form class="form-editable" action="update" method="post">
					<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
					
					<label>First Name:<input type="text" name="firstName" value="<c:out value="${user.firstName}" />" /></label>
					<label>Last Name:<input type="text" name="lastName" value="<c:out value="${user.lastName}" />" /></label>
					<label>High School:<input type="text" name="highschool" value="<c:out value="${user.highschool}" />" /></label>
					<label>Home Town:<input type="text" name="hometown" value="<c:out value="${user.hometown}" />" /></label>
					<label>College/University:<input type="text" name="university" value="<c:out value="${user.university}" />" /></label>
					
					<div class="hide">
						<label>Date Of Birth: &nbsp;<input class="date" type="date" name="dob" value="<c:out value="${user.dateOfBirthHidden}" />" /></label>
						<c:choose>
							<c:when test="${user.dateOfBirth == 'XXXX-XX-XX'}">
								<label>Hide Date of Birth: <input type="checkbox" name="dobHidden" value="Hide" class="checkbox" checked></label>
							</c:when>
							<c:otherwise>
								<label>Hide Date of Birth: <input type="checkbox" name="dobHidden" value="Hide" class="checkbox"></label>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="hide">
						<label>Email Address: <input class="text" type="text" name="email" value="<c:out value="${user.emailAddressHidden}" />" /></label>
						<c:choose>
							<c:when test="${user.emailAddress == 'Hidden'}">
								<label>Hide Email Address: <input type="checkbox" name="emailHidden" value="checked" class="checkbox" checked></label>
							</c:when>
							<c:otherwise>
								<label>Hide Email Address: <input type="checkbox" name="emailHidden" value="checked" class="checkbox"></label>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="hide">
						<label>Phone Number:<input class="text" type="text" name="phone" value="<c:out value="${user.phoneNumberHidden}" />" /></label>
						<c:choose>
							<c:when test="${user.phoneNumber == 'XXXXXXXXXX'}">
								<label>Hide Phone Number: <input type="checkbox" name="phoneHidden" value="Hide" class="checkbox" checked></label>
							</c:when>
							<c:otherwise>
								<label>Hide Phone Number: <input type="checkbox" name="phoneHidden" value="Hide" class="checkbox"></label>
							</c:otherwise>
						</c:choose>
						
					</div>
					
					<label>Password: <input type="password" name="password" value="<c:out value="${user.password}" />" /></label>
					<div class="form-actions">
						<input type="submit" value="Back" name="submit" />
						<input type="submit" value="Save" name="submit" />
					</div>
				</form>
			</c:if>
		</div>
	</body>
</html>