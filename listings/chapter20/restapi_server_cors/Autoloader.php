<?php
  
class Autoloader {
  public static function autoload($classname) {
    $filename = preg_replace('(([A-Z]))', '/\\1', $classname);
    if (substr($filename, 0, 1) != '/') {
      $filename = '/'.$filename;
    }
    $filename .= '.php';
    $path = __DIR__.$filename;
    if (file_exists($path)) {
      include_once($path);
    }
  }
}

