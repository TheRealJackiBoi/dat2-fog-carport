<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Fog
    </jsp:attribute>


    <jsp:body>

       <img src="${pageContext.request.contextPath}/images/carport.jpg" class="img-fluid">
        <a href="svg">svg</a>



    </jsp:body>

</t:pagetemplate>