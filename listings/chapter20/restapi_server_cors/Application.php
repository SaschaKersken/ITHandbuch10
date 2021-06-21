<?php
  
class Application {
  public function run() {
    $path = strtok($_SERVER['REQUEST_URI'], '?');
    $elements = preg_split('(/)', $path);
    while (empty($elements[0])) {
      array_shift($elements);
    }
    $classname = ucfirst(array_shift($elements));
    $method = strtolower($_SERVER['REQUEST_METHOD']);
    $headersOnly = FALSE;
    if ($method == 'head') {
      $method = 'get';
      $headersOnly = TRUE;
    }
    if (!class_exists($classname)) {
      $message = sprintf("<error>Unknown resource %s.</error>", $classname);
      header("HTTP/1.1 404 Not Found");
      header("Content-type: application/xml");
      header(sprintf("Content-length: %d", strlen($message)));
      if (!$headersOnly) {
        echo $message;
      }
      return;
    }
    $instance = new $classname();
    if (!method_exists($instance, $method)) {
      $message = sprintf("<error>Method %s not implemented.</error>", $method);
      header("HTTP/1.1 501 Not Implemented");
      header("Content-type: application/xml");
      header(sprintf("Content-length: %d", strlen($message)));
      echo $message;
      return;
    }
    $result = $instance->$method($elements);
    header(
      sprintf(
        "HTTP/1.1 %d %s",
        $instance->statusCode(),
        $instance->statusMessage()
      )
    );
    header(sprintf("Content-type: %s", $instance->contentType()));
    header(sprintf("Content-length: %d", strlen($result)));
    if (!$headersOnly) {
      echo $result;
    }
  }
}

