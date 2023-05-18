<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Design din carport

    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Design din carport</h1>

        <div class="container">
            <c:if test="${sessionScope.current_order_id != null}">
            <form method="post">
                <div class="form inline">
                <div class="mx-auto col-10 col-md-8 col-lg-3">
                    <label for="enterWidth">Bredde i meter</label>
                    <input type="number" step="0.01" min="2" max="6.80" class="form-control" id="enterWidth" name="width" placeholder="Bredde" value="${requestScope.width}">
                </div><br>
                </div>
                <div class="mx-auto col-10 col-md-8 col-lg-3">
                    <label for="enterLength">Længde i meter</label>
                    <input type="number" step="0.01" min="3.50" max="7.80" class="form-control" id="enterLength" name="length" placeholder="Længde" value="${requestScope.length}">
                </div><br>
                <div class="mx-auto col-10 col-md-8 col-lg-3">
                    <label for="enterHeight">Højde i meter</label>
                    <input type="number" step="0.01" min="2.30" max="3"class="form-control" id="enterHeight" name="height" placeholder="Højde" value="${requestScope.height}">
                </div><br>
                <div class="mx-auto col-10 col-md-8 col-lg-3">
                    <textarea for="textArea" rows="3"  placeholder="Skrive her" class="w-100"></textarea>
                </div><br>
                <div class="mx-auto mx-auto col-10 col-md-8 col-lg-3 d-flex justify-content-between">
                    <button type="submit" formaction="annuller_order" formmethod="post" name="order_id" value="${sessionScope.current_order_id}" class="btn btn-danger">Cancel</button>
                    <button type="submit" formaction="bestil" formmethod="post" name="submit" value="update" class="btn btn-primary">Opdater</button>
                    <button type="submit" formaction="bestil" formmethod="post" name="submit" value="order" class="btn btn-success">Bestil</button>
                </div>
            </form>
            </c:if>
            <c:if test="${sessionScope.current_order_id == null}">
                <form method="post">
                    <div class="form inline">
                        <div class="mx-auto col-10 col-md-8 col-lg-3">
                            <label for="enterHeight">Højde i meter</label>
                            <input type="number" step="0.01" class="form-control" id="enterHeight" name="height" placeholder="Enter Height">
                        </div><br>
                        <div class="mx-auto col-10 col-md-8 col-lg-3">
                            <label for="enterWidth">Bredde i meter</label>
                            <input type="number" step="0.01" class="form-control" id="enterWidth" name="width" placeholder="Enter Width">
                        </div><br>
                    </div>
                    <div class="mx-auto col-10 col-md-8 col-lg-3">
                        <label for="enterLength">Længde i meter</label>
                        <input type="number" step="0.01" class="form-control" id="enterLength" name="length" placeholder="Enter Length">
                    </div><br>
                    <div class="mx-auto col-10 col-md-8 col-lg-3">
                        <textarea for="textArea" rows="3"  placeholder="Skrive her" class="w-100"></textarea>
                    </div><br>
                    <div class="mx-auto mx-auto col-10 col-md-8 col-lg-3 d-flex justify-content-between">
                        <button type="submit" formaction="bestil" formmethod="post" name="submit" value="update" class="btn btn-primary">Opdater</button>
                        <button type="submit" formaction="bestil" formmethod="post" name="submit" value="order" class="btn btn-success">Bestil</button>
                    </div>
                </form>
            </c:if>
        </div>





    </jsp:body>
</t:pagetemplate>