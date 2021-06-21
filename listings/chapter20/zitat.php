<?php

// Array mit allen Zitaten erzeugen
$zitate = array(
  "\"To be is to do.\" -- Socrates",
  "\"To do is to be.\" -- Sartre",
  "\"Do be do be do.\" -- Sinatra"
);

// Ein zufälliges Zitat auswählen
$zitat = $zitate[array_rand($zitate)];

// Das Zitat ausgeben
echo $zitat;

