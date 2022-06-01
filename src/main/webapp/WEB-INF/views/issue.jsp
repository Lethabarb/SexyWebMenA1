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
    <div class="IGridContainer">
        <div class="IGridTitle">
            <p class="Title">${issue.title}</p>
        </div>
        <div class="IGridDescription">
            <p>${issue.description}</p>
        </div>
        <div class="IGridSolution">
            <div class="Solution">
                ${issue.solution}
            </div>
        </div>
        <div class="IGridId">
            ${issue.id} : 
            <c:if test="${userManager.getUser().role == 1}">
                <a class="Button" href="/wsm-app/admin/issue/${issue.id}"> edit </a>
            </c:if>
        </div>
        <div class="IGridCatagory">
            <p>${issue.catagory} : ${issue.subCatagory}</p>
        </div>
        <div class="IGridStatus${issue.status}">
            <c:if test = "${issue.status == 0}">
            <p>
                Submitted
            </p>
            </c:if>
            <c:if test = "${issue.status == 1}">
            <p>
                In Progress
            </p>
            </c:if>
            <c:if test = "${issue.status == 2}">
            <p>
                Solution
            </p>
            </c:if>
            <c:if test = "${issue.status == 3}">
            <p>
                Accepted
            </p>
            </c:if>
            <c:if test = "${issue.status == 4}">
            <p>
                Rejected
            </p>
            </c:if>
        </div>
        <div class="IGridDate">
            <p>
                <strong>Open</strong>
            </p>
            <p>${issue.dateOpened}</p>
            <p>
                <strong>Closed</strong>
            </p>
            <p>${issue.dateClosed}</p>
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