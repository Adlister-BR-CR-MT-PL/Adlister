<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">
    <nav class="navbar navbar-expand-lg sticky-top py-3">
        <div class="container"><a href="#" class="navbar-brand text-uppercase font-weight-bold">AdLister</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="http://localhost:8099/ads" class="nav-link text-uppercase font-weight-bold">Ads</a></li>
                    <li class="nav-item"><a href="http://localhost:8099/ads/create" class="nav-link text-uppercase font-weight-bold">Create Ad</a></li>
                    <c:if test="${sessionScope.user.username == null}">
                        <li class="nav-item"><a href="http://localhost:8099/register" class="nav-link text-uppercase font-weight-bold">Register</a></li>
                        <li class="nav-item"><a href="http://localhost:8099/login" class="nav-link text-uppercase font-weight-bold">Sign in</a></li>
                    </c:if>

                    <c:if test="${sessionScope.user.username != null}">
                        <li class="nav-item"><a href="http://localhost:8099/profile" class="nav-link text-uppercase font-weight-bold"><c:out value="${sessionScope.user.username}"/></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>