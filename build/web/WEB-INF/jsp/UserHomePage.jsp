<%-- 
    Document   : UserWelcomePage
    Created on : Aug 30, 2014, 5:03:30 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/user_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=session.getAttribute("username")%></title>
    </head>
    <body>
         <%if (request.getAttribute("Insertmessage") != null)
                if (request.getAttribute("Insertmessage").equals("House Succesfully enlisted"))
                {%>
        <span id="responseTextGreen"><span style="padding-right: 6px;" class="glyphicon glyphicon-exclamation-sign"></span><%=request.getAttribute("Insertmessage")%></span><%
                }else{%>
        <span id="responseTextRed"><span style="padding-right: 6px;" class="glyphicon glyphicon-exclamation-sign"></span><%=request.getAttribute("Insertmessage")%></span><%
                }%>
        <div class="bodyContainer">
            <h3>Welcome <%=session.getAttribute("username")%></h3>
            <hr id="hhr">
            <div class="infoContainer" style="position: absolute;left: 300px;">
                <h4><strong style="color: #259b24;margin-left: 40px;margin-right: 10px;">Fist Name :</strong><%=session.getAttribute("fistName")%></h4>
                <h4><strong style="color: #259b24;margin-left: 40px;margin-right: 10px;">Last Name :</strong><%=session.getAttribute("lastName")%></h4>
                <h4><strong style="color: #259b24;margin-left: 40px;margin-right: 10px;">Country :</strong><%=session.getAttribute("country")%></h4>
                <h4><strong style="color: #259b24;margin-left: 40px;margin-right: 10px;">Mail :</strong><%=session.getAttribute("mail")%></h4>
                <h4><strong style="color: #259b24;margin-left: 40px;margin-right: 10px;">Phone :</strong><%=session.getAttribute("phone")%></h4>
                <%
                    int x = db.MessageDb.CountUnread(session.getAttribute("username").toString());
                    int y = db.HouseDb.CountHousesOfUser(session.getAttribute("username").toString());
                %>
               
            </div>
                 <h4 id="info" style="position: absolute; top: 350px;left: 300px;"><span class="glyphicon glyphicon-envelope red" style="margin-left: 42px;padding-right: 8px;"></span><%=x%> Unread Messages</h4>
                 <h4 id="info" style="position: absolute; top: 382px;left: 300px;"><span class="glyphicon glyphicon-home red" style="margin-left: 42px;padding-right: 8px;"></span><%=y%> Houses Enlisted</h4>
        </div>
    </body>
</html>
