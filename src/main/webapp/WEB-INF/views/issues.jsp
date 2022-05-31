<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="./static/site.css">
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
            <li><a>Login</a></li>
        </ul>
    </div>
    <div class="Title">
        <h1>Issues</h1>
    </div>
    <div class="Center">
        Filters?
    </div>
    <div class="Center">
        <div class="FlexBox">
            <table class="FlexTable">
                <tr class="FlexRow">
                    <th>
                        <p>Id</p>
                    </th>
                    <th>
                        <p>Title</p>
                    </th>
                    <th>
                        <p>Reporter</p>
                    </th>
                    <th>
                        <p>Catagory</p>
                    </th>
                    <th>
                        <p>Sub-Catagory</p>
                    </th>
                    <th>
                        <p>Date</p>
                    </th>
                    <th>
                        <p>Status</p>
                    </th>
                </tr>
            </table>
            <c:forEach var="issue" items="${issues}">
                <table class="FlexTable">
                    <tr class="FlexRow">
                        <td>
                            <p>${issue.id}</p>
                        </td>
                        <td>
                            <p>${issue.title}</p>
                        </td>
                        <td>
                            <p>${issueMap.get(issue).email}</p>
                        </td>
                        <td>
                            <p>${issue.catagory}</p>
                        </td>
                        <td>
                            <p>${issue.subCatagory}</p>
                        </td>
                        <td>
                            <p>${issue.getDateOpenedString()}</p>
                        </td>
                        <td class="Status${issue.status}">
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
                        </td>
                    </tr>
                </table>
            </c:forEach>
        </div>
    </div>
    </div>


</body>

</html>