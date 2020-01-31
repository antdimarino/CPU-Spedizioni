<%@ page import="GenericObject.Edge" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%@ page import="org.joda.time.format.DateTimeFormatter" %>
<%@ page import="GenericObject.Node" %><%--
  Created by IntelliJ IDEA.
  User: secondigliano
  Date: 07/01/20
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CPU | Tracking</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>

    <link rel="stylesheet" href="assets/js/leaflet/leaflet.css" />
    <link rel="stylesheet" href="assets/js/leaflet/leaflet-routing-machine.css" />
    <style>
        #tr{
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #tr2{
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>

</head>
<body>
    <div id="page-wrapper">

        <!-- Header -->
        <header id="header">
            <h1 id="logo"><a href="index.jsp">CPU</a></h1>
        </header>


        <div id="main" class="wrapper style1">
            <div class="container">
                <header class="major">
                    <h2>Tracking</h2>
                    <p>Benvenuto nella pagina dedicata al Tracking, inserisci il codice che hai ricevuto nell'email
                        e potrai verificare dove si trova il tuo pacco!</p>
                </header>

                <section>


                    <form method="post" action="/Tracking">
                        <div class="row gtr-uniform gtr-50">
                            <div id="tr" class="col-12">
                                <input type="text" name="codeSpedizione" id="codeSpedizione" value="" placeholder="Codice Spedizione" />
                            </div>

                            <div id="tr2" class="col-12">
                                <ul class="actions">
                                    <li><input type="submit" value="Tracking" class="primary" /></li>
                                    <li><input type="reset" value="Reset" /></li>
                                </ul>
                            </div>
                        </div>
                    </form>

                    <% String message=(String) request.getAttribute("m");
                        if(message != null){%>
                    <a style="font-family :helvetica;
						color: red; font-size:20px;" >
                        <% out.println(message);%>
                    </a>
                    <%}%>
                </section>

            </div>
        </div>


        <div id="mappa" class="wrapper style3">
            <div class="container">
                <div class="col-12" align="center">
                    <div id = "map" style = "width: 100%; height: 100%; horiz-align: center;">


                    </div>
                </div>
            </div>
        </div>


        <!-- Footer -->
        <footer id="footer">
            <ul class="icons">
                <li><a href="#" class="icon brands alt fa-twitter"><span class="label">Twitter</span></a></li>
                <li><a href="#" class="icon brands alt fa-facebook-f"><span class="label">Facebook</span></a></li>
                <li><a href="#" class="icon brands alt fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
                <li><a href="#" class="icon brands alt fa-instagram"><span class="label">Instagram</span></a></li>
                <li><a href="#" class="icon brands alt fa-github"><span class="label">GitHub</span></a></li>
                <li><a href="#" class="icon solid alt fa-envelope"><span class="label">Email</span></a></li>
            </ul>
            <ul class="copyright">
                <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
            </ul>
        </footer>
    </div>

    <!-- Scripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery.scrolly.min.js"></script>
    <script src="assets/js/jquery.dropotron.min.js"></script>
    <script src="assets/js/jquery.scrollex.min.js"></script>
    <script src="assets/js/browser.min.js"></script>
    <script src="assets/js/breakpoints.min.js"></script>
    <script src="assets/js/util.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="assets/js/leaflet/leaflet.js"></script>
    <script src="assets/js/leaflet/leaflet-routing-machine.js"></script>
    <script src="assets/js/leaflet/MovingMarker.js"></script>
    <script>

        <%List<Node> coord = (ArrayList<Node>) request.getAttribute("coord");
        List<String> d = (ArrayList<String>) request.getAttribute("date");
        if ( coord != null && d != null ) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
            DateTime date = formatter.parseDateTime( d.get(0) );%>

                var map = L.map('map').setView([
                    <%out.print(coord.get(0).getCoord().getKey());%>,
                    <%out.print(coord.get(0).getCoord().getValue());%>],
                    13);

                L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=INSERT_YOUR_API', {
                    attribution: '© OpenStreetMap contributors',
                    id: 'mapbox/streets-v11'
                }).addTo(map);

                var mes = [<% out.print('\"' +d.get(0) + '\"');  for(int i = 1; i < d.size(); i++ )out.print("," + '\"' + d.get(i) + '\"');%>];

                L.Routing.control({
                    router: L.routing.mapbox('pk.eyJ1IjoiYW50ZGltYXJpbm8iLCJhIjoiY2s1NGJ3b2Q3MDU2NjNscnVxeWJzNDF5eCJ9.2TE0hTXiWgtljNPsCFgo3Q'),
                    waypoints: [
                        <%for(int i = 0; i < d.size(); i++){
                            if( i != d.size()-1){ %>
                                L.latLng( <% out.print(coord.get(i).getCoord().getKey()); %> , <% out.print(coord.get(i).getCoord().getValue()); %>  ),
                            <%}else {%>
                                L.latLng( <% out.print(coord.get(i).getCoord().getKey()); %>, <%out.print(coord.get(i).getCoord().getValue()); %>  )
                            <%}
                        }%>
                    ],
                    routeWhileDragging: false,
                    lineOptions: {
                        styles: [{color: 'blue', opacity: 1, weight: 5}]
                    },
                    show: false,
                    createMarker: function(i, nwp){
                        return L.marker(nwp.latLng)
                            .addTo(map)
                            .bindPopup("Il pacco e' arrivato in Filiale in questa data:<br> " + mes[i]);
                            // .bindPopup("Il pacco e' arrivato in Filiale");
                    }
                }).addTo(map);


                <%for(int i = 0; i < d.size(); i++){
                    if( i != 0){
                         date = date.plusHours( (int)coord.get(i).getPeso() );
                         date = date.plusMinutes( (int)(100 * (coord.get(i).getPeso() - (int)coord.get(i).getPeso())) );
                    }
                %>
                        <%--L.marker([<% out.print(coord.get(i).getFirstNode().getCoord().getKey()); %> , <% out.print(coord.get(i).getFirstNode().getCoord().getValue()); %>])--%>
                        <%--    .addTo(map)--%>
                        <%--    .bindPopup("Il pacco e' arrivato in Filiale in questa data: <% out.print( d.get(i) );%> ");--%>

                <%}%>

                <%

                        for(int i = d.size(); i < coord.size(); i++){
                             date = date.plusHours( (int)coord.get(i).getPeso() );
                             date = date.plusMinutes( (int)(100 * (coord.get(i).getPeso() - (int)coord.get(i).getPeso())) );
                                %>

                                L.marker([<% out.print(coord.get(i).getCoord().getKey()); %> , <% out.print(coord.get(i).getCoord().getValue()); %>])
                                    .addTo(map)
                                    .bindPopup("Data stimata di arrivo del pacco in filiale:<br> <%out.print( date.toString(formatter) );%> ");

                <%
                        }
        }%>

    </script>
</body>
</html>
