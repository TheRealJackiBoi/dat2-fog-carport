<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Ændre kunder
    </jsp:attribute>

    <jsp:body>
        <h4 class="text-center">Her kan du ændre roller på brugere</h4>
        <form action="/kunder">
            <table class="table table-striped mt-4">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Navn</th>
                    <th>Adresse</th>
                    <th>By</th>
                    <th>Post nr.</th>
                    <th>Rolle</th>
                    <th>Ændre rolle</th>
                </tr>
                <!-- Prints user values from userList into tables -->
                <c:forEach var="user" items="${requestScope.userList}">
                    <tr>
                        <td>
                                ${user.id}
                        </td>
                        <td>
                                ${user.email}
                        </td>
                        <td>
                                ${user.name}
                        </td>
                        <td>
                                ${user.address}
                        </td>
                        <td>
                                ${user.city}
                        </td>
                        <td>
                                ${user.zip}
                        </td>
                        <td>
                                ${user.role}
                        </td>
                        <td>
                            <form>
                                <!-- If the user is a salesman, he cannot changes roles -->
                                <c:choose>
                                    <c:when test="${sessionScope.user.role.equals('admin')}">
                                        <select name="roleSelect">
                                            <c:forEach var="role" items="${requestScope.roleList}" varStatus="loop">
                                                <c:if test="${status.status.equals(order.status)}">
                                                    <option value="${role.role}" selected>
                                                </c:if>
                                                <c:if test="${!status.status.equals(order.status)}">
                                                    <option value="${role.role}">
                                                </c:if>
                                                        ${role.role}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <button class="btn btn-primary" type="submit" formaction="kunder"
                                                formmethod="post" name="user_id" value="${user.id}">OK
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        Kontakt administrator
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </jsp:body>
</t:pagetemplate>