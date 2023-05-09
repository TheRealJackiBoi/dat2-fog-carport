<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Fedt, lad os få dig oprettet!
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <form action="signup" name="sign_up" method="POST"><br>
                    <div class="mx-auto col-10 col-md-8 col-lg-4">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="email@email.com">
                    </div>
                <div class="mx-auto col-10 col-md-8 col-lg-4">
                    <label for="emailrepeat">Repeat email</label>
                    <input type="email" class="form-control" id="emailrepeat" placeholder="email@email.com">
                </div>
                <div class="mx-auto col-10 col-md-8 col-lg-4">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Pa$$w0RD!">
                    </div>
                <div class="mx-auto col-10 col-md-8 col-lg-4">
                    <label for="repeatpassword">Repeat password</label>
                    <input type="password" class="form-control" id="repeatpassword" placeholder="Pa$$w0RD!">
                </div>
                <div class="mx-auto col-10 col-md-8 col-lg-4">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Jon Bertelsen">
                </div>
                <div class="row mx-auto col-10 col-md-8 col-lg-4">
                    <div class="col">
                        <label for="city">City</label>
                        <input type="text" id="city" class="form-control" placeholder="København">
                </div>
                    <div class="col">
                        <label for="zip">Zip</label>
                        <input type="text" id="zip" class="form-control" placeholder="2100"><br>
                    </div>
                </div>
                <div class="row mx-auto col-lg-2">
                <button class="btn btn-primary" type="submit" >Create user</button>
            </div>


        </form>


    </jsp:body>

</t:pagetemplate>
