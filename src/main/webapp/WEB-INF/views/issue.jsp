<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/wsm-app/static/site.css">

</head>
<body>
    <c:import var="navbar" url="./header.jsp"/>
                ${navbar}
    <div class="GridContainer">
        <div class="GridTitle">
            <p class="Title">${issue.title}</p>
        </div>
        <div class="GridDescription">
            <p>${issue.description}</p>
        </div>
        <div class="GridSolution">
            <div class="Solution">
                ${issue.solution}
            </div>
        </div>
        <div class="GridId">
            ${issue.id} : 
            <c:if test="${userManager.getUser().role == 1}">
                <a class="Button" href="/wsm-app/admin/issue/${issue.id}"> edit </a>
            </c:if>
        </div>
        <div class="GridCatagory">
            <p>${issue.catagory} : ${issue.subCatagory}</p>
        </div>
        <div class="GridDate">
            <div style="float:left">
                <p>
                    <strong>Open</strong>
                </p>
                <p>${issue.dateOpened}</p>
            </div>
            <div style="float:right">
                <p>
                    <strong>Closed</strong>
                </p>
                <p>${issue.dateClosed}</p>
            </div>
        </div>
    </div>
    <div class="Comments">
        <div>
            <c:forEach var="comment" items="${comments}">
                <div class="Comment">
                    <div class="CommentHead">
                        <c:set var="auth" value="${users.get(comment.author)}"/>
                        <p>${auth.firstName} ${auth.lastName}</p>
                        <p>${comment.date}</p>
                    </div>
                    <div class="Content">
                        <p>${comment.content}</p>
                    </div>
                    <a href="/wsm-app/reply?parent=${comment.id}&redirectPath=/issue/${issue.id}">Reply</a>
                    <c:if test = "${fn:length(comment.replies) > 0}">
                        <c:set var="comment" value="${comment}" scope="request"/>
                        <c:import var="replies" url="./comments.jsp"/>
                ${replies}
                    </c:if>
                </div>
            </c:forEach>
            <form:form method="POST" action="/wsm-app/comment">
                <textarea name="content" placeholder="Your message here" cols="80" rows="10" minlength="5" maxlength="500" spellcheck required></textarea>
                <input type="text" value="${issue.id}" name="parent" hidden/>
                <input type="text" value="I" name="relation" hidden/>
                <input type="text" value="${userManager.userId}" name="author" hidden/>
                <input type="text" value="/issue/${issue.id}" name="redirectPath" hidden/>
                <input type="submit" value="submit" class="Button"/>
            </form:form>
        </div>
    </div>
</body>

</html>