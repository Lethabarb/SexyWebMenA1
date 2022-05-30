<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../static/site.css">
</head>

<body>
    <div class="Header">
        <ul class="Nav">
            <li><a href="home-IT.html">Home</a></li>
            <li>
                <a href="Issues.html">Issues</a>
            </li>
            <li>
                <a>Knowledge Base</a>
            </li>
        </ul>

        <ul class="Login">
        <%-- <c:choose>
            <c:when test="${userManager.isSignedIn}">
                <li><a href="logout">${userManager.getUser().email}</a></li>
                <li><a href="logout">Logout</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="logout">Logout</a></li>
            </c:otherwise>
        </c:choose> --%>
            <li><a href="home-User.html">Login</a></li>
        </ul>
    </div>