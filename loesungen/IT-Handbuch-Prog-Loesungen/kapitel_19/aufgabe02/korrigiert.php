<?php
/*
Falsche Fassung:

function add($a = 5, $b) {
  $a += $b;
  return $a;
  if $b < 10 {
    return 0;
  }
}

Korrigierte Fassung:
*/
function add($a = 5, $b = 7) { // Parameter ohne Vorgabewert dürfen nicht auf solche mit Vorgabewert folgen
  $a += $b;
  // return $a darf hier noch nicht stehen, denn sonst wäre die Funktion in jedem Fall beendet
  if ($b < 10) {  // Hier fehlten die runden Klammern um die Bedingung
    return 0;
  }
  return $a;      // Korrekte Stelle für das allgemeine return
}

// Test
echo add()."\n";
echo add(10, 20)."\n";
