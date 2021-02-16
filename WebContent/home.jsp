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
						  <td rowspan="4">Profile Pic</td>
						  <td colspan="2">Name</td>
						  <td colspan="2">Highschool</td>
						  <td colspan="2">Hometown</td>
					 </tr>
					 <tr>
						  <td colspan="2">DOB (Hidden)</td>
						  <td colspan="2">Email Address(Hidden)</td>
						  <td colspan="2">PhoneNumber(Hidden)</td>
					 </tr>
					 <tr>
						  <td rowspan="2">Button A</td>
						  <td rowspan="2">Button B</td>
						  <td colspan="4">Last Login Date</td>  
					 </tr>
					 <tr>
					 	  <td colspan="4">Last Modification Date</td>
					 </tr>
				</c:forEach>
			</table>
		</div>
		
		<c:forEach var="user" items="${leftUsers}">
			<div class="left-people content">
				<table border="1">
					 <tr>
						  <td rowspan="2">Profile Pic</td>
						  <td colspan="2">Name: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						  <td colspan="2">Highschool</td>
						  <td colspan="2">Hometown</td>
					 </tr>
					 <tr>
						  <td colspan="2">DOB (Hidden)</td>
						  <td colspan="2">Email Address(Hidden)</td>
						  <td colspan="2">PhoneNumber(Hidden)</td>
					 </tr>
				</table>
			</div>
		</c:forEach>
		<c:forEach var="user" items="${rightUsers}">
			<div class="right-people content">
				<table border="1">
					 <tr>
						  <td rowspan="2">Profile Pic</td>
						  <td colspan="2">Name: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						  <td colspan="2">Highschool: <c:out value="${user.highschool}"/></td>
						  <td colspan="2">Hometown: <c:out value="${user.hometown}"/></td>
					 </tr>
					 <tr>
						  <td colspan="2">DOB (Hidden)</td>
						  <td colspan="2">Email Address(Hidden)</td>
						  <td colspan="2">PhoneNumber(Hidden)</td>
					 </tr>
				</table>
			</div>
		</c:forEach>
	</body>
</html>