<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Stylesheet -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body class="bg-secondary">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white py-0">
        <div class="container my-0">
            <a id="fog-logo" class="" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/Fog_logo.svg" class="img"/>
            </a>

            <div class="collapse navbar-collapse justify-content-start" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link text-dark" href="${pageContext.request.contextPath}/">Page 1</a>
                    <a class="nav-item nav-link text-dark" href="${pageContext.request.contextPath}/">Page 2</a>
                    <a class="nav-item nav-link text-dark" href="${pageContext.request.contextPath}/">Page 3</a>
                </div>
            </div>
            <div class="navbar-nav">
                <c:if test="${sessionScope.user == null }">
                    <a class="nav-item nav-link text-dark" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                </c:if>
                <c:if test="${sessionScope.user != null }">
                    <a class="nav-item nav-link text-dark" href="${pageContext.request.contextPath}/logout">Log out</a>
                </c:if>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4 bg-secondary" style="min-height: 400px;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div id="footer" class="bg-white">
    <div id="footer-content" class="container mt-3 bg-white">
        <div class="row mt-4">
            <div class="col">
                <b>Links</b><br/>
                <a>Hjem</a><br/>
                <a>Bestil</a><br/>
                <a>Ordre</a><br/>
                <a>Kontakt</a><br/>
            </div>
            <div class="col">
                <b>Johannes Fog A/S</b><br/>
                Firskovvej 20<br/>
                2800 Lyngby<br/>
                <br/>
                CVR-nr. 16314439<br/>
            </div>
            <div class="col">
                <div id="footer-logo-container">
                    <img class="img" src="${pageContext.request.contextPath}/images/Fog_logo.svg" alt="Fog's Logo">
                </div>
            </div>
        </div>
        <hr/>

    </div>
</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>