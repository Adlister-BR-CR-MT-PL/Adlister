<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"></textarea>
            </div>
            <div class="result-sorting col-4">
                <span>Sort By:</span>
                <select class="form-control border-0" id="category" name="category">
                    <option value="1" name="1">Antiques</option>
                    <option value="2" name="2">Appliances</option>
                    <option value="3" name="3">Arts+crafts</option>
                    <option value="4" name="4">Books</option>
                    <option value="5" name="5">Clothes+acc</option>
                    <option value="6" name="6">Computer</option>
                    <option value="7" name="7">Electronics</option>
                    <option value="8" name="8">Free</option>
                    <option value="9" name="9">Furniture</option>
                    <option value="10" name="10">Garage Sale</option>
                    <option value="11" name="11">Music</option>
                    <option value="12" name="12">Toys+games</option>
                    <option value="13" name="13">Antiques</option>
                    <option value="14" name="14">Wanted</option>
                </select>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
    <jsp:include page="../partials/footer.jsp" />
</body>
</html>
