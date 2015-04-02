<%-- 
    Document   : adminPage
    Created on : Aug 28, 2014, 5:43:20 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/admin_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/AdminHomePage.css" rel="stylesheet">
        <title>Home page</title>
    </head>
    <body>
        <div class="bodyContainer">
            <h3>Welcome <%=session.getAttribute("username")%></h3>
            <hr id="hhr">
            <%  
                int z = db.UserDb.CountTotal();
            %>  
            <h4 id="info"><span class="glyphicon glyphicon-user"></span>There are: <%=z%> Total users</h4>
            <%  
                int x = db.UserDb.CountPending();
            %>  
            <h4 id="info"><span class="glyphicon glyphicon-user yellow"></span> There are: <%=x%> Pending to be accepted</h4>
            <%  
                int y = db.UserDb.CountActive();
            %>  
            <h4 id="info"><span class="glyphicon glyphicon-user green"></span>There are: <%=y%> Active users</h4>
<!--            Allo ena me glyphicon glyphicon-home gia ton algorithmo-->
            <%  
                int roles [] = db.UserDb.CountActiveRoles();
            %>
            <div class="secondaryinfo">
                <h4 id="infoSec"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[0]%> active Administrators</h4>
                <h4 id="infoSec"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[1]%> active Lessors</h4>
                <h4 id="infoThrd"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[2]%> active Sellers</h4>
                <h4 id="infoThrd"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[3]%> active Tenants</h4>
                <h4 id="infoThrd"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[4]%> active Buyers</h4>
                <h4 id="infoThrd"><span class="glyphicon glyphicon-user lightgreen"></span>There are: <%=roles[5]%> active Visitors</h4>
            </div>
        </div>
    </body>
</html>
