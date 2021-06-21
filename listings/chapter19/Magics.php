<?php

class Magics {
  private $data = array();

  public function __get($name) {
    printf("Attribut '%s' angefordert.\n", $name);
    return $this->data[$name];
  }

  public function __set($name, $value) {
    printf("Attribut '%s' wird auf Wert '%s' gesetzt.\n", $name, $value);
    $this->data[$name] = $value;
  }

  public function __isset($name) {
    printf("Ist Attribut '%s' gesetzt?\n", $name);
    return isset($this->data[$name]);
  }

  public function __unset($name) {
    printf("Attribut '%s' wird vergessen.\n", $name);
    unset($this->data[$name]);
  }

  public function __call($name, $arguments) {
    printf("Aufruf der Methode %s() mit folgenden Argumenten:\n", $name);
    foreach ($arguments as $argument) {
      printf("- %s\n", $argument);
    }
  }
}

$magics = new Magics();
// __get
$magics->testAttribute = 42;
// __set
printf("%d\n", $magics->testAttribute);
// __isset
var_dump(isset($magics->testAttribute));
// __unset
unset($magics->testAttribute);
// Noch einmal __isset
var_dump(isset($magics->testAttribute));
// __call
$magics->testMethod("Argument 1", "Argument 2", "Argument 3");

