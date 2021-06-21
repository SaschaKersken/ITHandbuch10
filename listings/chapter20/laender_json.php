<?php

// (Datenbankparameter)
// ...

// Datenbankverbindung
$conn = new mysqli($host, $root, $pass, $db);
$conn->query("SET NAMES utf8");

$laender = array();

// Datenbankabfrage
$sql = "SELECT id, name FROM laender
         ORDER BY name ASC";
$query = $conn->query($sql);

while (list($id, $land) = $query->fetch_row()) {
  // Daten in Array packen
  $eintrag = array($id, $land);
  array_push($laender, $eintrag);
}

// JSON-Code erzeugen
$ausgabe = json_encode($laender);

// Ausgabe
header("Content-type: text/plain; charset=utf-8");
echo($ausgabe);

