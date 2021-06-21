<?php

class LikeAnArray implements ArrayAccess, Countable, Iterator {
  private $data = array();

  // ArrayAccess-Methoden

  public function offsetSet($offset, $value) {
    $this->data[$offset] = $value;
  }

  public function offsetGet($offset) {
    return $this->data[$offset];
  }

  public function offsetExists($offset) {
    return isset($this->data[$offset]);
  }

  public function offsetUnset($offset) {
    unset($this->data[$offset]);
  }

  // Countable-Methode

  public function count() {
    return count($this->data);
  }

  // Iterator-Methoden

  public function rewind() {
    reset($this->data);
  }

  public function current() {
    return current($this->data);
  }

  public function key() {
    return key($this->data);
  }

  public function next() {
    return next($this->data);
  }

  public function valid() {
    return $this->current() !== FALSE;
  }
}

$arr = new LikeAnArray();
$arr['beispielschluessel1'] = 'Wert 1';
$arr['beispielschluessel2'] = 'Wert 2';
printf("Es gibt %d Elemente:\n", count($arr));
foreach ($arr as $key => $value) {
  printf("- %s: %s\n", $key, $value);
}

