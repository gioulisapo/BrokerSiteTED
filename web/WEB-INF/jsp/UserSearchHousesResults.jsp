<%-- 
    Document   : SellerPage
    Created on : Aug 30, 2014, 4:40:56 PM
    Author     : Apostolis
--%>

<%@page import="db.AlgorithmDb"%>
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
        <link rel="stylesheet" href="css/UserHousesSearchResults.css">

    </head>
    <body>
        <form method="post" action="HousePresentationServlet" target="_blank">
            <%
                ArrayList<HouseEntity> ResultsArray = new ArrayList<HouseEntity>();
                ResultsArray = (ArrayList<HouseEntity>) session.getAttribute("resultsArray");
                if (ResultsArray != null && !ResultsArray.isEmpty())
                {
            %>
            <div id="map-canvas"></div>
            <script type="text/javascript">
                <%
                    if (ResultsArray != null)
                    {
                        int i = 0;%>
            function initialize() {
                var myLatlng = <%out.print(" new google.maps.LatLng(" + ResultsArray.get(0).getLatitude() + "," + ResultsArray.get(0).getLongitude() + ");\n");%>
                var mapOptions = {
                    zoom: 6,
                    center: myLatlng
                };
                var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
                var image = 'images/house.png';
                <%
                        for (HouseEntity u : ResultsArray)
                        {
                            String lat = u.getLatitude();
                            String lon = u.getLongitude();
                            String date = u.getDateAvailable();
                            String heat = u.getHeat().substring(0, 1).toUpperCase() + u.getHeat().substring(1); // capitalize the first letter
                            int squares = u.getSquares();
                            String location = u.getRegion();
                            String type = u.getType();
                            String content = "<p><h4>Region: " + location + "</h4></p>"
                                    + "<p><h4>Square Meters: " + squares + "</h4></p>"
                                    + "<p><h4>Type of heat available: " + heat + "</h4></p>"
                                    + "<p><h4>" + type + " will be available at: " + date + "</h4></p>"
                                    + "<button style=\"margin-bottom: 20px;\" name=\"house\" type=\"submit\" class=\"btn btn-info\" id=\"" + i + "\" value=\"" + u.getUniqueCode() + "\">more</button>";
                            out.print("var myLat" + i + " = new google.maps.LatLng(" + lat + "," + lon + ");\n");
                            out.print("var contentString" + i + " = '" + content + "';\n");
                            out.print("var infowindow" + i + " = new google.maps.InfoWindow({\n");
                            out.print("content: contentString" + i + "\n");
                            out.print("});\n");
                            out.print("var marker" + i + " = new google.maps.Marker({\n");
                            out.print("position: myLat" + i + ",\n");
                            out.print("icon: image,\n");
                            out.print("map: map,\n");
                            out.print("animation: google.maps.Animation.DROP,\n");
                            out.print(" });");
                            out.print("google.maps.event.addListener(marker" + i + ", 'click', function() {"
                                    + " infowindow" + i + ".open(map,marker" + i + ");"
                                    + "});\n");
                            i++;
                        }
                    }
                %>
            }
            google.maps.event.addDomListener(window, 'load', initialize);
            </script>
            <%
            }
            else
            {
            %>
        </form>
        <h1>No Houses Enlisted. Please Enlist new House</h1><%}%>
    </body>
</html>

