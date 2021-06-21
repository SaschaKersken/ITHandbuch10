<?php

class Hyperlink {
  private $href = '';

  private $caption = '';

  public function __construct($href, $caption = '') {
    $this->href = $href;
    $this->caption = $caption;
  }

  public function __toString() {
    $caption = ($this->caption != '') ? $this->caption : $this->href;
    return sprintf(
      '<a href="%1$s" title="%2$s">%2$s</a>',
      $this->href,
      htmlspecialchars($caption)
    );
  }
}

$linkWithCaption = new Hyperlink(
  'http://www.rheinwerk-verlag.de',
  'Rheinwerk Verlag'
);

$linkWithoutCaption = new Hyperlink('http://www.heise.de');

printf("%s\n%s\n", $linkWithCaption, $linkWithoutCaption);

