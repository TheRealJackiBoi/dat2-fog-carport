<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Login
    </jsp:attribute>

    <jsp:body>

        <h3 class="text-center">Log ind</h3>

        <div class="container">
        <form action="login" name="login" method="GET"><br>
            <div class="mx-auto col-10 col-md-8 col-lg-4">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="email@email.com">
            </div>
            <div class="mx-auto col-10 col-md-8 col-lg-4">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Pa$$w0RD!"><br>
            </div>
            <div class="col mx-auto col-md-2">
                <a class="" href="signup.jsp">Create user</a>
                <button class="btn btn-primary" type="submit" >Login</button>
            </div>
        </form>

        </div>
    </jsp:body>
</t:pagetemplate>