<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Fog
    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Her kan du ændre prisen mens du forhandler med kunden</h1>
            <table class="table table-hover table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th>User Id</th>
                <th>Order Id</th>
                <th>Indkøbs pris</th>
                <th>Foreslået salgspris</th>
                <th>Dækningsbidrag</th>
                <th>Dækningsgrad</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.order.userId}</td>
                <td>${requestScope.order.orderId}</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.order.materialCost}"/>Kr.</td>
                <td>
                    <form class="d-flex gap-2">
                        <input  type="number" step="0.01" class="form-control w-50" id="enterSalesPrice" name="enterSalesPrice" placeholder="<fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.order.salesPrice}"/>" required>
                        <button type="submit" formaction="admin_change_sales_price" formmethod="POST" name="order_id" value="${requestScope.order.orderId}" class="btn btn-primary">Opdater salgspris</button>
                    </form>
                </td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.contributionMargin}"/>Kr.</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${requestScope.degreeOfCoverage}"/>%</td>
            </tr>
            </tbody>
            </table>
    </jsp:body>

</t:pagetemplate>
