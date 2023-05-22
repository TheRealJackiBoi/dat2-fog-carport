<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
        Fog
    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Her kan du ændre lagerpriserne</h1>
        <br/>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>MaterialeId</th>
                <th>Description</th>
                <th>Enhed</th>
                <th>Type</th>
                <th>Pris pr enhed</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="materials" items="${requestScope.materials}">
                <tr>
                    <td>${materials.materialId}</td>
                    <td>${materials.description}</td>
                    <td>${materials.unit}</td>
                    <td>${materials.type}</td>
                    <td><form method="POST">
                        <label for="enterCostPrice"></label>
                        <input type="number" step="0.01" class="form-control" id="enterCostPrice" name="newcostprice" placeholder="${materials.unitPrice}">
                        <input type="hidden" value="${materials.materialId}" name="materialid" id="materialid">
                        <input type="submit" formaction="admin_change_costprices"  value="submit">
                    </form></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>
