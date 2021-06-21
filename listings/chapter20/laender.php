<?php

// Datenbankparameter (anpassen)
$host = "localhost";
$user = "dbuser";
$pass = "Ihr Passwort";
$db = "welt";

// Datenbankverbindung (mysqli)
$conn = new mysqli($host, $user, $pass, $db);
// Jegliche Kommunikation mit der Datenbank in UTF-8
$conn->query("SET NAMES utf8");

// Dokumentbeginn: XML-PI, öffnendes <laender>-Tag
$laender = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
$laender .= "<laender>";

// SQL-Abfrage
$sql = "SELECT id, name FROM laender
        ORDER BY name ASC";
$query = $conn->query($sql);

// Daten aus der Datenbank lesen
while (list($id, $land) = $query->fetch_row()) {
  // als <land>-Element in den XML-Code schreiben
  $eintrag =
        "<land><id>$id</id><name>$land</name></land>";
  $laender .= $eintrag;
}

// Schließendes </laender>-Tag
$laender .= "</laender>";

// MIME-Type und Dokument ausgeben
header("Content-type: text/xml; charset=utf-8");
echo($laender);

