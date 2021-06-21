<?php

class Table {
  private static $allowedAttributes = array(
    'title', 'style', 'border', 'cellpadding', 'cellspacing'
  );

  private $attributes = array();

  private $rows = array();

  public function __construct($attributes = array()) {
    if (!empty($attributes)) {
      foreach ($attributes as $name => $value) {
        $this->addAttribute($name, $value);
      }
    }
  }

  public function addAttribute($name, $value) {
    if (in_array($name, self::$allowedAttributes)) {
      $this->attributes[$name] = $value;
    }
  }

  public function addRow(Row $row) {
    $this->rows[] = $row;
  }

  public function __toString() {
    $result = '<table';
    foreach ($this->attributes as $name => $value) {
      $result .= sprintf(
        ' %s="%s"',
        $name,
        htmlspecialchars($value)
      );
    }
    $result .= ">\n";
    foreach ($this->rows as $row) {
      $result .= $row->__toString()."\n";
    }
    $result .= "</table>\n";
    return $result;
  }
}

class Row {
  private $cells = array();

  public function addCell(Cell $cell) {
    $this->cells[] = $cell;
  }

  public function __toString() {
    $result = "<tr>\n";
    foreach ($this->cells as $cell) {
      $result .= $cell->__toString()."\n";
    }
    $result .= "</tr>";
    return $result;
  }
}

class Cell {
  private static $allowedAttributes = array(
    'title', 'style', 'align', 'valign'
  );

  private $type = 'td';

  private $attributes = array();

  private $contents = '';

  public function __construct($contents, $type = 'td',
      $attributes = array()) {
    $this->contents = $contents;
    if ($type == 'th') {
      $this->type = 'th';
    }

    if (!empty($attributes)) {
      foreach ($attributes as $name => $value) {
        $this->addAttribute($name, $value);
      }
    }
  }

  public function addAttribute($name, $value) {
    if (in_array($name, self::$allowedAttributes)) {
      $this->attributes[$name] = $value;
    }
  }

  public function __toString() {
    $result = '<'.$this->type;
    foreach ($this->attributes as $name => $value) {
      $result .= sprintf(
        ' %s="%s"',
        $name,
        htmlspecialchars($value)
      );
    }
    $result .= '>';
    $result .= $this->contents;
    $result .= sprintf("</%s>", $this->type);
    return $result;
  }
}

$table = new Table(array('border' => 2, 'cellpadding' => 4));
$row = new Row();
$row->addCell(new Cell('Zeile 1, Zelle 1'));
$row->addCell(new Cell('Zeile 1, Zelle 1'));
$table->addRow($row);
$row = new Row();
$row->addCell(new Cell('Zeile 2, Zelle 1'));
$row->addCell(new Cell('Zeile 2, Zelle 1'));
$table->addRow($row);
echo $table;

