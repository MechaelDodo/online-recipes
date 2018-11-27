<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url var="url_1" value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${url_1}"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <c:url var="url_2" value="/styles/index_style.css"/>
    <link rel="stylesheet" href="${url_2}">
    <c:url var="image_url" value="/images/users.png"/>
    <link rel="icon" href="${image_url}" type="images/x-icon">
    <title><c:out value="Authorization"/></title>
</head>
<body>
<div id="signin_block">
    <div id="title">
        <h3><c:out value="Sign in"/></h3>
    </div>
    <form action="pharmacy" method="post">
        <input type="hidden" name="action" value="signin">
        <p><label for="login"><c:out value="Login: "/></label><input type="text" class="form-control" id="login"
                                                                     name="login" placeholder="Enter login" required/>
        </p>
        <p><label for="password"><c:out value="Password: "/></label><input type="password" class="form-control"
                                                                           name="pass" id="password"
                                                                           placeholder="Enter password"
                                                                           required/></p>
        <p>
            <button class="btn btn-info" type="submit"><c:out value="Sign in"/></button>
        </p>
    </form>
    <p><a href="main.jsp"><c:out value="Continue without authorization?"/></a></p>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<c:url var="script_url_2" value="https://code.jquery.com/jquery-3.3.1.slim.min.js"/>
<script src="${script_url_2}"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<c:url var="script_url_3" value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"/>
<script src="${script_url_3}"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<c:url var="script_url_4" value="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"/>
<script src="${script_url_4}"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>