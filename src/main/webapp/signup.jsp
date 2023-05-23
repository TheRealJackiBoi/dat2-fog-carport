<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Opret Bruger
    </jsp:attribute>

    <jsp:body>
        <h1 class="text-center">Opret Din Bruger</h1>
        <h4 class="text-center">
            <c:if test="${sessionScope.error}">
                <c:out value="Denne email er allerede registreret. Log ind eller vælg en anden email"></c:out>
            </c:if>
        </h4>
        <div class="container w-50 p-2 mt-4">
        <form action="signup" name="signup" onsubmit="return finalCheck()" method="POST">
            <div class="mt-2">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="email@email.com">
            </div>
            <div class="mt-2">
                <label for="repeatemail">Gentag email</label>
                <input type="email" class="form-control" id="repeatemail" name="repeatemail" placeholder="email@email.com"
                       onkeyup='checkEmail();' /> <span id='Emessage'></span>
            </div>

            <div class="mt-2">
                <label for="password">Kodeord</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Pa$$w0RD!" >
            </div>

            <div class="mt-2">
                <label for="repeatpassword">Gentag Kodeord</label>
                <input type="password" class="form-control" id="repeatpassword" name="repeatpassword" placeholder="Pa$$w0RD!"
                       onkeyup='checkPass();'  /> <span id='message'></span>
            </div>

            <div class="mt-2">
                <label for="name">Navn</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Jon Bertelsen" >
            </div>
            <div class="form-row mt-2">
                <label for="address">Adresse</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="Østerbrogade 4, 1. th." >
            </div>

            <div class="d-inline-flex justify-content-between w-100 mt-2">
                <div class="col-8">
                    <label for="city">By</label>
                    <input type="text" id="city" class="form-control " name="city" placeholder="København" >
                </div>
                <div class="col-3">
                    <label for="zip">Post-nr.</label>
                    <input type="number" id="zip" class="form-control" name="zip" placeholder="2100" ><br>
                </div>
            </div>
                <button class="btn btn-primary col-3 float-center" type="submit" name="signupbtn" id="signupbtn" >Opret Bruger</button>
        </form>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/MatchChecker.js"></script>

    </jsp:body>

</t:pagetemplate>
