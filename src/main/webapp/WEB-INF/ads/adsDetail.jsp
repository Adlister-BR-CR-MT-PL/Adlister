<%--
  Created by IntelliJ IDEA.
  User: bianca
  Date: 11/16/20
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="ad Detail"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Ad Overview</h1>
    <img height="150px" src="${adPic}" alt="advertising picture">
    <h1><c:out value="${ad.title}"/></h1>
    <h2><c:out value="${ad.description}"/></h2>
    <h3>Ad ID: <c:out value="${ad.id}"/></h3>
    <h4> Created By: <c:out value="${user}"/></h4>

    <c:if test="${sessionScope.user.id == sessionScope.ad.userId}">
        <form action="/ads/detail" method="post">
            <div class="form-group">
                <input type="url" placeholder="img URL Here" name="adsPicture">
                <input name="userID" id="userID" type="hidden" value="${sessionScope.ad.id}">
                <input type="submit" class="btn" value="Update Ad Picture">
            </div>
        </form>
        <c:if test="${sessionScope.PictureError != error}">
            <div class="alert alert-warning" role="alert">
                Couldn't upload file, try again.
            </div>
        </c:if>
    </c:if>
</div>
<jsp:include page="../partials/footer.jsp" />
</body>
</html>
