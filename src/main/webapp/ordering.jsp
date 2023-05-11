<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Design din carport

    </jsp:attribute>

    <jsp:body>

        <form class="container">
            <form action="ordering" name="ordering" method="post">
                <div class="form inline">
                <div class="col-5 col-md-5 col-lg-2 align-self-end ">
                    <label for="enterHeight">Højde i meter</label>
                    <input type="number" step="0.01" class="form-control" id="enterHeight" name="height" placeholder="Enter Height">
                </div><br>
                <div class="col-5 col-md-5 col-lg-2 align-self-center">
                    <label for="enterWidth">Bredde i meter</label>
                    <input type="number" step="0.01" class="form-control" id="enterWidth" name="width" placeholder="Enter Width">
                </div><br>
                </div>
                <div class="col-5 col-md-5 col-lg-2 align-self-end">
                    <label for="enterLength">Længde i meter</label>
                    <input type="number" step="0.01" class="form-control" id="enterLength" name="length" placeholder="Enter Length">
                </div><br>
                <div class="col-5 col-md-5 col-lg-2 align-self-end">
                    <textarea for="textArea" rows="3"  placeholder="Skrive her"></textarea>
                </div><br>
                <div class="d-grid gap-2 d-md-block">
                    <button class="btn btn-danger" onclick="location.href='index.jsp'">Cancel</button>
                    <button type="button" class="btn btn-primary">Opdater</button>
                    <button type="submit" formaction="ordering" formmethod="post" class="btn btn-success" value="order">Bestil</button>
                </div>
            </form>

        </form>




    </jsp:body>
</t:pagetemplate>