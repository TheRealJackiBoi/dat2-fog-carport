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
            <tr>
                <td>${order.userEmail}</td>
                <td>${order.orderId}</td>
                <td>${order.materialCost}</td>
                <td>${order.salesPrice}</td>
                <td>${order.status}</td>
                <td>
                    <button formaction="seOrder" class="btn btn-primary" name="item_id" value="seOrder">
                        Se Order
                    </button>
                    <button formaction="annuller" name="item_id" class="btn btn-danger" value="annuller">
                        Annuller
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        </table>




    </jsp:body>

</t:pagetemplate>