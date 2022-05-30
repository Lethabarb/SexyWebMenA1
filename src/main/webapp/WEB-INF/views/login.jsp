<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="../css/site.css">
    </head>
    <body>
        <c:if test="${error}">
            <div class="error">
                <p>${errorMessage}</p>
            </div>
        </c:if>
        <div class="Header">
            <ul class="Nav">
                <li>
                    <a href="home-IT.html">Home</a>
                </li>
                <li>
                    <a href="Issues.html">Issues</a>
                </li>
                <li>
                    <a>Knowledge Base</a>
                </li>
            </ul>
        </div>
        <div class="Title">
            <h1>Login</h1>
        </div>

        <div class="loginFormDiv">
            <form:form method="POST" name="loginForm" action="createLogin">
                <table>
                    <tr>
                        <td>Email: </td>
                        <td>
                            <input type="text" name="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td>
                            <input type="password" name="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Submit"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>


        <script>

        function Validate(){

            if(!ValidateEmail(document.loginForm.email.value)){
                return false;
            }
            if(document.loginForm.password.value == ""){
                alert("Password cannot be empty");
                return false;
            }
            return true;

        }

        function ValidateEmail(input) {

            var validEmailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
          
            if (input.value.match(validEmailRegex)) {
              return true;
            } 
            else {
              document.form1.text1.focus();
              return false;
            }
          
          }

        </script>
    </body>
</html>