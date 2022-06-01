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
    <form:form class="pageForm" method="POST" action="/wsm-app/admin/editIssue">
        <div class="IGridContainer">
            <div class="IGridTitle">
                <input type="text" name="title" class="Title" value="${issue.title}" id="formTitle">
            </div>
            <div class="IGridDescription">
                <input type="text" value="${issue.description}" name="description"  id="formDescription"/>
            </div>
            <div class="IGridSolution">
                <textarea name="solution" class="Solution" cols="80" rows="10" minlength="5" maxlength="500" id="formSolution">${issue.solution}</textarea>
            </div>
            <div class="IGridId">

            <p id="Id">${issue.id}</p>
                <input type="text" value="${issue.id}" name="id" hidden id="formId"/>
                <input class="Button" type="submit" value="save" onclick="return validate()"/>
            </div>
            <div class="IGridCatagory">
                <input id="formCatagory" type="text" value="${issue.catagory}" name="catagory" /> : <input id="formSub" type="text" value="${issue.subCatagory}" name="subCatagory" />
            </div>
            <div class="IGridStatus${issue.status}">
                <select id="formStatus" name="status">
                    <option value="0">Submitted</option>
                    <option value="1">In Progress</option>
                    <option value="2">Solution</option>
                    <option value="3">Accepted</option>
                    <option value="4">Rejected</option>
                </select>
            </div>
            <div class="IGridDate">
                <p>
                    <strong>Open</strong>
                <input type="datetime-local" value="${issue.dateOpened}" name="dateOpened" hidden/>
                </p>
                <p>${issue.dateOpened}</p>
                <p>
                    <strong>Closed</strong>
                <input type="datetime-local" value="${issue.dateClosed}" name="dateClosed" hidden/>
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
                            <p>${auth.firstName} ${auth.lastName} </p>
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