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
			<c:if test="${image1 == null}">
				<h2>Change Profile Pic: Add Images</h2>
				<div class="inputs">
					<form action = "changePic" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
						<div class="file-upload">
							<label for="Profile Photo">Profile Photos:</label> 
							<input class="file-button" type="file" name="image" multiple/><br><br>
						</div>
						<input class="header-button" type="submit" value="Submit" name="submit" />
					</form>
					<br>
					<form action="update" method="post">
						<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
						<input class="header-button" type="submit" value="Back" name="submit" />
					</form>
				</div>
			</c:if>
			<div class="table">
					<form action = "selectPic" method="post">
						<c:if test="${image1 != null && image1.size() != 0}">
							<h2>Change Profile Pic: Choose One</h2>
							<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
							<table border="1">
								<c:forEach items="${image1}" var="image">
				   	 				<td class="pfp"><img src="data:image/jpg;base64,${image.base64Image}"  class="pfp-image"/><br><button type="submit" name="image" value="<c:out value="${image.id}"/>">Select</button></td>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${image2 != null && image2.size() != 0}">
							<table border="1">
								<c:forEach items="${image2}" var="image">
				   	 				<td class="pfp"><img src="data:image/jpg;base64,${image.base64Image}"  class="pfp-image"/><br><button type="submit" name="image" value="<c:out value="${image.id}"/>">Select</button></td>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${image3 != null && image3.size() != 0}">
							<table border="1">
								<c:forEach items="${image3}" var="image">
				   	 				<td class="pfp"><img src="data:image/jpg;base64,${image.base64Image}"  class="pfp-image"/><br><button type="submit" name="image" value="<c:out value="${image.id}"/>">Select</button></td>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${image4 != null && image4.size() != 0}">
							<table border="1">
								<c:forEach items="${image4}" var="image">
				   	 				<td class="pfp"><img src="data:image/jpg;base64,${image.base64Image}"  class="pfp-image"/><br><button type="submit" name="image" value="<c:out value="${image.id}"/>">Select</button></td>
								</c:forEach>
							</table>
						</c:if>
					</form>
				<c:if test="${image1 != null}">
					<form action="removeImages" method="post">
						<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
						<input type="submit" value="Back" name="submit" />
					</form>
				</c:if>
			</div>
		</div>
	</body>
</html>