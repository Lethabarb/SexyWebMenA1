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
            <form method="post" action="/wsm-app/addIssue">
                <table>
                    <tr>
                        <td><label for="title">Title</label></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="title"></td>
                    </tr>
                    <tr>
                        <td> 
                            <select id="catagory" name="catagory" size="1">
                                <option value="" selected="selected">Select Catagory</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> 
                            <select id="subCatagory" name="subCatagory" size="1">
                                <option value="" selected="selected">Please Select Catagory first</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="description">Description</label></td>
                    </tr>
                    <tr>
                        <td><textarea name="description" cols="30" rows="5"></textarea></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" onclick="return validate()"></td>
                    </tr>
                </table>
            </form>
       </div>

       <script>

        var selection = {
            "Network": ["Can't connect", "Speed", "Constant dropouts"],
            "Software": ["Slow to load", "Won't load at all"],
            "Hardware": ["Computer won't turn on", "Computer 'blue screens'", "Disk drive", "Peripherals"],
            "Email": ["Can't send", "Can't recieve", "SPAM/Phishing"],
            "Account": ["Password reset", "Wrong details"]
        }

        window.onload = function () {
            var catagory = document.getElementById("catagory");
            var subCatagory = document.getElementById("subCatagory");

            for (var cat in selection) {
                catagory.options[catagory.options.length] = new Option(cat, cat);
            }
        }
        catagory.onchange = function () {
            subCatagory.length = 1;
            if (this.selectedIndex < 1) return;
            var subCategories = selection[this.value];
            for (var i = 0; i < subCategories.length; i++) {
  	            subCatagory.options[subCatagory.options.length] = new Option(subCategories[i], subCategories[i]);
                }
            }
        catagory.onchange();

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