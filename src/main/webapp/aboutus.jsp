<%--
  Created by IntelliJ IDEA.
  User: bianca
  Date: 11/20/20
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="About Us"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">

    <img src="https://dotunroy.files.wordpress.com/2015/05/happy-people.jpg" width="90%" class="img-fluid"
         alt="Responsive image">
    <h1>About Adlister</h1>


    <div class="row">
        <div class="col-4"> The Adlister is a classified advertisements website with sections devoted to jobs, for sale,
            items wanted, services, community service and gigs. Bianca Reusch, Chelsea Rolland, Michael Troia and Patrick Larkin began the
            service in 2020 as an email distribution list to friends, featuring local events in the San Antonio Area
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/partials/footer.jsp"/>
</body>
</html>
