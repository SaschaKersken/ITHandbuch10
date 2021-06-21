<?php

class Api {
  protected $xml = NULL;
  protected $contentType = "text/xml";
  protected $statusCode = 200;
  protected $statusMessage = "OK";

  public function get($elements) {
    return $this->notImplemented();
  }

  public function post($elements) {
    return $this->notImplemented();
  }

  public function put($elements) {
    return $this->notImplented();
  }

  public function delete($elements) {
    return $this->notImplemented();
  }

  public function contentType($type = NULL) {
    if ($type !== NULL) {
      $this->contentType = $type;
    }
    return $this->contentType;
  }

  public function statusCode($code = NULL) {
    if ($code !== NULL) {
      $this->statusCode = $code;
    }
    return $this->statusCode;
  }

  public function statusMessage($message = NULL) {
    if ($message !== NULL) {
      $this->statusMessage = $message;
    }
    return $this->statusMessage;
  }

  protected function notFound() {
    $this->statusCode(404);
    $this->statusMessage('Not found');
    return $this
      ->xml()
      ->getElement(
          'The requested resource could not be found.',
          'error'
        );
  }

  protected function badRequest($message = '') {
    $this->statusCode(400);
    $this->statusMessage('Bad request');
    return $this
      ->xml()
      ->getElement(
          empty($message) ? 'This request is formally incorrect.' : $message,
          'error'
        );
  }

  protected function notImplemented() {
    $this->statusCode(501);
    $this->statusMessage('Not implemented');
    return $this
      ->xml()
      ->getElement(
          'This request method is not implemented.',
          'error'
        );
  }

  protected function forbidden() {
    $this->statusCode(403);
    $this->statusMessage('Forbidden');
    return $this
      ->xml()
      ->getElement(
          'You are not allowed to access this resource.',
          'error'
        );
  }

  protected function checkAuthorization() {
    $result = FALSE;
    if (defined('AUTH_USER') && defined('AUTH_KEY')) {
      if (isset($_GET['user']) && $_GET['user'] == AUTH_USER &&
          isset($_GET['key']) && md5($_GET['key']) == AUTH_KEY) {
        $result = TRUE;
      }
    }
    return $result;
  }

  public function xml($xml = NULL) {
    if ($xml !== NULL) {
      $this->xml = $xml;
    } elseif ($this->xml === NULL) {
      $this->xml = new Xml();
    }
    return $this->xml;
  }
}
