<%-- 
    Document   : adminPage
    Created on : Aug 28, 2014, 5:43:20 PM
    Author     : Apostolis
--%>

<%@page import="entities.MessageEntity"%>
<%@page import="db.MessageDb"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/user_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/UserInboxPage.css" rel="stylesheet">
        <title>User Overview</title>
    </head>
    <body>
         <h3>Inbox</h3>
            <hr id="hhr">
        <% ArrayList<MessageEntity> ResultsArray = (ArrayList) MessageDb.SelectMessagesOfUser(session.getAttribute("username").toString());
            if (ResultsArray != null) {
        %>
        <form class="form-signin" action="UserMessageMethodsServlet" method="post">
            <div class="container">
                <table class="table table-responsive overflow-y:auto" id="userstable">
                    <thead>
                        <tr>
                            <th>Delete</th>
                            <th>Read</th>
                            <th>Sender</th>
                            <th>Subject</th>
                        </tr>
                    </thead>
                    <%
                        for (MessageEntity u : ResultsArray) {
                    %>
                    <tbody>
                        <tr <%if (!u.isRead()) {%>style="font-weight: bold;background-color: #d4d1d1"<%}%>>
                            <td><input style="margin-left: 17px;" type="checkbox" name="message_id" value="<%=u.getUnique_id()%>"></td>
                            <td><button style="width:80px;" name="read" type="submit" class="btn btn-info" value="<%=u.getUnique_id()%>">Read/Reply</button></td>
                            <span class="clearfix"></span>
                            <td><%=u.getSender_mail()%></td>
                            <td><%=u.getSubject()%></td>
                        </tr>
                    </tbody>
                    <%
                        }
                    %>
                </table>
                <%
                    }
                %>
            </div>
            <button class="btn btn-warning btn-primary btn-block btn-lg" name="delete" type="submit" id="DeleteButton">Delete</button>
        </form>
    </body>
</html>
