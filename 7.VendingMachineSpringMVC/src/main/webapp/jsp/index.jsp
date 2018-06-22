<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container">
            <h1>Sosa's Vending Machine</h1>
            <hr/>
        </div>
        
    <div class ="container">

      <div class="row">
    
        <div class = "col-sm-9" style="opacity:.7; height:100%;">


            <c:forEach var="currentItem" items = "${itemList}">

              <a href ="${pageContext.request.contextPath}/selectItem?id=${currentItem.itemNumber}"/>
                <div class="col-sm-3 lhs" style="height:200px;color:black;">
                    <span class="number-fix">${currentItem.itemNumber} </span>
                    <p> ${currentItem.selectionName} </p>
                    <p>  ${currentItem.cost} </p>
                    <p>  ${currentItem.inventory} </p>
                </div>
              </a>

            </c:forEach>
        </div>
              
            

          

        <div class="col-sm-3 right-hand-side" style="height:100%; " >

            <div class="row individual-RHS" >

              <div class="col-sm-12 " style="height:200px;margin:10px">
                <p class = "money-in">Total $ In </p>
                <p id ="moneyIn" class = "money-in" style = "font-size:1.5em;"> Current Balance : ${updatedMoney} </p>
                <div class = "row">
                    <a id="dollar" class="btn-success col-sm-5 purchase-buttons" href="${pageContext.request.contextPath}/addMoney?id=${100}">+ Dollar</a>
                    <a id="quarter" class="btn-success col-sm-5 purchase-buttons col-sm-offset-2" href="${pageContext.request.contextPath}/addMoney?id=${25}">+ Quarter</a>
                    <a id="dime" class="btn-success col-sm-5 purchase-buttons" href="${pageContext.request.contextPath}/addMoney?id=${10}">+ Dime</a>
                    <a id="nickel" class="btn-success col-sm-5 purchase-buttons col-sm-offset-2" href="${pageContext.request.contextPath}/addMoney?id=${5}">+ Nickel</a>
                    
                </div>
              </div>

            </div>
              
            <div class="row individual-RHS">

              <div class="col-sm-12 " style="height:200px;margin:10px">
                <p class = "money-in" >Messages </p>
                <p id ="messages" class = "money-in individual-RHS op7"> MSG: ${messageString} </p>
                
                <div class = "row">
                    <p class = "col-sm-4 item-display extra-style"> Item </p>
                    <p class = "col-sm-6  col-sm-offset-1 individual-RHS extra-style "> Number : ${item} </p>
                    <a id="makePurchase" class="btn-success col-sm-10 col-sm-offset-1 other-buttons" href="${pageContext.request.contextPath}/makePurchase?id=${100}">Make Purchase</a>
                    
                </div>
              </div>

            </div>
              
            <div class="row individual-RHS">

              <div class="col-sm-12 " style="height:200px;margin:10px">
                <p class = "money-in"> Change </p>
                <p id ="changeBreakdown" class = "changeDisplay  change-breakdown">CB: ${changeBreakdown} </p>
                
                <div class = "row">
                    <a id="changeReturn" class="btn-success col-sm-10 col-sm-offset-1 other-buttons" href="${pageContext.request.contextPath}/changeReturn?id=${100}">Change Return</a>
                    
                </div>  
            </div>

            </div>
          </div>
    </div>

        
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

