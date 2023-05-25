<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Fog
    </jsp:attribute>


    <jsp:body>

        <h1 class="text-center">Dine Ordre</h1>
        <br/>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tilbud</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.customer_orders}" >
                <tr>
                    <th scope="row">${order.orderId}</th>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${order.salesPrice}"/> kr.</td>
                    <td>${order.status}</td>
                    <td class="d-flex gap-2">
                        <form class="d-inline-block">
                            <input name="order_id" type="number" value="${order.orderId}" style="display: none" readonly/>
                            <!-- TODO: place formaction route -->
                            <button type="submit" formaction="se-din-ordre" formmethod="get" class="btn btn-primary">Se Ordren</button>
                        </form>
                        <c:if test="${order.status.equals('Order_placed') || order.status.equals('Creating')}">
                            <form class="d-inline-block">
                                <button type="submit" formaction="annuller_order" formmethod="post" class="btn btn-danger" name="order_id" value="${order.orderId}">Annuller</button>
                            </form>
                    </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>