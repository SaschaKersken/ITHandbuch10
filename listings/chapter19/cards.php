<?php	
	
$deckOfCards = [];	
$suits = ['♣', '♠', '♥', '♦'];	
	
$values = ['7', '8', '9', '10', 'B', 'D', 'K', 'A'];	
	
foreach ($suits as $suit) {	
  foreach ($values as $value) {	
    $deckOfCards[] = "$suit$value";	
  }	
}	
	
echo implode(" ", $deckOfCards);	
echo "\n\n";	
shuffle($deckOfCards);	
echo implode(" ", $deckOfCards);	
echo "\n";

