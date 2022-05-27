    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <c:forEach var="comment" items="${comment.getReplies()}">
        <div class="Comment">
            <div class="CommentHead">
                <p>${comment.author}</p>
                <p>${comment.date}</p>
            </div>
            <div class="Content">
                <p>${comment.content}</p>
            </div>
            <c:if test = "${fn:length(comment.replies) > 0}" >
                <c:set var="comment" value="${comment}" scope="request"/>
                <c:import var="replies" url="./comments.jsp"/>
                ${replies}
            </c:if>
        </div>
        </c:forEach>
    