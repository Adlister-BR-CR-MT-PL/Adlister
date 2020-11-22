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
    <h1>TITLE: <c:out value="${ad.title}"/></h1>
    <h2>DESCRIPTION: <c:out value="${ad.description}"/></h2>
    <h3>Ad ID: <c:out value="${ad.id}"/></h3>
    <h4> Created By: <c:out value="${user}"/></h4>

    <c:if test="${sessionScope.user.id == sessionScope.ad.userId}">
        <form action="/ads/detail" method="post">
            <div class="form-group">
                <input type="url" placeholder="img URL Here" name="adsPicture">
                <input name="adId" id="adId" type="hidden" value="${sessionScope.ad.id}">
                <input type="submit" class="btn btn-primary" value="Update Ad Picture">
            </div>
        </form>
<%-- Updating an Ad--%>
        <form action="../ads/editAd" method="POST">
            <input name="update-AdID" id="updateAdID" type="hidden" value="${ad.id}">
            <input name="update-UserID" id="update-UserID" type="hidden" value="${ad.userId}">
            <input type="submit" id="update-AD" class="btn btn-warning" value="Update">
        </form>
<%--        Delete Ad WORKS--%>
        <form action="../delete-ad" method="POST">
            <input name="delete-AdID" id="delete-AdID" type="hidden" value="${sessionScope.ad.id}">
            <input name="delete-UserID" id="delete-UserID" type="hidden" value="${sessionScope.ad.userId}">
            <input type="submit" id="delete-AD" class="btn btn-danger" value="Delete">
        </form>

        <c:if test="${sessionScope.PictureError != error}">
            <div class="alert alert-warning" role="alert">
                Couldn't upload file, try again.
            </div>
        </c:if>
    </c:if>
</div>
<hr>
<jsp:include page="../partials/footer.jsp" />
</body>
</html>
