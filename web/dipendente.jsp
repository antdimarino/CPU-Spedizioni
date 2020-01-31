<%--
  Created by IntelliJ IDEA.
  User: tony
  Date: 17/12/2019
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CPU | Dipendente</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>
<body>
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><a href="index.jsp">CPU</a></h1>
        <nav id="nav">
            <ul>
                <li><a href="Logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>

    <div id="main" class="wrapper style1">
        <div class="container">
            <header class="major">
                <h2>Homepage</h2>
                <p>Premere il tasto sottostante per poter Aggiornare lo stato dei Veicoli</p>
            </header>

            <section>

                <div class="row gtr-uniform gtr-50">
                    <div class="col-12" align="center">
                        <form method="post" action="/Sincronizzazione">
                            <input type="submit" name="bt" value="Sincronizza Veicoli" class="primary" />
                        </form>
                        <% String message=(String) request.getAttribute("msg");
                            if(message!=null){%>
                        <a style="font-family :helvetica;
						color: red; font-size:20px;" >
                            <% out.println(message);%>
                        </a>
                        <%}%>
                    </div>
                </div>

            </section>

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
</body>
</html>
