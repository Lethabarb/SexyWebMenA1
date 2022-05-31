<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>New Issue Form WIP</title>
    </head>
   <body>
       <h1>New Issue</h1>
       <div class="newIssueDiv">
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
                            <form:option value="0" label="Network"/>
                            <form:option value="1" label="Software"/>
                            <form:option value="2" label="Hardware"/>
                            <form:option value="3" label="Email"/>
                            <form:option value="4" label="Account"/>
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