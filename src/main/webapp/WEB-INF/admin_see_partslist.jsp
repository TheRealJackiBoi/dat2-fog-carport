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

        <h1 class="text-center">Her er styklisten for denne ordre</h1>
        <br/>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>Beskrivelse</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Formål</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="partsList" items="${requestScope.partslist}">
                <tr>
                    <th>${partsList.dimensions}</th>
                    <th><fmt:formatNumber type="number" maxFractionDigits="0" value="${partsList.length}"/></th>
                    <th>${partsList.quantity}</th>
                    <th>${partsList.unit}</th>
                    <th>${partsList.useDescription}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>
