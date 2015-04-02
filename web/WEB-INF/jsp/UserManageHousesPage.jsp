<%-- 
    Document   : SellerPage
    Created on : Aug 30, 2014, 4:40:56 PM
    Author     : Apostolis
--%>

<%@page import="db.HouseDb"%>
<%@page import="entities.HouseEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/user_header.jspf"%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
        <title>Seller</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
        <link rel="stylesheet" href="css/UserManageHouses.css">
    </head>
    <body>
        <h3>Enlisted Houses</h3>
        <hr id="hhr">
        <div id="result">
            <% ArrayList<HouseEntity> ResultsArray = (ArrayList) HouseDb.SelectHousesOfUser(session.getAttribute("username").toString());
                if (!ResultsArray.isEmpty()) {
            %>
            <div id="map-canvas"></div>
            <script type="text/javascript">
                <%
                    if (ResultsArray != null) {
                        int i = 0;%>
                function initialize() {
                    var myLatlng = <%out.print(" new google.maps.LatLng(" + ResultsArray.get(0).getLatitude() + "," + ResultsArray.get(0).getLongitude() + ");\n");%>
                    var mapOptions = {
                        zoom: 10,
                        center: myLatlng
                    };
                    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
                    var image = 'images/house.png';
                <%
                        for (HouseEntity u : ResultsArray) {
                            String lat = u.getLatitude();
                            String lon = u.getLongitude();
                            String name = u.getName();
                            out.print("var myLat" + i + " = new google.maps.LatLng(" + lat + "," + lon + ");\n");
                            out.print("var marker" + i + " = new google.maps.Marker({\n");
                            out.print("position: myLat" + i + ",\n");
                            out.print("map: map,\n");
                            out.print("icon: image,\n");
                            out.print("animation: google.maps.Animation.DROP,\n");
                            out.print("title: '" + name + "'\n");
                            out.print(" });");
                            out.print("google.maps.event.addListener(marker" + i + ", 'click', function() {"
                                    + " document.getElementById('" + i + "').click();"
                                    + "});\n");
                            i++;
                        }
                    }
                %>
                }
                google.maps.event.addDomListener(window, 'load', initialize);
            </script>
            <form class="form-signin" action="HouseInfoServlet" method="post" target="_blank">
                <div class="containerA">

                    <table class="table table-responsive" id="userstable">

                        <thead>
                            <tr>
                                <th>Manage House</th>
                                <th>Name</th>
                                <th>Longitude</th>
                                <th>Latitude</th>
                            </tr>
                        </thead>
                        <%
                            int i = 0;
                            for (HouseEntity u : ResultsArray) {
                        %>
                        <tbody>
                            <tr>
                        <span class="clearfix"></span>
                        <td><button name="house" type="submit" class="btn btn-info" id="<%=i%>" value="<%=u.getUniqueCode()%>">Alter House Info</button></td>
                        <td><%=u.getName()%></td>
                        <td><%=u.getLongitude()%></td>
                        <td><%=u.getLatitude()%></td>
                        </tr>
                        </tbody>
                        <%
                                i++;
                            }
                        %>
                    </table>
                </div>
            </form>
        </div>
        <%
        } else {
        %>
        <h1>No Houses Enlisted. Please Enlist new House</h1><%}%>
    </body>
</html>

