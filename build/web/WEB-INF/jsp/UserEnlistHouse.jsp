<%-- 
    Document   : SellerPage
    Created on : Aug 30, 2014, 4:40:56 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/user_header.jspf"%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <link href="css/UserBuyPage.css" rel="stylesheet">
        <meta charset="utf-8">
        <title>Seller</title>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places&language=en" type="text/javascript"></script>
        <script src="js/mapsJavascript.js"></script>
        <link href="css/datepicker.css" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <h3>Enlist New House</h3>
        <hr id="hhr">
        <input id="pac-input" class="controls" type="text" placeholder="Enter House Coordinates/Address">
        <div id="map-canvas"></div>
        <div class="infoContainer">
            <div class="formcontainer" style="width: 100%;margin-bottom: 50px;">
                <div class="row" style="width: 100%;">
                    <form action="ServletSellHouse" onsubmit="return confirm('Please check again the location information of the house. House location cannot be edited after submition');" name="MainForm" method="post" accept-charset="utf-8" class="form" role="form" style="width: 100%;">
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Longitude X-axis</span>
                                <input style="margin-top:5px;" type="number" step="any" class="form-control input-lg" id="xInput" name="xInput" placeholder="Longitde" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span id="InfoSpan" class="text-primary" style="font-size: 16px;font-weight: bold;">Longitude y-axis</span>
                                <input style="margin-top:5px;" type="number" step="any" class="form-control input-lg" id="yInput" name="yInput" placeholder="Latitude" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Address</span>
                                <input style="margin-top:5px;" type="text" class="form-control input-lg" name="addressInput" id="addressInput" placeholder="Address" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Region</span>
                                <input style="margin-top:5px;" type="text" class="form-control input-lg" accept-charset="utf-8" name="RegionInput" id="RegionInput" placeholder="Region" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">House Name</span>
                                <input style="margin-top:5px;" type="text" class="form-control input-lg" name="nameInput" placeholder="Memory trigger name to the house" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                 <span id="euroGlyph" class="glyphicon glyphicon-euro"></span>
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Sell Price</span>
                                <input style="margin-top:5px;" type="number" step="any" id="money" class="form-control input-lg" name="priceBuyInput" placeholder="Price to Sell" required>
                                
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Rent Price</span>
                                <span id="euroGlyph" class="glyphicon glyphicon-euro"></span>
                                <input style="margin-top:5px;" type="number" step="any" id="money" class="form-control input-lg" name="priceLentInput" placeholder="Price to Rent" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Square Meters</span>
                                <input style="margin-top:5px;" type="number" step="any" class="form-control input-lg" name="squaresInput" placeholder="Square Meters" required>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 25px;margin-bottom: 25px;">
                           <span class="text-primary" style="font-size: 16px;font-weight: bold;">Heat type</span>
                            <div style="margin-top:5px;" class="input-group-lg-6 inline">
                                <input type="radio" name="heat" value="central" class="radio-inline" checked>Central
                                <input type="radio" name="heat" value="independent" class="radio-inline">Independent
                                <input type="radio" name="heat" value="none" class="radio-inline">None
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6 inline">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">Expenses</span>
                                <span id="euroGlyph" class="glyphicon glyphicon-euro"></span>
                                <input style="margin-top:5px;" type="number" step="any" class="form-control input-lg" name="expensesInput" placeholder="Expenses EUROS" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Availability Date</span>
                            <div style="margin-top:5px;" class="input-group date">
                                <input readonly id="inputFieldDate" class="form-control input-lg" type="text" name="timeAvailableInput" value="23-10-2014" required>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-home"></span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 25px;margin-bottom: 25px;">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Type of Building</span>
                            <div style="margin-top:5px;" class="input-group-lg-6 inline">
                                <input type="radio" name="type" value="apartment" class="radio-inline" checked>Apartment
                                <input type="radio" name="type" value="house" class="radio-inline">House
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group-lg-6 inline">
                                <span class="text-primary" style="font-size: 16px;font-weight: bold;">House Description</span>
                                <textarea type="text" class="form-control" accept-charset="utf-8" style="height: 120px;margin-top:5px;" name="descriptionInput" 
                                          placeholder="Desciption" style=";" required></textarea> 
                            </div>
                        </div>
                        <span class="help-block">House Photos can be added later from house management page</span>
                        <button class="btn btn-lg btn-primary btn-block confirmMessage" type="submit" id="button">
                            Submit House</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/datepicker.js"></script> 
        <script type="text/javascript">
                        window.onload = function() {
                            new JsDatePick({
                                useMode: 2,
                                target: "inputFieldDate",
                                dateFormat: "%d-%m-%Y"
                            });
                        };
        </script>
    </body>
</html>

