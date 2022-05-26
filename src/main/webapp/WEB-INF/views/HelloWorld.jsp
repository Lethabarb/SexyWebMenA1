<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Spring 5 MVC - Hello World Example | javaguides.net</title>
    </head>
   <body>
      <h2>${thisClient.email} <br>
        ${thisClient.firstName}
      </h2>
   </body>
</html>