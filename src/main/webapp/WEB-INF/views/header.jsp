    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="Header">
        <ul class="Nav">
            <li><a href="/wsm-app/">Home</a></li>
            <li>
                <a href="/wsm-app/issues">Issues</a>
            </li>
            <li>
                <a href="/wsm-app/articles">Knowledge Base</a>
            </li>
        </ul>

        <ul class="Login">
        <c:choose>
            <c:when test="${userManager.isSignedIn}">
                <li><p>${userManager.getUser().email}</p></li>
                <li><a href="/wsm-app/logout">Logout</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/wsm-app/login">login</a></li>
            </c:otherwise>
        </c:choose>
        </ul>
    </div>
