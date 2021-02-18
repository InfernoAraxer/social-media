<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Mask Stories</title>
		
		<style>
			<%@ include file="css/homeStyle.css" %>
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
			<table border="1">
				<c:forEach var="user" items="${users}">
					 <tr>
						  <td rowspan="4" class="main-pfp"><img src="data:image/jpg;base64,${user.base64Image}" width="auto" height="195"/></td>
						  <td colspan="2" class="content name-and-dob">Name: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						  <td colspan="2" class="content highschool-and-email">High School: <c:out value="${user.highschool}"/><br>College/Univ.: <c:out value="${user.university}"/></td>
						  <td colspan="2" class="content hometown-and-phone">Home Town: <c:out value="${user.hometown}"/></td>
					 </tr>
					 <tr>
						  <td colspan="2" class="name-and-dob">DOB: <c:out value="${user.dateOfBirthHidden}"/></td>
						  <td colspan="2" class="highschool-and-email">Email Address: <c:out value="${user.emailAddress}"/></td>
						  <td colspan="2" class="hometown-and-phone">Phone Number: <c:out value="${user.phoneNumberHidden}"/></td>
					 </tr>
					 <tr>
						  <td rowspan="2" class="update-profile"><a class="header-button" href="${pageContext.request.contextPath}/edit?id=${user.id}" class="button">Update Profile</a></td>
						  <td rowspan="2" class="update-pfp"><a class="header-button" href="${pageContext.request.contextPath}/editpfp?id=${user.id}" class="button">Change Profile Pic</a></td>
						  <td colspan="4" class="login">Last Login Date: <c:out value="${user.loginDate}"/></td>  
					 </tr>
					 <tr>
					 	  <td colspan="4" class="modified">Last Modification Date: <c:out value="${user.lastModifiedDate}"/></td>
					 </tr>
				</c:forEach>
			</table>
		</div>
		<div class="left-people content">
			<c:forEach var="user" items="${leftUsers}">
				<table border="1">
					 <tr>
						  <td rowspan="2" class="pfp"><img src="data:image/jpg;base64,${user.base64Image}" width="auto" height="105"/></td>
						  <td colspan="2" class="name-and-dob">Name: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						  <td colspan="2" class="highschool-and-email">High School: <c:out value="${user.highschool}"/><br>College/Univ.: <c:out value="${user.university}"/></td>
						  <td colspan="2" class="hometown-and-phone">Home Town: <c:out value="${user.hometown}"/></td>
					 </tr>
					 <tr>
						  <td colspan="2" class="name-and-dob">DOB: <c:out value="${user.dateOfBirth}"/></td>
						  <td colspan="2" class="highschool-and-email">Email Address: <c:out value="${user.emailAddress}"/></td>
						  <td colspan="2" class="hometown-and-phone">Phone Number: <c:out value="${user.phoneNumber}"/></td>
					 </tr>
				</table>
			</c:forEach>
		</div>
		<div class="right-people content">
			<c:forEach var="user" items="${rightUsers}">
				<table border="1">
					 <tr>
						  <td rowspan="2" class="pfp"><img src="data:image/jpg;base64,${user.base64Image}" width="auto" height="105"/></td>
						  <td colspan="2" class="name-and-dob">Name: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						  <td colspan="2" class="highschool-and-email">High School: <c:out value="${user.highschool}"/><br>College/Univ.: <c:out value="${user.university}"/></td>
						  <td colspan="2" class="hometown-and-phone">Home Town: <c:out value="${user.hometown}"/></td>
					 </tr>
					 <tr>
						  <td colspan="2" class="name-and-dob">DOB: <c:out value="${user.dateOfBirth}"/></td>
						  <td colspan="2" class="highschool-and-email">Email Address: <c:out value="${user.emailAddress}"/></td>
						  <td colspan="2" class="hometown-and-phone">Phone Number: <c:out value="${user.phoneNumber}"/></td>
					 </tr>
				</table>
			</c:forEach>
		</div>
	</body>
</html>