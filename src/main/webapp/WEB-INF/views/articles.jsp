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
        <h1>articles</h1>
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
                        <p>Catagory</p>
                    </th>
                    <th>
                        <p>Sub-Catagory</p>
                    </th>
                    <th>
                        <p>Date</p>
                    </th>
                </tr>
            </table>
            <c:forEach var="article" items="${articles}">
                <table class="FlexTable">
                    <tr class="FlexRow">
                        <td>
                            <a href="wsm-app/article/${article.id}">${article.id}</a>
                        </td>
                        <td>
                            <p>${article.title}</p>
                        </td>
                        <td>
                            <p>${article.catagory}</p>
                        </td>
                        <td>
                            <p>${article.subCatagory}</p>
                        </td>
                        <td>
                            <p>${article.dateOpened}</p>
                        </td>
                    </tr>
                </table>
            </c:forEach>
        </div>
    </div>
    </div>


</body>

</html>