<?php

$personen = array(
  array('name' => "Freeman", 'vorname' => "Morgan"),
  array('name' => "Freeman", 'vorname' => "Martin"),
  array('name' => "Cumberbatch", 'vorname' => "Benedict"),
  array('name' => "Dormer", 'vorname' => "Natalie"),
  array('name' => "Clarke", 'vorname' => "Emilia"),
  array('name' => "Jackman", 'vorname' => "Hugh")
);
usort($personen, function($a, $b) {
  if ($a['name'] < $b['name']) {
    return -1;
  }
  if ($a['name'] > $b['name']) {
    return 1;
  }
  if ($a['vorname'] < $b['vorname']) {
    return -1;
  }
  if ($a['vorname'] > $b['vorname']) {
    return 1;
  }
  return 0;
});
foreach ($personen as $person) {
  printf("%s %s\n", $person['vorname'], $person['name']);
}

