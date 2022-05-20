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
        </div>
        <div class="Title">
            <h1>Login</h1>
        </div>

        <div class="loginFormDiv">
            <form:form method="post" action="UserController">
                <table>
                    <tr>
                        <td> <form:input path="email"/> </td>
                    </tr>
                    <tr>
                        <td><form:input path="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" path="submit"/></td>
                    </tr>
                </table>
            </form:form>
        </div>

        <div class="loginFormDiv">
            <form method="post" action="" style="width:fit-content" name="loginForm"> <!--need to add action-->
                <input id="email" name="email" type="text" placeholder="Email here"><br><br>
                <input id="password" name="password" type="text" placeholder="Password here"> <br><br>
    
                <input type="submit" value="Submit" onsubmit="return Validate()">
            </form>
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