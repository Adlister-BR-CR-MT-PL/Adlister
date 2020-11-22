<%--
  Created by IntelliJ IDEA.
  User: cerapture
  Date: 11/21/20
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Ad"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1>Edit Ad Information</h1>
    <c:if test="${sessionScope.user.id == sessionScope.ad.userId}">
        <form action="../update-ad" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" class="form-control" value="${sessionScope.ad.title}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea name="description" id="description" cols="30" rows="10">${sessionScope.ad.description}</textarea>
            </div>
            <input name="update-AdID" id="updateAdID" type="hidden" value="${ad.id}">
            <input name="update-UserID" id="update-UserID" type="hidden" value="${ad.userId}">
            <input type="submit" id="update-AD" class="btn btn-warning" value="Submit Update">
        </form>
    </c:if>
</div>
<hr>
<jsp:include page="../partials/footer.jsp" />

</body>
</html>
