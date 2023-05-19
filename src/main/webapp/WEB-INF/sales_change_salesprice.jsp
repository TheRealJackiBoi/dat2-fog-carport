<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:pagetemplate>
    <jsp:attribute name="header">
        Fog
    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Her kan du ændre prisen mens du forhandler med kunden</h1>
        <form method="post">
            <thead>
            <tr>
                <th>OrderId</th>
                <th>Indkøbs pris</th>
                <th>Foreslået salgspris</th>
                <th>UserId</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>${requestScope.order.orderId}</th>
                <th>${requestScope.order.materialCost}</th>
                <th><div class="mx-auto col-10 col-md-8 col-lg-3">
                    <label for="enterSalesPrice"></label>
                    <input type="number" step="0.01" class="form-control" id="enterSalesPrice" name="enterSalesPrice" placeholder="${requestScope.order.salesPrice}">
                </div></th>
                <th>${requestScope.order.userId}</th>
                <th><div class="mx-auto mx-auto col-10 col-md-8 col-lg-3 d-flex justify-content-between">
                    <button type="submit" formaction="admin_change_sales_price" formmethod="post" name="submit" value="update" class="btn btn-primary">Opdater</button>
                </div></th>
            </tr>
            </tbody>
        </form>
    </jsp:body>

</t:pagetemplate>
