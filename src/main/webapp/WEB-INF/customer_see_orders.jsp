<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Fog
    </jsp:attribute>


    <jsp:body>

        <h1>Dine Ordre</h1>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tilbud</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="customer_orders" >
                <tr>
                    <th scope="row">${order.orderId}</th>
                    <td>${order.salesPrice}</td>
                    <td>${order.status}</td>
                    <form>
                        <input name="specific_order_id" type="number" value="${order.Id}" style="display: none" readonly/>
                        <!-- TODO: place formaction route -->
                        <button type="submit" formaction="" formmethod="get">See</button>
                    </form>
                    <c:if test="${order.status.equals('Order_placed') || order.status.equals('Creating')}">
                        <form>
                            <button type="submit" formaction="CustomerSeeOrder" formmethod="post">Annuller</button>
                        </form>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>