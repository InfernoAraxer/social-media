<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Mask Stories</title>
		
		<style>>
			<%@ include file="css/loginStyle.css" %>
		</style>
	</head>
	<body>
	    <div style="text-align: center">
	        <h1>Mask Stories Login</h1>
	        <form action="login" method="post">
	            <label for="email">Email:</label>
	            <input name="email" size="30" />
	            <br><br>
	            <label for="password">Password:</label>
	            <input type="password" name="password" size="30" />
	            <br><br>${message}
	            <br><br>           
	            <button type="submit">Login</button>
	        </form>
	    </div>
	</body>
</html>