<%--
  Created by IntelliJ IDEA.
  User: patrick
  Date: 11/16/20
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Recovery Password"/>
    </jsp:include>
    <link href="/css/recoverpw.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../partials/navbar.jsp"/>
<div class="container forget-password">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <img src="../partials/forgot-pw.png" alt="locked" border="0">
                        <h2 class="text-center">Forgot Password?</h2>
                        <p>You can reset your password here.</p>
                        <form action="/recoverpw" id="register-form" role="form" autocomplete="off" class="form" method="post">
                            <div class="form-group col-md-12">
                                <label for="username" class="col-sm-2 col-form-label">Username</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="username">
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="email" class="col-sm-2 col-form-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="email">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="new_password" class="col-sm-2 col-form-label">New Password</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="new_password">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="confirm_new_password" class="col-sm-2 col-form-label">Confirm Password</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="confirm_new_password">
                                </div>
                            </div>
                            <div class="form-group">
                                <input name="btnForget" class="btn btn-lg btn-primary btn-block btnForget" value="Reset Password" type="submit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
