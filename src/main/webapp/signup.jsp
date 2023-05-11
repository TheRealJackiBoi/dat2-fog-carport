<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Fedt, lad os få dig oprettet!
    </jsp:attribute>

    <jsp:body>

        <div class="container mx-auto">
        <form action="signup" name="signup" method="POST">
            <div class="mx-auto col-lg-4">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="email@email.com" required>
            </div>
            <div class="mx-auto col-lg-4">
                <label for="repeatemail">Repeat email</label>
                <input type="email" class="form-control" id="repeatemail" name="repeatemail" placeholder="email@email.com" required>
            </div>
            <div class="mx-auto col-lg-4">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Pa$$w0RD!" required>
            </div>
            <div class="mx-auto col-lg-4">
                <label for="repeatpassword">Repeat password</label>
                <input type="password" class="form-control" id="repeatpassword" name="repeatpassword" placeholder="Pa$$w0RD!" required>
            </div>
            <div class="mx-auto col-md-4">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Jon Bertelsen" required>
            </div>
            <div class="mx-auto col-md-4">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="Østerbrogade 4, 1. th." required>
            </div>
            <div class="row mx-auto col-md-4">
                <div class="col">
                    <label for="city">City</label>
                    <input type="text" id="city" class="form-control" name="city" placeholder="København" required>
                </div>
                <div class="col">
                    <label for="zip">Zip</label>
                    <input type="text" id="zip" class="form-control" name="zip" placeholder="2100" required><br>
                </div>
            </div>
            <div class="row mx-auto col-lg-2">
                <button class="btn btn-primary" type="submit" >Create user</button>
            </div>
        </form>


    </jsp:body>

</t:pagetemplate>
