<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="/wsm-app/static/site.css">

</head>

<body>
    <c:import var="navbar" url="./header.jsp"/>
                ${navbar}
    <div class="Title">
        <h1>articles</h1>
    </div>
    <div class="Center">
        Filters?
        <form action="/wsm-app/articles" method="GET">
            <label for="category">Category:</label>
            <select id="category" name="category" onchange="this.form.submit()">
                <option value="">No Filter</option>
                <c:choose>
                    <c:when test="${selectedCategory == 'Network'}">
                        <option value="Network" selected>Network</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Network">Network</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${selectedCategory == 'Software'}">
                        <option value="Software" selected>Software</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Software">Software</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${selectedCategory == 'Hardware'}">
                        <option value="Hardware" selected>Hardware</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Hardware">Hardware</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${selectedCategory == 'Email'}">
                        <option value="Email" selected>Email</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Email">Email</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${selectedCategory == 'Account'}">
                        <option value="Account" selected>Account</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Account">Account</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <label for="subCategory">Subcategory</label>
            <select id="subCategory" name="subCategory" onchange="this.form.submit()">
                <option value="">No Filter</option>
                <c:forEach var="subcategory" items="${subcategories}">
                    <option value="${subcategory}">${subcategory}</option>
                </c:forEach>
            </select>
        </form>


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
            <c:if test="${userManager.getUser().role == 1}">
                <table class="FlexTable">
                    <tr class="FlexRow">
                        <td>
                            <a href="/wsm-app/admin/createArticle" class="Button">Add</a>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:forEach var="article" items="${articles}">
                <table class="FlexTable">
                    <tr class="FlexRow">
                        <td>
                            <a href="article/${article.id}">${article.id}</a>
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