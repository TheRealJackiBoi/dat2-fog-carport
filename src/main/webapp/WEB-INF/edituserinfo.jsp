<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<!-- TODO: NEEDS TO BE LINKED FROM SOMEWHERE -->


<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the logged in area
    </jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="../login.jsp">Login</a></p>
        </c:if>

        <div class="container mx-auto">
        <form action="edituser" name="edituser" method="POST">
        <div class="mx-auto col-lg-4">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${requestScope.email}" required>
        </div>
        <div class="mx-auto col-lg-4">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="${requestScope.password}" required>
        </div>
        <div class="mx-auto col-md-4">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Jon Bertelsen" required>
        </div>
        <div class="mx-auto col-md-4">
            <label for="address">Address</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="Østerbrogade 4, 1. th." required>
        </div>
        <div class="row mx-auto col-md-4">
            <div class="col">
                <label for="city">City</label>
                <input type="text" id="city" class="form-control" name="city" placeholder="København" required>
            </div>
            <div class="col">
                <label for="zip">Zip</label>
                <input type="text" id="zip" class="form-control" name="zip" placeholder="2100" required><br>
            </div>
        </div>
        </form>
    </jsp:body>

</t:pagetemplate>