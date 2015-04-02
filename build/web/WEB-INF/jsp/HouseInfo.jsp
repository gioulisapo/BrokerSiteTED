<%-- 
    Document   : UserWelcomePage
    Created on : Aug 30, 2014, 5:03:30 PM
    Author     : Apostolis
--%>

<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="org.apache.tomcat.util.http.fileupload.IOUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.HouseDb"%>
<%@page import="entities.House_PictureEintity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/house_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/datepicker.css" rel="stylesheet">
        <link rel="stylesheet" href="css/HouseInfo.css">
        <title>Update <%=session.getAttribute("name")%></title>
    </head>
    <body>
        <form action="HouseDeleteServlet" method="post" accept-charset="utf-8" class="form" role="form">
            <input type="submit" id="deleteHouse" class="btn btn-danger btn-lg" name="deleteHouse" value="Delete"/>
        </form>
        <%if (request.getAttribute("UpdateMessage") != null)
                if (request.getAttribute("UpdateMessage").equals("Updated Succesfully")) {%>
        <span id="responseTextGreen"><span style="padding-right: 6px;" class="glyphicon glyphicon-exclamation-sign"></span><%=request.getAttribute("UpdateMessage")%></span><%
        } else {%>
        <span id="responseTextRed"><span style="padding-right: 6px;" class="glyphicon glyphicon-exclamation-sign"></span><%=request.getAttribute("UpdateMessage")%></span><%
                }%>
        <span id="message">${message}</span>
        <div class="bodyContainer">
            <h3>Update <%=session.getAttribute("name")%></h3>
            <hr id="hhr">
            <div class="form_upload">
                <legend>Add Image</legend>
                <form method="post" action="UploadPhotoServlet"  enctype="multipart/form-data">
                    <span class="help-block">Photo description</span>
                    <input class="form-control input" id="descrForm" type="text" name="description" placeholder="Photo description" size="50"/>
                    <span class="help-block">Choose Photo</span>
                    <input type="file" id="photForm" name="photo" size="50"/>
                    <button type="submit" id="saveBtn" value="Save" class="btn btn-lg btn-primary btn-block confirmMessage">Add Image</button>
                </form>
            </div>
            <div class="infoContainer">
                <div class="row">
                    <div class="col-lg-5 col-centered">
                        <form action="ServletUpdateHouse" method="post" accept-charset="utf-8" class="form" role="form">   <legend>Update House Info</legend>
                            <div class="form-group">
                                <div class="input-group-lg-6">
                                    <span class="help-block">House Name</span>
                                    <input type="text" class="form-control input-lg" value="<%=session.getAttribute("name")%>" id="nameInput" name="nameInput" placeholder="House Name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6">
                                    <span class="help-block">Lent Price</span>
                                    <input type="number" step="any" class="form-control input-lg" id="priceLentInput" value="<%=session.getAttribute("priceLent")%>" name="priceLentInput" placeholder="Price Rent" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6">
                                    <span class="help-block">Buy Price</span>
                                    <input type="number" step="any" class="form-control input-lg" id="priceBuyInput" value="<%=session.getAttribute("priceBuy")%>" name="priceBuyInput" placeholder="Price Sell" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6">
                                    <span class="help-block">Square meters</span>
                                    <input type="number" class="form-control input-lg" value="<%=session.getAttribute("squares")%>" name="squaresInput" placeholder="Square Meters" required>
                                </div>
                            </div>
                            <div class="form-group" style="margin-top: 25px;margin-bottom: 25px;">
                                <label>Heat Installed :</label>
                                <div class="input-group-lg-6 inline">
                                       <input type="radio" name="heat" value="central" class="radio-inline" <%if (session.getAttribute("heat").equals("central")) {%>checked <%}%>>Central
                                           <input type="radio" name="heat" value="independent" class="radio-inline" <%if (session.getAttribute("heat").equals("independent")) {%>checked <%}%>>Independent
                                           <input type="radio" name="heat" value="none" class="radio-inline" <%if (session.getAttribute("heat").equals("none")) {%>checked <%}%>>None
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6 inline">
                                    <span class="help-block">Expenses (EUROS)</span>
                                    <input type="number" step="any" class="form-control input-lg" value="<%=session.getAttribute("expenses")%>" name="expensesInput" placeholder="Expenses" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6 inline">
                                    <span class="help-block">Time till Available</span>
                                    <div class="input-group date">
                                        <input readonly id="date" class="form-control input-lg" type="text" name="timeAvailableInput" value="<%=session.getAttribute("timeAvailable")%>">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-calendar"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group-lg-6 inline">
                                    <span class="help-block">House Description</span>
                                    <textarea type="text" class="form-control"  accept-charset="utf-8" style="height: 120px;" name="descriptionInput" 
                                              placeholder="Desciption" style="" required><%=session.getAttribute("description")%></textarea> 
                                </div>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block confirmMessage" onclick="confirmModal()" type="submit" id="button">
                                Update House Info</button>
                        </form>
                    </div>
                    <br><br>
                </div>
            </div>
        </div>
        <div class="pictureContainer">
            <legend id="DeleteLeg">Delete Pictures</legend>

            <form action="ServletImageDelete" method="post" accept-charset="utf-8" class="form" role="form">
                <div id="PictureDeleteForm">
                        <%
                            int i;
                            int PicNo = HouseDb.CountPicturesOfHouse(session.getAttribute("houseId").toString());
                            ArrayList<House_PictureEintity> ResultsArray = (ArrayList) HouseDb.SelectPicturesOfHouse(session.getAttribute("houseId").toString());
                            for (i = 0; i < PicNo; i++) {
                                if (i % 2 == 0) {%><div class="left"><%} else {%><div class="right"><%}%>
                            <div class="form-group">
                                <img id="HouseImages" src="ImageServlet?picInfo=<%=session.getAttribute("houseId").toString() + "," + i%>"/>
                                <input id="checkToDelete" type="checkbox" name="picture" value="<%=ResultsArray.get(i).getUniqueId()%>">
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <button class="btn btn-warning btn-primary btn-block btn-lg" type="submit" id="DeleteButton">Delete</button>
            </form>
        </div>
    </div>
    <script src="js/datepicker.js"></script> 
    <script type="text/javascript">
                                window.onload = function() {
                                    g_globalObject = new JsDatePick({
                                        useMode: 2,
                                        target: "date",
                                        dateFormat: "%d-%m-%Y"
                                    });
                                };
    </script>
</body>
</html>
