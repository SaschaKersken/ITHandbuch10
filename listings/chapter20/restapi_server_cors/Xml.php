<?php

class Xml {
  public function getRecords($records, $root = 'result', $line = 'record') {
    $result = '<?xml version="1.0" encoding="utf-8" standalone="yes"?>';
    $result .= sprintf('<%s>', $root);
    foreach ($records as $id => $record) {
      $result .= sprintf('<%s id="%d">', $line, $id);
      foreach ($record as $field => $value) {
        $result .= sprintf(
          '<%1$s>%2$s</%1$s>',
          htmlspecialchars($field),
          htmlspecialchars($value)
        );
      }
      $result .= sprintf('</%s>', $line);
    }
    $result .= sprintf('</%s>', $root);
    return $result;
  }

  public function getRecord($record, $line = 'record') {
    $result = '<?xml version="1.0" encoding="utf-8" standalone="yes"?>';
    $result .= sprintf('<%s>', $line);
    foreach ($record as $field => $value) {
      $result .= sprintf(
        '<%1$s>%2$s</%1$s>',
        htmlspecialchars($field),
        htmlspecialchars($value)
      );
    }
    $result .= sprintf('</%s>', $line);
    return $result;
  }

  public function getElement($text, $element = 'message') {
    $result = '<?xml version="1.0" encoding="utf-8" standalone="yes"?>';
    $result .= sprintf(
      '<%1$s>%2$s</%1$s>',
      htmlspecialchars($element),
      htmlspecialchars($text)
    );
    return $result;
  }

  public function parse($xml, $fields) {
    $result = array();
    $record = new SimpleXMLElement($xml);
    foreach ($fields as $field) {
      if ($record->{$field} != NULL && !empty((string)$record->{$field})) {
        $result[$field] = (string)$record->{$field};
      }
    }
    return $result;
  }
}
