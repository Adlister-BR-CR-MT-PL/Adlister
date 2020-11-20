<%--
  Created by IntelliJ IDEA.
  User: bianca
  Date: 11/18/20
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile Info</title>
</head>
<body>
<div class="container">
    <form action="/editProfile" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" value="<c:out value="${sessionScope.user.username}"></c:out>"
                   name="username"
                   class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="first_name">First Name</label>
            <input id="first_name" value="<c:out value="${sessionScope.user.firstName}"></c:out>"
                   name="first_name"
                   class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="last_name">Last Name</label>
            <input id="last_name" value="<c:out value="${sessionScope.user.lastName}"></c:out>"
                   name="last_name"
                   class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" value="<c:out value="${sessionScope.user.email}"></c:out>" name="email"
                   class="form-control" type="text">
        </div>
        <input type="submit" value="Update Profile" class="btn btn-primary btn-block">
    </form>

</div>
</body>
</html>
