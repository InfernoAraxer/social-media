<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mask Stories</title>
		<style>
			<%@ include file="css/changePicStyle.css" %>
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
				<h2>Change Profile Pic</h2>
				<form action = "changePic" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
					<input type="submit" value="Submit" name="submit" />
					<label for="Profile Photo">Profile Photo:</label> <input type="file" name="image" multiple />
				</form>
				<form action="update" method="post">
					<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
					<input type="submit" value="Back" name="submit" />
				</form>
			</c:if>
		</div>
	</body>
</html>