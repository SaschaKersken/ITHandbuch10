<?php
class Test {
  public $value = 41;
}

function modifyValue($test) {
  $test->value++;
}

$test = new Test();
modifyValue($test);
echo $test->value."\n";

