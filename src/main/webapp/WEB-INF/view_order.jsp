<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/svgStyle.css">

<t:pagetemplate>
    <jsp:attribute name="header">
         Din Bestilling
    </jsp:attribute>


    <jsp:body>

        <h1 class="text-center">Tak for din bestilling</h1>

        <div class="w-50 mx-auto my-4">

            <div class=" w-100 text-light mx-auto d-flex gap-3 justify-content-between">
                <c:if test="${requestScope.order.status.equals('Order_placed')}">
                <div class="">
                    <div class="bg-succes circle"><p>1</p></div>
                    <p class="text-dark">Bekræftet</p>
                </div>
                <div class="">
                    <div class="circle bg-info"><p>2</p></div>
                    <p class="text-dark">Behandling</p>
                </div>
                <div class="">
                    <div class="circle bg-info"><p>3</p></div>
                    <p class="text-dark">Accepteret</p>
                </div>
                </c:if>
                <c:if test="${requestScope.order.status.equals('Pending')}">
                    <div class="">
                        <div class="circle bg-succes"><p>1</p></div>
                        <p class="text-dark">Bekræftet</p>
                    </div>
                    <div class="">
                        <div class="circle bg-succes"><p>2</p></div>
                        <p class="text-dark">Behandling</p>
                    </div>
                    <div class="">
                        <div class="circle bg-info"><p>3</p></div>
                        <p class="text-dark">Accepteret</p>
                    </div>
                </c:if>
                <c:if test="${requestScope.order.status.equals('Accepted')}">
                    <div class="">
                        <div class="circle bg-succes"><p>1</p></div>
                        <p class="text-dark">Bekræftet</p>
                    </div>
                    <div class="">
                        <div class="circle bg-succes"><p>2</p></div>
                        <p class="text-dark">Behandling</p>
                    </div>
                    <div class="">
                        <div class="circle bg-succes"><p>3</p></div>
                        <p class="text-dark">Accepteret</p>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="card col-4 mx-auto mt-8 p-4">

            <h2 class="text-center"> Bestilling #${requestScope.order.orderId}</h2>

            <hr/>

            <div class="row w-100 d-flex justify-content-between mt-2">
                <div class="col"><b>Bredde</b></div>
                <div class="col-2 text-end">${requestScope.order.carportWidth} m</div>
            </div>
            <div class="row w-100 d-flex justify-content-between mt-2">
                <div class="col"><b>Længde</b></div>
                <div class="col-2 text-end">${requestScope.order.carportLength} m</div>
            </div>
            <div class="row w-100 d-flex justify-content-between mt-2">
                <div class="col"><b>Højde</b></div>
                <div class="col-2 text-end">${requestScope.order.carportHeight} m</div>
            </div>

            <hr class="mt-4"/>

            <p><b>Total:</b> ${requestScope.order.salesPrice} kr.</p>

        </div>

        <div style="margin: 0 auto" class="moveSvg">
                ${requestScope.svg}
        </div>


    </jsp:body>


</t:pagetemplate>