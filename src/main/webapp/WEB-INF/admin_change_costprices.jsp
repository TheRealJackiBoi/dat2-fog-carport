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

        <h1 class="text-center">Her er styklisten for denne ordre</h1>
        <br/>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>MaterialeId</th>
                <th>Description</th>
                <th>Enhed</th>
                <th>Pris pr enhed</th>
                <th>Type</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="materials" items="${requestScope.materials}">
                <tr>
                    <th>${materials.materialId}</th>
                    <th>${materials.description}</th>
                    <th>${materials.unit}</th>
                    <th>${materials.unitPrice}</th>
                    <th>${materials.type}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>
