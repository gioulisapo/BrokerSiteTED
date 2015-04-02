<%-- 
    Document   : UserWelcomePage
    Created on : Aug 30, 2014, 5:03:30 PM
    Author     : Apostolis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.HouseDb"%>
<%@page import="entities.House_PictureEintity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Info</title>

        <link href='http://fonts.googleapis.com/css?family=Voltaire' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/WallopSlider.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/UserContact.css">
    </head>
    <body>
        <div class="bodyContainer">
            <h2 id="Pageheader">Contact <%=session.getAttribute("owner")%></h2>
            <hr id="hhr">
            <div class="infoContainer">
                <div class="row">
                    <form role="form" action="UserContactServlet" method="post" class="ContactForm">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="InputEmail">Your Email</label>
                                <div class="input-group">
                                    <input readonly type="text" class="form-control form-group" name="InputEmail" id="InputEmail" value="<%=session.getAttribute("mail")%>" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputSubject">Subject</label>
                                <div class="input-group">
                                    <input readonly type="text" class="form-control form-group" name="InputSubject" id="InputEmail" value="Question for house [<%=session.getAttribute("houseId")%>] in <%=session.getAttribute("address")%>, <%=session.getAttribute("region")%>" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputMessage">Message</label>
                                <div class="input-group">
                                    <textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
                                </div>
                            </div>
                        </div>
                        <button type="submit" name="submitContact" class="btn-primary btn-lg" id="contactButton">Contact Seller</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- include javascript, jQuery FIRST -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
