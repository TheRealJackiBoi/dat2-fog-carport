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

        <h3>You can log in here</h3>

        <div class="container">
        <form action="signup" name="sign_up" method="POST"><br>
            <div class="mx-auto col-10 col-md-8 col-lg-4">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" placeholder="email@email.com">
            </div>
            <div class="mx-auto col-10 col-md-8 col-lg-4">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Pa$$w0RD!"><br>
            </div>
            <div class="col mx-auto col-md-2">
                <button class="btn btn-primary" type="submit" value="Log in" >Login</button>
                <a class="btn btn-warning" href="signup.jsp" role="button">Create user</a>
            </div>
        </form>
        <a href="signup.jsp">Opret Bruger</a><br>
    </jsp:body>
</t:pagetemplate>