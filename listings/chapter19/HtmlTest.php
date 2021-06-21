<?php

require_once('/usr/lib/php/PHPUnit/Framework/TestCase.php');
require_once(__DIR__.'/Html/Tag.php');

class HtmlTagTest extends PHPUnit_Framework_TestCase {
  /**
  * @covers HtmlTag::__construct
  */
  public function testConstruct() {
    $tag = new HtmlTag('p', array('title' => 'Test'), 'Test text');
    $this->assertAttributeEquals('p', 'tagName', $tag);
    $this->assertAttributeEquals(
      array('title' => 'Test'), 'attributes', $tag);
    $this->assertAttributeEquals('Test text', 'content', $tag);
  }

  /**
  * @covers HtmlTag::addAttribute
  */
  public function testAddAttribute() {
    $tag = new HtmlTag('p');
    $tag->addAttribute('title', 'Test Title');
    $this->assertAttributeEquals(
      array('title' => 'Test Title'), 'attributes', $tag);
  }

  /**
  * @covers HtmlTag::__toString
  */
  public function testToString() {
    $tag = new HtmlTag('p', array('title' => 'Test'), 'Test text');
    $expected = '<p title="Test">
Test text</p>
';
    $this->assertEquals($expected, $tag->__toString());
  }
}

