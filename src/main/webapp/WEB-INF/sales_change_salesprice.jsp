<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
        Fog
    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Her kan du ændre prisen mens du forhandler med kunden</h1>
        <form method="post">
            <thead>
            <tr>
                <th>UserId</th>
                <th>OrderId</th>
                <th>Indkøbs pris</th>
                <th>Foreslået salgspris</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.order.userId}</td>
                <td>${requestScope.order.orderId}</td>
                <td>${requestScope.order.materialCost}</td>
                <form action="admin_change_sales_price">
                <td><div class="mx-auto col-10 col-md-8 col-lg-3">
                    <label for="enterSalesPrice"></label>
                    <input type="number" step="0.01" class="form-control" id="enterSalesPrice" name="enterSalesPrice" placeholder="${requestScope.order.salesPrice}">
                </div></td>
                <td><div class="mx-auto mx-auto col-10 col-md-8 col-lg-3 d-flex justify-content-between">
                    <button type="submit" formaction="admin_change_sales_price" formmethod="POST" name="submit" value="${requestScope.order.orderId}" class="btn btn-primary">Opdater salgspris</button>
                </div></td>
                </form>
            </tr>
            </tbody>
        </form>
    </jsp:body>

</t:pagetemplate>
