<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <th scope="col">Ordrernummer</th>
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
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${order.materialCost}"/> kr.</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${order.salesPrice}"/> kr.</td>
                <td>
                    <form>
                        <select name="statusselect">
                        <c:forEach var="status" items="${requestScope.statuslist}" varStatus="loop">
                            <c:if test="${status.status.equals(order.status)}">
                                <option value="${status.status}" selected>
                            </c:if>
                            <c:if test="${!status.status.equals(order.status)}">
                                <option value="${status.status}">
                            </c:if>
                                    ${status.status}
                            </option>
                        </c:forEach>
                        </select>
                        <button class="btn btn-primary" type="submit" formaction="admin-change-status"
                            formmethod="post" name="order_id" value="${order.orderId}">OK
                        </button>
                    </form>
                </td>
                <td>
                    <div class="d-flex gap-2 w-100" role="group">
                        <form action="se-din-ordre" method="get">
                            <button type="submit" class="btn btn-primary" name="order_id"  value="${order.orderId}">
                                Se Order
                            </button>
                        </form>

                        <form>
                            <button type="submit" class="btn btn-primary" name="order_id" value="${order.orderId}" formaction="admin_stykliste" formmethod="get">
                                Stykliste
                            </button>
                        </form>
                        <form>
                            <button type="submit" class="btn btn-primary" name="order_id" value="${order.orderId}" formaction="admin_change_sales_price" formmethod="get">
                                Ændre Salgspris
                            </button>
                        </form>

                        <c:if test="${order.status != 'Cancelled'}">
                            <form action="annuller_order" method="post">
                                <button class="btn btn-danger" type="submit" name="order_id" value="${order.orderId}">
                                    Annuller
                                </button>
                            </form>
                        </c:if>
                    </div>
                </td>
            </tr>

        </c:forEach>

        </tbody>
        </table>
    </jsp:body>
</t:pagetemplate>