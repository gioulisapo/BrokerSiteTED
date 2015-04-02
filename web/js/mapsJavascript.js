function initialize() {

    var markers = [];
    var map = new google.maps.Map(document.getElementById('map-canvas'), {
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-33.8902, 151.1759),
            new google.maps.LatLng(-33.8474, 151.2631));
    map.fitBounds(defaultBounds);

    // Create the search box and link it to the UI element.
    var input = /** @type {HTMLInputElement} */(
            document.getElementById('pac-input'));
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    var searchBox = new google.maps.places.SearchBox(
            /** @type {HTMLInputElement} */(input));

    // [START region_getplaces]
    // Listen for the event fired when the user selects an item from the
    // pick list. Retrieve the matching places for that item.
    google.maps.event.addListener(searchBox, 'places_changed', function() {
        var places = searchBox.getPlaces();
        var geocoder;
        geocoder = new google.maps.Geocoder();
        if (places.length == 0) {
            return;
        }
        for (var i = 0, marker; marker = markers[i]; i++) {
            marker.setMap(null);
        }

        // For each place, get the icon, place name, and location.
        markers = [];
        var bounds = new google.maps.LatLngBounds();
        for (var i = 0, place; place = places[i]; i++) {
            var image = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };

            // Create a marker for each place.
            var marker = new google.maps.Marker({
                map: map,
                icon: image,
                title: place.name,
                position: place.geometry.location
            });
            markers.push(marker);
            bounds.extend(place.geometry.location);
        }
        map.fitBounds(bounds);
        var lat = marker.getPosition().lat();
        var lng = marker.getPosition().lng();
        var latlng = new google.maps.LatLng(lat, lng);
        geocoder.geocode({'latLng': latlng}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
//                if (results[1]) {
                var add = results[1].formatted_address;
                var address = add.split(",", 3);
                var region = address[1].replace(/[0-9]/g, '');//remove ZIP CODE\
                document.getElementById("RegionInput").value = region.trim(); //Remove exes empty spaces     
                //}
            }
        });
        document.getElementById("xInput").value = lng.toString();
        document.getElementById("yInput").value = lat.toString();
                        
        window.scrollBy(0, 250); // horizontal and vertical scroll increments
    });
    // [END region_getplaces]

    // Bias the SearchBox results towards places that are within the bounds of the
    // current map's viewport.
    google.maps.event.addListener(map, 'bounds_changed', function() {
        var bounds = map.getBounds();
        searchBox.setBounds(bounds);
    });
}


google.maps.event.addDomListener(window, 'load', initialize);
