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

        <h1 class="text-center">Ændre dine oplysninger</h1>
        <div class="container w-50 p-2 mt-4">
            <form action="edituser" name="edituser" method="POST">
                <div class="mt-2">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${requestScope.edituser.email}" placeholder="${requestScope.edituser.email}" required>
                </div>
                <div class="mt-2">
                    <label for="password">Kodeord</label>
                    <input type="text" class="form-control" id="password" name="password" value="${requestScope.edituser.password}" placeholder="${requestScope.edituser.password}" required>
                </div>
                <div class="mt-2">
                    <label for="name">Navn</label>
                    <input type="text" class="form-control" id="name" name="name" value="${requestScope.edituser.name}" placeholder="${requestScope.edituser.name}" required>
                </div>
                <div class="form-row mt-2">
                    <label for="address">Adresse</label>
                    <input type="text" class="form-control" id="address" name="address" value="${requestScope.edituser.address}" placeholder="${requestScope.edituser.address}" required>
                </div>

                <div class="d-inline-flex justify-content-between w-100 mt-2">
                    <div class="col-8">
                        <label for="city">By</label>
                        <input type="text" id="city" class="form-control " name="city" value="${requestScope.edituser.city}" placeholder="${requestScope.edituser.city}" required>
                    </div>
                    <div class="col-3">
                        <label for="zip">Post-nr.</label>
                        <input type="text" id="zip" class="form-control" name="zip" value="${requestScope.edituser.zip}" placeholder="${requestScope.edituser.zip}" required><br>
                    </div>
                </div>

                <button class="btn btn-primary col-3" type="submit">Bekræft ændringer</button>
            </form>
        </div>
    </jsp:body>

</t:pagetemplate>