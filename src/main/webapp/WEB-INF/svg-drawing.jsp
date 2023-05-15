<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Carport tegning
    </jsp:attribute>


    <jsp:body>
        <h1>Carport tegning</h1>
        <div class="container">
            <div class="row mt-3">
                ${requestScope.svg}
            </div>

        </div>

    </jsp:body>

</t:pagetemplate>