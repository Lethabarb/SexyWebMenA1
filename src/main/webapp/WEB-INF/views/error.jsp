<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./static/site.css">
    <meta charset="ISO-8859-1">
        <title>No Access</title>
    </head>
    <body>
<c:import var="navbar" url="./header.jsp"/>
                ${navbar}
        <h1>You do not have access to this.</h1>
    </body>
</html>