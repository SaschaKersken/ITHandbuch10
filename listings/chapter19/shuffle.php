<?php

$kartendeck = array();

$farben = ['♧', '♤', '♡', '♢'];
$werte = ['7', '8', '9', '10', 'B', 'D', 'K', 'A'];

foreach ($farben as $farbe) {
  foreach ($werte as $wert) {
    $kartendeck[] = "$farbe$wert";
  }
}

echo implode(" ", $kartendeck);
echo "\n\n";
shuffle($kartendeck);
echo implode(" ", $kartendeck);
echo "\n";

