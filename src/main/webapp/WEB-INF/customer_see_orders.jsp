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
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <form>
                    <button type="button">See</button>
                </form>
                <c:if test="${status.equals('Order_placed')}">
                    <form>
                        <button type="button">Annuller</button>
                    </form>
                </c:if>
            </tr>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>