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
            <h1>New Issue</h1>
        </div>
       <div>
           <form:form method="post" action="/wsm-app/addIssue">
               <table>
                   <tr>
                       <td> <form:label path="title">Title</form:label></td>
                       <td> <form:input path="title" /></td>
                   </tr>
                   <tr>
                        <td> <form:label path="description">Description</form:label></td>
                        <td> <form:input path="description" /></td>
                    </tr>
                    <tr>
                        <td> 
                            <form:select path="catagory">
                            <form:option value="Category" selected="true" disabled="true"/>
                            <form:option value="Network"/>
                            <form:option value="Software"/>
                            <form:option value="Hardware"/>
                            <form:option value="Email"/>
                            <form:option value="Account"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td> <input type="submit" value="Submit"/></td>
                    </tr>
               </table>
           </form:form>
       </div>
   </body>
</html>