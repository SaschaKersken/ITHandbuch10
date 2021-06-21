<?php

class HtmlTag {
  protected static $allowedAttributes = array('title');

  protected $tagName = '';

  protected $attributes = array();

  protected $content = '';

  public function __construct($tagName, $attributes = array(),
      $content = '') {
    $this->tagName = $tagName;
    if (!empty($attributes)) {
      foreach ($attributes as $name => $value) {
        $this->addAttribute($name, $value);
      }
    }
    $this->content = $content;
  }

  public function addAttribute($name, $value) {
    if (in_array($name, self::$allowedAttributes)) {
      $this->attributes[$name] = $value;
    }
  }

  public function __toString() {
    $result = sprintf('<%s', htmlspecialchars($this->tagName));
    foreach ($this->attributes as $name => $value) {
      $result .= sprintf(
        ' %s="%s"',
        htmlspecialchars($name),
        htmlspecialchars($value)
      );
    }
    if (!empty($this->content)) {
      $result .= ">\n";
      $result .= $this->content;
      $result .= sprintf("</%s>\n", htmlspecialchars($this->tagName));
    } else {
      $result .= "/>\n";
    }
    return $result;
  }
}
