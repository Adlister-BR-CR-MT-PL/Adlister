<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="../partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="first_name">First Name</label>
                <input id="first_name" name="first_name" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="last_name">Last Name</label>
                <input id="last_name" name="last_name" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block">
        </form>

        <c:if test="${sessionScope.registrationError != error}">
            <div class="alert alert-warning" role="alert">
            This username is already in use. Please try a different one...
        </div>
        </c:if>
    </div>
    <jsp:include page="../partials/footer.jsp" />
</body>
</html>















<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="register.css">
</head>
<body>
<div class="container-fluid main-container">
    <div class="row">
        <div class="col">
            <div class="row">
                <div class="col-12" style="padding: 40px">
                    <img src="WomanShopping.jpg" class="login-img"/>
                </div>
                <div class="col-12 side-txt">
                    <h1>Adlister</h1>
                    <p><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo ipsam, ipsum laboriosam maxime molestiae omnis ratione sunt? Ab debitis eum, ex facere,</span></p>
                </div>
            </div>
        </div>
        <div class="col form2">
            <div class="form-container">
                <!--                <h1>Please fill in your information.</h1>-->
                <form action="/register" method="post">
                    <div class="form-group">
                        <!--                        <label for="username">Username</label>-->
                        <input id="username" placeholder="Username" name="username" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <!--                        <label for="first_name">First Name</label>-->
                        <input id="first_name" placeholder="First Name" name="first_name" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <!--                        <label for="last_name">Last Name</label>-->
                        <input id="last_name" placeholder="Last Name" name="last_name" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <!--                        <label for="email">Email</label>-->
                        <input id="email" placeholder="Email Address" name="email" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <!--                        <label for="password">Password</label>-->
                        <input id="password" placeholder="Password" name="password" class="form-control" type="password">
                    </div>
                    <div class="form-group">
                        <!--                        <label for="confirm_password">Confirm Password</label>-->
                        <input id="confirm_password" placeholder="Confirm Password" name="confirm_password" class="form-control" type="password">
                    </div>
                    <input type="submit" class="btn btn-primary btn-block my-2" value="Sign Up" style="background-color: #8688ff">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>