<%-- 
    Document   : about
    Created on : Aug 30, 2014, 3:17:49 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>About Us</title>
    </head>
    <body>
        <div class="container" style="margin-top: 80px;">
            <div class="row">
                <div class="col-md-12">
                    <form role="form" action="" method="post">
                        <div class="col-lg-6">
                            <div class="form-group-lg">
                                <label for="InputName">Your Name</label>
                                <div class="input-group">
                                    <input style="height: 48px;" type="text" class="form-control form-group" name="InputName" id="InputName" placeholder="Enter Name" required>
                                    <span class="input-group-addon" style="background-color:#6bc68f;"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputEmail">Your Email</label>
                                <div class="input-group">
                                    <input style="height: 48px;" type="email" class="form-control" id="InputEmail" name="InputEmail" placeholder="Enter Email" required  >
                                    <span class="input-group-addon"  style="background-color:#6bc68f;"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputMessage">Message</label>
                                <div class="input-group">
                                    <textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
                                    <span class="input-group-addon"  style="background-color:#6bc68f;"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right" method="post" action="ContactServlet">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
