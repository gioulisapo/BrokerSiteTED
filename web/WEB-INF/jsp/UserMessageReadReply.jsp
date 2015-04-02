<%-- 
    Document   : about
    Created on : Aug 30, 2014, 3:17:49 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/user_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/messagePage.css" rel="stylesheet">
        <title>Read</title>
    </head>
    <body>
        <h3>Message</h3>
        <hr id="hhr">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="col-lg-6">
                        <div class="form-group-lg">
                            <label for="OutputFrom">From</label>
                            <div class="input-group">
                                <input style="height: 48px;" readonly class="form-control form-group" id="OutputFrom" value="<%=session.getAttribute("sender")%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="OutputSubject">Subject</label>
                            <div class="input-group">
                                <input style="height: 48px;" readonly class="form-control" id="OutputSubject" value="<%=session.getAttribute("subject")%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="OutputMessage">Message</label>
                            <div class="input-group">
                                <textarea readonly id="OutputMessage" class="form-control" rows="5" required><%=session.getAttribute("message_body")%></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <a class="email" title="Email a friend" href="#" onclick="javascript:window.location =
                                                'mailto:<%=session.getAttribute("sender")%>?subject=Reply&body=I \n\
                                              thought you might find this information interesting: '">
                <button id='replyButton' class='btn-success btn-lg'> Reply</button>
            </a>

    </body>
</html>
