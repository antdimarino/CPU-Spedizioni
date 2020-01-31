<!DOCTYPE HTML>
<!--
Landed by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>CPU | Spedizioni</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>
<body class="is-preload">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><a href="index.jsp">CPU</a></h1>
        <nav id="nav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="tracking.jsp">Tracking</a></li>
            </ul>
        </nav>
    </header>


    <div id="main" class="wrapper style1">
        <div class="container">
            <header class="major">
                <h2>Spedizioni</h2>
                <p>Benvenuto nella pagina dedicata alle spedizioni, compila il modulo per poter inviare il tuo pacco!</p>
            </header>


            <!-- Form -->
            <section>
                <h3>Compila tutti i campi</h3>
                <form name="f1" method="post" action="/Inserimento" onsubmit="return validateForm()">
                    <div class="row gtr-uniform gtr-50">
                        <div class="col-6 col-12-xsmall">
                            <input type="text" name="nameMittente" id="name" value="" placeholder="Nome Mittente" />
                        </div>
                        <div class="col-6 col-12-xsmall">
                            <input type="text" name="nameDestinatario" id="nameDestinatario" value="" placeholder="Nome Destinatario" />
                        </div>

                        <div class="col-6 col-12-xsmall">
                            <input type="email" name="emailMittente" id="emailMittente" value="" placeholder="Email Mittente" />
                        </div>

                        <div class="col-6 col-12-xsmall">
                            <input type="email" name="emailDestinatario" id="emailDestinatario" value="" placeholder="Email Destinatario" />
                        </div>

                        <div class="col-6">
                            <select name="filialePartenza" id="category">
                                <option value="">- Filiale pi&ugrave; vicina a te -</option>
                                <option value="Ancona">Ancona</option>
                                <option value="Aosta">Aosta</option>
                                <option value="Bari">Bari</option>
                                <option value="Bologna">Bologna</option>
                                <option value="Cagliari">Cagliari</option>
                                <option value="Campobasso">Campobasso</option>
                                <option value="Catanzaro">Catanzaro</option>
                                <option value="Firenze">Firenze</option>
                                <option value="Genova">Genova</option>
                                <option value="L'Aquila">L'Aquila</option>
                                <option value="Milano">Milano</option>
                                <option value="Napoli">Napoli</option>
                                <option value="Palermo">Palermo</option>
                                <option value="Perugia">Perugia</option>
                                <option value="Potenza">Potenza</option>
                                <option value="Roma">Roma</option>
                                <option value="Torino">Torino</option>
                                <option value="Trento">Trento</option>
                                <option value="Trieste">Trieste</option>
                                <option value="Venezia">Venezia</option>
                            </select>
                        </div>

                        <div class="col-6">
                            <select name="filialeDestinazione" id="category2">
                                <option value="">- Filiale pi&ugrave; vicina al Destinatario -</option>
                                <option value="Ancona">Ancona</option>
                                <option value="Aosta">Aosta</option>
                                <option value="Bari">Bari</option>
                                <option value="Bologna">Bologna</option>
                                <option value="Cagliari">Cagliari</option>
                                <option value="Campobasso">Campobasso</option>
                                <option value="Catanzaro">Catanzaro</option>
                                <option value="Firenze">Firenze</option>
                                <option value="Genova">Genova</option>
                                <option value="L'Aquila">L'Aquila</option>
                                <option value="Milano">Milano</option>
                                <option value="Napoli">Napoli</option>
                                <option value="Palermo">Palermo</option>
                                <option value="Perugia">Perugia</option>
                                <option value="Potenza">Potenza</option>
                                <option value="Roma">Roma</option>
                                <option value="Torino">Torino</option>
                                <option value="Trento">Trento</option>
                                <option value="Trieste">Trieste</option>
                                <option value="Venezia">Venezia</option>
                            </select>
                        </div>

                        <div class="col-6 col-12-xsmall">
                            <input type="text" name="viaM" id="indirizzoMittente" value="" placeholder="Via Mittente" />
                        </div>
                        <div class="col-6 col-12-xsmall">
                            <input type="text" name="viaD" id="indirizzoDestinatario" value="" placeholder="Via Destinatario" />
                        </div>

                        <div class="col-6">
                            <input type="text" name="peso" id="peso" value="" placeholder="Inserisci peso pacco in Kg" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  />
                        </div>
                        <div class="col-12">
                            <ul class="actions">
                                <li><input type="submit" value="Richiedi Spedizione" class="primary" /></li>
                                <li><input type="reset" value="Reset" /></li>
                            </ul>
                        </div>
                    </div>
                </form>
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

        <script>
            function validateForm(){
                var x = document.forms["f1"]["peso"].value;
                var double = parseFloat(x);

                if( double > 1000 ){
                    alert("Inserire un peso minore o uguale di 1000 kg");
                    return false;
                }
            }
        </script>

</body>
</html>