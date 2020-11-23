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
                <input id="title" name="title" class="form-control" type="text" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"></textarea>
            </div>
            <div class="result-sorting col-4">
                <span>Sort By:</span>
                <select class="form-control border-0" id="category" name="category" required>
                    <option value="1"  value="1" >Antiques</option>
                    <option value="2"  value="2">Appliances</option>
                    <option value="3"  value="3">Arts+crafts</option>
                    <option value="4"  value="4">Books</option>
                    <option value="5"  value="5">Clothes+acc</option>
                    <option value="6"  value="6">Computer</option>
                    <option value="7"  value="7">Electronics</option>
                    <option value="8"  value="8">Free</option>
                    <option value="9"  value="9" >Furniture</option>
                    <option value="10" value="10">Garage Sale</option>
                    <option value="11" value="11">Music</option>
                    <option value="12" value="12">Toys+games</option>
                    <option value="13" value="13">Antiques</option>
                    <option value="14" value="14">Wanted</option>
                </select>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
    <jsp:include page="../partials/footer.jsp" />
</body>
</html>
