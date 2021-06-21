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

class Table extends HtmlTag {
  private $rows = array();

  public function __construct($attributes = array()) {
    parent::__construct('table', $attributes);
    self::$allowedAttributes = array(
      'title', 'style', 'border', 'cellpadding', 'cellspacing'
    );
  }

  public function addRow(Row $row) {
    $this->rows[] = $row;
  }

  public function __toString() {
    foreach ($this->rows as $row) {
      $this->content .= $row->__toString();
    }

    return parent::__toString();
  }
}

class Row extends HtmlTag {
  private $cells = array();

  public function __construct($attributes = array()) {
    parent::__construct('tr', $attributes);
  }

  public function addCell(Cell $cell) {
    $this->cells[] = $cell;
  }

  public function __toString() {
    foreach ($this->cells as $cell) {
      $this->content .= $cell->__toString();
    }
    return parent::__toString();
  }
}

class Cell extends HtmlTag {
  public function __construct($content, $type = 'td',
      $attributes = array()) {
    if ($type != 'td' && $type != 'th') {
      $type = 'td';
    }
    parent::__construct($type, $attributes, $content);
    self::$allowedAttributes = array(
      'title', 'style', 'align', 'valign'
    );
  }
}

$table = new Table(array('border' => 1, 'cellpadding' => 4));
$table->addAttribute('style', 'background-color: #ff0;');
$row1 = new Row();
$row1->addCell(new Cell('Hello World!'));
$row1->addCell(new Cell('New Table'));
$table->addRow($row1);
$row2 = new Row();
$row2->addCell(new Cell('Top content', 'td', array('valign' => 'top')));

$row2->addCell(new Cell('Normal content'));
$table->addRow($row2);
echo $table;

