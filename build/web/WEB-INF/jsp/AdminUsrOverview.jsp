<%-- 
    Document   : adminPage
    Created on : Aug 28, 2014, 5:43:20 PM
    Author     : Apostolis
--%>

<%@page import="db.UserDb"%>
<%@page import="entities.UserEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/admin_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/AdminUserOverview.css" rel="stylesheet">
        <title>User Overview</title>
    </head>
    <body>
        <% ArrayList<UserEntity> ResultsArray = (ArrayList) UserDb.SelectUsers(true);
        if (ResultsArray != null)
        {
            %>
            <form class="form-signin" action="ServletAdminDelete" method="post">
            <div class="container">
                <table class="table table-responsive overflow-y:auto" id="userstable">
                    <thead>
                        <tr>
                            <th>Delete</th>
                            <th>UserName</th>
                            <th>Mail</th>
                            <th>Fist Name</th>
                            <th>Last Name</th>
                            <th>Roles</th>
                            <th>PhoneNo</th>
                            <th>Country</th>
                        </tr>
                    </thead>
                    <%
                    for (UserEntity u : ResultsArray)
                    {
                        %>
                        <tbody>
                            <tr>
                                <td><input type="checkbox" name="username" value="<%=u.getUsername()%>"</td>
                                <span class="clearfix"></span>
                                <td><%=u.getUsername()%></td>
                                <td><%=u.getMail()%></td>
                                <td><%=u.getFirstName()%></td>
                                <td><%=u.getLastName()%></td>
                                <td><%=u.getRole()%></td>
                                <td><%=u.getPhone()%></td>
                                <td><%=u.getCountry()%></td>
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
            <button class="btn btn-warning btn-primary btn-block btn-lg" type="submit" id="DeleteButton">Delete</button>
        </form>
    </body>
</html>
