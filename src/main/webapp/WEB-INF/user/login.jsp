<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/login.css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="form-container">
        <h1>Adlister</h1>
        <form action="/login" method="POST">
            <div class="form-group input-container" id="user-container">
                <!--            <label for="username"><i class="fas fa-user"></i> Username</label>-->
                <input id="username" name="username" class="form-control input" placeholder="Username" type="text" required>
            </div>
            <div class="form-group input-container">
                <!--            <label for="password"><i class="fas fa-key"></i> Password</label>-->
                <input id="password" name="password" class="form-control input" placeholder="Password" type="password" required>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <input type="submit" class="btn btn-success btn-block" value="Log In">
                    </div>
                    <br>
                    <br>
                    <div class="col">
                        <!--Might need to use this button /register servlet-->
                        <!--<a class="btn btn-primary" href="#" role="button">Register</a>-->
                        <input type="submit" class="btn btn-primary btn-block" value="Register">
                    </div>
                    <div class="col">
                        <input type="submit" class="btn btn-danger btn-block" value="Forgot Password">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../partials/footer.jsp" />
</body>
</html>