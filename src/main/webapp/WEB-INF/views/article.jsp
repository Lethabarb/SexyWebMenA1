<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <li><a href="home-User.html">Login</a></li>
        </ul>
    </div>
    <div class="GridContainer">
        <div class="GridTitle">
            <p class="Title">${article.title}</p>
        </div>
        <div class="GridDescription">
            <p>${article.description}</p>
        </div>
        <div class="GridSolution">
            <div class="Solution">
                ${article.solution}
            </div>
        </div>
        <div class="GridId">
            ${article.id}
        </div>
        <div class="GridCatagory">
            <p>${article.catagory} : ${article.subCatagory}</p>
        </div>
        <div class="GridDate">
            <p><strong>Open</strong></p>
            <p>${article.dateOpened}</p>
        </div>
    </div>
    <div class="Comments">
    <div>
        <c:forEach var="comment" items="${comments}">
        <div class="Comment">
            <div class="CommentHead">
                <p>${comment.author}</p>
                <p>${comment.date}</p>
            </div>
            <div class="Content">
                <p>${comment.content}</p>
            </div>
            <c:if test = "${fn:length(comment.replies) > 0}" >
                <c:set var="comment" value="${comment}" scope="request"/>
                <c:import var="replies" url="./comments.jsp"/>
                ${replies}
            </c:if>
        </div>
        </c:forEach>
        <form:form method="POST" action="/comment">
        <table>
            <tr>
                <td>
                    <form:textarea path="content" rows="5" cols="30"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:hidden path="parent" value="${article.id}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:hidden path="relation" value="A"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:hidden path="user" value="${thisClient.id}"/>
                </td>
            </tr>
        </table>
        </form:form>
    </div>

    
    </div>
</body>

</html>