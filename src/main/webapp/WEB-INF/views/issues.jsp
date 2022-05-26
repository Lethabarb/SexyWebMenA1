<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../css/site.css">
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
            <c:forEach var="i" begin="0" end="${issues.length}">
                <table class="FlexTable">
                    <tr class="FlexRow">
                        <th>
                            <p>${issues[i].id}</p>
                        </th>
                        <th>
                            <p>${issues[i].title}</p>
                        </th>
                        <th>
                            <p>${reporter.email}</p>
                        </th>
                        <th>
                            <p>${issues[i].catagory}</p>
                        </th>
                        <th>
                            <p>${issues[i].subCatagory}</p>
                        </th>
                        <th>
                            <p>${issues[i].opened}</p>
                        </th>
                        <th>
                            <p>${issues[i].status}</p>
                        </th>
                    </tr>
                </table>
            </c:forEach>
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
            <table class="FlexTable">
                <tr class="FlexRow Status0body">
                    <td>
                        <a href="Issue.html">Id</a>
                    </td>
                    <td>
                        <p>Title</p>
                    </td>

                    <td>
                        <p>Reporter</p>
                    </td>
                    <td>
                        <p>Catagory</p>
                    </td>
                    <td>
                        <p>Sub-Catagory</p>
                    </td>
                    <td>
                        <p>Date</p>
                    </td>
                    <td class="Status0">
                        <p>Status</p>
                    </td>
                </tr>
            </table>
            <table class="FlexTable">
                <tr class="FlexRow Status1body">
                    <td>
                        <a href="Issue.html">Id</a>
                    </td>
                    <td>
                        <p>Title</p>
                    </td>

                    <td>
                        <p>Reporter</p>
                    </td>
                    <td>
                        <p>Catagory</p>
                    </td>
                    <td>
                        <p>Sub-Catagory</p>
                    </td>
                    <td>
                        <p>Date</p>
                    </td>
                    <td class="Status1">
                        <p>Status</p>
                    </td>
                </tr>
            </table>
            <table class="FlexTable">
                <tr class="FlexRow Status2body">
                    <td>
                        <a href="Issue.html">Id</a>
                    </td>
                    <td>
                        <p>Title</p>
                    </td>

                    <td>
                        <p>Reporter</p>
                    </td>
                    <td>
                        <p>Catagory</p>
                    </td>
                    <td>
                        <p>Sub-Catagory</p>
                    </td>
                    <td>
                        <p>Date</p>
                    </td>
                    <td  class="Status2">
                        <p>Status</p>
                    </td>
                </tr>
            </table>
            <table class="FlexTable">
                <tr class="FlexRow Status3body">
                    <td>
                        <a href="Issue.html">Id</a>
                    </td>
                    <td>
                        <p>Title</p>
                    </td>

                    <td>
                        <p>Reporter</p>
                    </td>
                    <td>
                        <p>Catagory</p>
                    </td>
                    <td>
                        <p>Sub-Catagory</p>
                    </td>
                    <td>
                        <p>Date</p>
                    </td>
                    <td class="Status3">
                        <p>Status</p>
                    </td>
                </tr>
            </table>
            <table class="FlexTable">
                <tr class="FlexRow Status4body">
                    <td>
                        <a href="Issue.html">Id</a>
                    </td>
                    <td>
                        <p>Title</p>
                    </td>

                    <td>
                        <p>Reporter</p>
                    </td>
                    <td>
                        <p>Catagory</p>
                    </td>
                    <td>
                        <p>Sub-Catagory</p>
                    </td>
                    <td>
                        <p>Date</p>
                    </td>
                    <td class="Status4">
                        <p>Status</p>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </div>


</body>

</html>