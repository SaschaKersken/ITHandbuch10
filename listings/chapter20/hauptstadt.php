<?php

// (Datenbankparameter)
// ...

// Datenbankverbindung
$conn = new mysqli ($host, $root, $pass, $db);

// ID aus der URL ermitteln
$id = $_GET['id'];

// SQL-Abfrage senden
$sql = "SELECT name, hauptstadt FROM laender
        WHERE id=\"$id\"";
$query = $conn->query ($sql);

// Land und Hauptstadt lesen
list ($land, $hauptstadt) = $query->fetch_row();

// Datentyp und -inhalt ausgeben
header ("Content-type: text/plain;
         charset=iso-8859-1");
echo ("$land: $hauptstadt");

