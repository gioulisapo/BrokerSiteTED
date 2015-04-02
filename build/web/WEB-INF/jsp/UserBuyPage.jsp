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
        <link href="css/datepicker.css" rel="stylesheet">
        <script src="js/mapFindHouses.js" type="text/javascript"></script>    
        <link rel="stylesheet" href="css/bootstrap.css">

    </head>
    <body>
        <%if(request.getAttribute("message")!=null){%><span id="respnseText"><span style="padding-right: 6px;" class="glyphicon glyphicon-exclamation-sign"></span><%=request.getAttribute("message")%></span><%}%>
        <h3>Find a Home</h3>
        <hr id="hhr">
        <input id="pac-input" class="controls" type="text" placeholder="Enter One region at a Time">
        <div id="map-canvas"></div>
        <div class="infoContainer">
            
            <div class="formcontainer" style="width: 100%;margin-bottom: 50px;">
                <div class="row" style="width: 100%;">
                    <form action="UserHouseSearchResultsServlet" onsubmit="return confirm('Are you sure all your input is correct?');" name="MainForm" method="post" accept-charset="utf-8" class="form" role="form" style="width: 100%;">
                        <div class="form-group" id="Buyer_Tenant">
                            <label><span class="text-primary" style="font-size: 16px;font-weight: bold;margin-right: 15px;">Buy/Rent/Both</span></label>
                            <label class="checkbox-inline">
                                <input type="checkbox" onclick="BuyRent()" id="Lessor" name="Buyer_Tenant" value="Buy">Buy</label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="Seller" onclick="BuyRent()" name="Buyer_Tenant" value="Lent">Rent</label>
                        </div>
                        <hr>
                        <div class="form-group">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Regions (seperated by commas)</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="RegionWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="RegionWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="RegionWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="RegionWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="RegionWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td><input type="text" style="margin-left: 10px;width: 515px;" class="form-control input-lg" accept-charset="utf-8" name="RegionInput" id="RegionInput" placeholder="Region" required></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Lent">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Rent Price Range</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="LentWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="LentWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="LentWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="LentWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="LentWeight" value="5" class="radio-inline" style='padding-right: -15px;'>5
                                            </div>
                                        </td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style=" width: 250px;margin-left: 10px; padding-left: -15px;" type="number" step="any" class="form-control input-lg" name="LentFromInput" placeholder="Min Price EURO"></td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style="width: 250px;margin-left: 20px;" type="number" step="any" class="form-control input-lg" name="LentToInput" placeholder="Max Price EURO"></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Buy">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Buy Price Range</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="BuyWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="BuyWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="BuyWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="BuyWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="BuyWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style="width: 250px; margin-left: 10px;" type="number" step="any" class="form-control input-lg" name="BuyFromInput" placeholder="Min Price EURO"></td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style="width: 250px; margin-left: 20px;" type="number" step="any" class="form-control input-lg" name="BuyToInput" placeholder="Max Price EURO"></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Lent">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Square Meters</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="SquaresWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="SquaresWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="SquaresWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="SquaresWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="SquaresWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td><input style="width: 250px; margin-left: 10px;" type="number" step="any" class="form-control input-lg" name="SquaresFromInput" placeholder="Min Meters" required></td>
                                        <td><input style="width: 250px; margin-left: 20px;" type="number" step="any" class="form-control input-lg" name="SquaresToInput" placeholder="Max Meters" required></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Heat">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Type of Heat</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="HeatWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="HeatWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="HeatWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="HeatWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="HeatWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-group-lg-6 inline"style="margin-left: 25px;margin-bottom: 7px;">
                                                <input type="radio" name="heat" value="central" class="radio-inline" checked>Central
                                                <input type="radio" name="heat" value="independent" class="radio-inline">Independent
                                                <input type="radio" name="heat" value="none" class="radio-inline">None
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Expense">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Expenses Price Range</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="ExpenseWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="ExpenseWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="ExpenseWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="ExpenseWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="ExpenseWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style="width: 250px; margin-left: 10px;" type="number" step="any" class="form-control input-lg" name="ExpenseFromInput" placeholder="Min Price EURO" required></td>
                                        <td><span id="euroGlyphSmallInput" class="glyphicon glyphicon-euro"></span><input style="width: 250px; margin-left: 20px;" type="number" step="any" class="form-control input-lg" name="ExpenseToInput" placeholder="Max Price EURO" required></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Date">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">House Availability Dates</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 0px;">Search Weight</label>
                                                <input type="radio" name="DateWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="DateWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="DateWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="DateWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="DateWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td>
                                            <div style="margin-right: -15px;margin-left: 10px;">
                                                <label class="control-label"style="margin-right: -30px;margin-left: 12px">From</label>
                                                <div class="input-group date">
                                                    <input readonly="" id="date" style="width: 205px; margin-left: 10px" class="form-control input-lg" type="text" name="DateFromInput" value="22-10-2014">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-calendar"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div style="margin-right: -30px;margin-left: 20px;">
                                                <label class="control-label" style="margin-right: -40px;margin-left: 20px">Until</label>
                                                <div class="input-group date">
                                                    <input readonly="" id="date2" style="width: 205px; margin-left: 20px;" class="form-control input-lg" type="text" name="DateToInput" value="23-10-2014">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-calendar"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group" id="Type">
                            <span class="text-primary" style="font-size: 16px;font-weight: bold;">Type of House</span>
                            <div class="input-group-lg-6">
                                <table style="margin-top: 15px;">
                                    <tr>
                                        <td>
                                            <div class="input-group-lg-6 inline">
                                                <label style="margin-right: 5px;">Search Weight</label>
                                                <input type="radio" name="TypeWeight" value="1" class="radio-inline" checked>1
                                                <input type="radio" name="TypeWeight" value="2" class="radio-inline">2
                                                <input type="radio" name="TypeWeight" value="3" class="radio-inline">3
                                                <input type="radio" name="TypeWeight" value="4" class="radio-inline">4
                                                <input type="radio" name="TypeWeight" value="5" class="radio-inline">5
                                            </div>
                                        </td>
                                        <td>
                                            <div class="input-group-lg-6 inline"style="margin-left: 25px;margin-bottom: 7px;">
                                                <input type="radio" name="TypeInput" value="apartment" class="radio-inline" checked>Apartment
                                                <input type="radio" name="TypeInput" value="house" class="radio-inline">House
                                                <input type="radio" name="TypeInput" value="both" class="radio-inline">Both
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="form-group row">

                        </div>
                        <button class="btn btn-lg btn-primary btn-block confirmMessage" type="submit" id="button">
                            Search</button>
                    </form>
                </div>
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
                                        g_globalObject2 = new JsDatePick({
                                            useMode: 2,
                                            target: "date2",
                                            dateFormat: "%d-%m-%Y"
                                        });
                                    };
        </script>
    </body>
</html>

