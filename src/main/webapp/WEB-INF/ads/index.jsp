<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="/css/searchbar.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
</form>
<jsp:include page="/WEB-INF/partials/Searchbar.jsp" />
<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h2><c:out value="${ad.title}" /></h2>
                </div>
                <div class="card-text">
                    <p><c:out value="${ad.description}"/></p>
                </div>
                <form action="../ads" method="POST">
                    <input name="ad-ID" id="ad-ID" type="hidden" value="${ad.id}">
                    <input name="userID" id="userID" type="hidden" value="${ad.userId}">
                    <input type="submit" class="btn btn-primary" value="see details...">
                </form>
            </div>
        </div>
    </c:forEach>

</div>
<jsp:include page="../partials/footer.jsp" />
</body>
</html>



