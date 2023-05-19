<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Se alle ordrer
    </jsp:attribute>


    <jsp:body>
        <h1 class="text-center">Se alle ordrer</h1> <br/>

        <form action="admin-view-orders" method="get">
            <label for="search">Søg efter Order Id:</label>
            <input type="number" id="search" name="search">
            <input class="btn btn-primary" type="submit" name="but" value="search">
        </form>
        <br>
        <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Ordrernummber</th>
            <th scope="col">Kostpris</th>
            <th scope="col">Tilbud</th>
            <th scope="col">Status</th>
        </tr>
        <thead>
        <tbody>


        <c:forEach var="order" items="${requestScope.list}">
            <c:if test="${requestScope.userIdSearch == order.orderId}">
            <tr class="table-primary" >
            </c:if>
            <c:if test="${requestScope.userIdSearch != order.orderId}">
                <tr>
            </c:if>

                <td>${order.userEmail}</td>
                <td>${order.orderId}</td>
                <td>${order.materialCost}</td>
                <td>${order.salesPrice}</td>
                <td>${order.status}</td>
                <td>
                    <div class="btn-group" role="group">
                    <form action="admin_stykliste" method="get">
                        <button type="submit" class="btn btn-primary" name="button"  value="${order.orderId}">
                            Se Order
                        </button>
                    </form>
                    <form action="annuller_order" method="post">
                        <button class="btn btn-danger">
                        Annuller
                        </button>
                    </form>
                    </div>
                </td>
            </tr>

        </c:forEach>

        </tbody>
        </table>
    </jsp:body>
</t:pagetemplate>