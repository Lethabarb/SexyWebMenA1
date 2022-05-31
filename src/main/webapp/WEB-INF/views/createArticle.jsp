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
    <form:form class="pageForm" method="POST" action="/wsm-app/admin/articleCreate">
        <div class="GridContainer">
            <div class="GridTitle">
                <input type="text" name="title" class="Title" value="${article.title}" id="formTitle">
            </div>
            <div class="GridDescription">
                <input type="text" value="${article.description}" name="description"  id="formDescription"/>
            </div>
            <div class="GridSolution">
                <textarea name="solution" class="Solution" cols="80" rows="10" minlength="5" maxlength="500" id="formSolution">${article.solution}</textarea>
            </div>
            <div class="GridId">

            <p id="Id">${article.id}</p>
                <input type="text" value="${article.id}" name="id" hidden id="formId"/>
                <input class="Button" type="submit" value="save" onclick="return validate()"/>
            </div>
            <div class="GridCatagory">
                <input id="formCatagory" type="text" value="${article.catagory}" name="catagory" /> : <input id="formSub" type="text" value="${article.subCatagory}" name="subCatagory" />
            </div>
            <div class="GridDate">
                <p>
                    <strong>Open</strong></p>
                <input type="datetime-local" value="${article.dateOpened}" name="dateOpened" hidden/>
                <p>${article.dateOpened} </p>
            </div>
        </div>
        <div class="Comments">
            <div>
                <c:forEach var="comment" items="${comments}">
                    <div class="Comment">
                        <div class="CommentHead">
                            <c:set var="auth" value="${users.get(comment.author)}"/>
                            <p>${auth.firstName} ${auth.lastName} </p>
                            <p>${comment.date}</p>
                        </div>
                        <div class="Content">
                            <p>${comment.content}</p>
                        </div>
                        <a href="/wsm-app/reply?parent=${comment.id}&redirectPath=/article/${article.id}">Reply</a>
                        <c:if test = "${fn:length(comment.replies) > 0}">
                            <c:set var="comment" value="${comment}" scope="request"/>
                            <c:import var="replies" url="./comments.jsp"/>
                ${replies}
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form:form>
<script>
    function validate() {
        var formTitle = document.getElementById("formTitle").value;
        var formDescription = document.getElementById("formDescription").value;
        var formSolution = document.getElementById("formSolution").value;
        var formId = document.getElementById("formId").value;
        var Id = document.getElementById("Id").innerHTML;
        var formCatagory = document.getElementById("formCatagory").value;
        var formSub = document.getElementById("formSub").value;

        var valid = true;
        var message = "Error: "
        if (formTitle == "") {
            message+= "title"
            valid = false;
        }
        if (formDescription == "") {
            if (valid) {
                message+= "description"
                valid = false;
            } else {
                message += ", description"
            }

        }
        if (formSolution == "") {
            if (valid) {
                message+= "solution"
                valid = false;
            } else {
                message += ", solution"
            }

        }
        if (formCatagory == "") {
            if (valid) {
                message+= "catagory"
                valid = false;
            } else {
                message += ", catagory"
            }

        }
        if (formSub == "") {
            if (valid) {
                message+= "sub catagory"
                valid = false;
            } else {
                message += ", sub catagory"
            }

        }
        if (formId != Id) {
            if (valid) {
                message+= "Id"
                valid = false;
            } else {
                message += ", Id"
            }

        }
        return valid;
    }
</script>
</body>

</html>