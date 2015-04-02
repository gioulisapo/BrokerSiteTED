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
        <link rel="stylesheet" href="css/HousePresentation.css">

    </head>
    <body>
        <div class="bodyContainer">
            <h2 id="Pageheader">House Info Card</h2>
            <form action="UserContactServlet" method="post">
                <button type="submit" name="requestContact" class="btn-primary btn-lg" id="contactButton">Contact Seller</button>
            </form>
            <hr id="hhr">
            <div class="infoContainer">
                <h3 id="Houseinfo">House Region: <%=session.getAttribute("region")%></h3>
                <h3 id="Houseinfo">House Address: <%=session.getAttribute("address")%></h3>
                <h3 id="Houseinfo">Price to Buy: <%=session.getAttribute("priceBuy")%><span id="euroGlyph" class="glyphicon glyphicon-euro"></span></h3>
                <h3 id="Houseinfo">Price to Lent: <%=session.getAttribute("priceLent")%><span id="euroGlyph" class="glyphicon glyphicon-euro"></span></h3>
                <h3 id="Houseinfo">Monthly Expenses: <%=session.getAttribute("expenses")%><span id="euroGlyph" class="glyphicon glyphicon-euro"></span></h3>
                <h3 id="Houseinfo">Availability Date: <%=session.getAttribute("date")%></span></h3>
                <h3 id="Houseinfo">Heat Available: <%=session.getAttribute("heat")%></span></h3>
                <h3 id="Houseinfo">Size: <%=session.getAttribute("squares")%> Square Meters</span></h3>
                <textarea id="description" class="form-control" rows="5" cols="3" required><%=session.getAttribute("description")%></textarea>
            </div>
            <div class="pictureContainer">
                <div class="photo-slider wallop-slider wallop-slider--fold">
                    <ul class="wallop-slider__list">
                        <%
                            int i = 0;
                            int PicNo = HouseDb.CountPicturesOfHouse(session.getAttribute("houseId").toString());
                            ArrayList<House_PictureEintity> ResultsArray = (ArrayList) HouseDb.SelectPicturesOfHouse(session.getAttribute("houseId").toString());%>
                        <li class="wallop-slider__item wallop-slider__item--current"><img id="HouseImages" src="ImageServlet?picInfo=<%=session.getAttribute("houseId").toString() + "," + i%>"/>
                        <p id="PhotoDexription"><%=ResultsArray.get(i).getDescription()%></p></li>
                        <%
                            for (i = 1; i < PicNo; i++)
                            {%>
                        <li class="wallop-slider__item"><img id="HouseImages" src="ImageServlet?picInfo=<%=session.getAttribute("houseId").toString() + "," + i%>"/>
                        <p id="PhotoDexription"><%=ResultsArray.get(i).getDescription()%></p></li>
                            <% }%>
                    </ul>
                    <button class="wallop-slider__btn wallop-slider__btn--previous btn btn--previous">Previous</button>
                    <button class="wallop-slider__btn wallop-slider__btn--next btn btn--next">Next</button>
                </div>
            </div>
                    <button type="button" class="btn btn-default">Left</button>
        </div>
        <!-- include javascript, jQuery FIRST -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="js/WallopSlider.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
