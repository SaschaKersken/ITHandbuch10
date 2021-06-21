<?php

class Dba extends Database {
  protected $fields = array();

  protected function recordToResult($record, $fields = NULL) {
    if ($fields === NULL) {
      $fields = $this->fields;
    }
    $result = array();
    if (is_array($record)) {
      foreach ($record as $field => $value) {
        if (isset($fields[$field])) {
          $result[$fields[$field]] = $value;
        } else {
          $result[$field] = $value;
        }
      }
    }
    return $result;
  }

  protected function resultToRecord($result) {
    return $this->recordToResult($result, array_flip($this->fields));
  }
}
