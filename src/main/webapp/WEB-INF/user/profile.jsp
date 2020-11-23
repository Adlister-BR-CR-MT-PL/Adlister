<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<c:if test="${requestScope.errors != null}">
    <c:forEach var="e" items="${errors}">
        <div class="alert alert-danger" role="alert">
                ${e}
        </div>
    </c:forEach>
</c:if>

<div class="container">
    <h1>Welcome, ${sessionScope.user.username}!</h1>

    <div class="card">
        <img class="card-img-top" height="150px" width="150px" src="${userPic}" alt="User Profile Image">
        <div class="card-body">
            <div class="card-header">
                <h3>User Information</h3>
            </div>
            <div class="card-text">
                <br>
                <c:out value="${sessionScope.user.firstName}">.</c:out>
                <c:out value="${sessionScope.user.lastName}">.</c:out>
                <br>
                <c:out value="${sessionScope.user.email}">.</c:out>
                <br>
            </div>
        </div>
        <form method="post" action="/updateinfo">
            <div class="form-group mt-5">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${sessionScope.user.email}" class="form-control">
            </div>
            <div class="form-group">
                <label for="password">New Password</label>
                <input type="password" id="password" name="password" placeholder="Enter new password" class="form-control">
            </div>
            <div class="form-group">
                <label for="confirm-password">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirm-password" placeholder="re-enter new password" class="form-control">
            </div>
            <button type="submit" value="${sessionScope.user.id}" name="userId" class="btn btn-dark btn-block">Submit</button>
        </form>
    </div>
</div>
<div class="card-body">
    <form action="/profile" method="post">
        <div class="form-group">
            <input type="hidden" name="pageName" value="showProfile">
            <input type="url" id="userPicture" placeholder="img URL Here" name="userPicture">
            <input name="userID" id="userID" type="hidden" value="${user.id}">
            <input type="submit" class="btn" value="Update Profile Picture">
        </div>
    </form>
    <c:if test="${sessionScope.PictureError != error}">
        <div class="alert alert-warning" role="alert">
            Couldn't upload file, try again.
        </div>
    </c:if>
    <form action="/profile" method="post">
        <input type="hidden" name="pageName" value="editProfile">
        <input type="submit" value="Edit Profile">
    </form>
</div>
</div>

<hr>
<h1>Here are your ads!</h1>

<c:forEach var="ad" items="${ads}">
    <div class="card">
        <div class="card-body">
            <div class="card-title">
                <h3><c:out value="${ad.title}"/></h3>
            </div>
            <div class="card-text">
                <p><c:out value="${ad.description}"/></p>
            </div>
            <form action="/ads" method="POST">
                <input name="ad-ID" id="ad-ID" type="hidden" value="${ad.id}">
                <input name="userID" type="hidden" value="${ad.userId}">
                <input type="submit" class="btn btn-primary" value="see details...">
            </form>
        </div>
    </div>
</c:forEach>
</p>
<jsp:include page="../partials/footer.jsp"/>
</div>
</body>
</html>