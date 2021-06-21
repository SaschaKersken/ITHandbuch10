<?php

require_once('/usr/lib/php/PHPUnit/Framework/TestCase.php');
require_once(__DIR__.'/Database.php');

class DatabaseTest extends PHPUnit_Framework_TestCase {
   /**
   * @covers Database::setCon
   */
   public function testSetCon() {
    $db = new Database();
    $con = $this->getMock('GenericDb');
    $db->setCon($con);
    $this->assertAttributeSame($con, 'con', $db);
  }

  /**
  * @covers Database::getCon
  */
  public function testGetCon() {
     $this->markTestSkipped('Real database connection cannot be tested.');
  }

  /**
  * @covers Database::getCondition
  */
  public function testGetCondition() {
    $db = new Database();
    $con = $this->getMock('GenericDb', array('real_escape_string'));
    $con
      ->expects($this->atLeastOnce())
      ->method('real_escape_string')
      ->will(
          $this->onConsecutiveCalls(
            $this->returnValue('field1'),
            $this->returnValue(1),
            $this->returnValue(2),
            $this->returnValue('field2'),
            $this->returnValue('test%'),
            $this->returnValue('field3'),
            $this->returnValue('test')
          )
        );
    $db->setCon($con);
    $expected = " field1 IN ('1', '2') AND field2 LIKE 'test%' AND field3 = 'test'";
    $this->assertEquals(
      $expected,
      $db->getCondition(
         array('field1' => array(1, 2), 'field2' => 'test%', 'field3' => 'test')
      )
    );
  }

  /**
  * @covers Database::query
  */
  public function testQuery() {
    $db = new Database();
    $con = $this->getMock('GenericDb', array('real_escape_string',
      'query'));
    $result = $this->getMock('GenericDbResult');
    $con
      ->expects($this->atLeastOnce())
      ->method('real_escape_string')
      ->will(
          $this->onConsecutiveCalls(
            $this->returnValue('table'),
            $this->returnValue(1)
          )
        );
    $con
      ->expects($this->once())
      ->method('query')
      ->will($this->returnValue($result));
    $db->setCon($con);
    $this->assertEquals(
      $result,
      $db->query('SELECT test FROM %s WHERE field = %d', array('table', 1))
    );
  }

  /**
  * @covers Database::insertQuery
  */
  public function testInsertQuery() {
    $db = new Database();
    $con = $this->getMock('GenericDb', array('real_escape_string', 'query'));
    $con
      ->expects($this->atLeastOnce())
      ->method('real_escape_string')
      ->will(
          $this->onConsecutiveCalls(
            $this->returnValue('table'),
            $this->returnValue('name'),
            $this->returnValue('SampleName'),
            $this->returnValue('value'),
            $this->returnValue('SampleValue')
         )
       );
    $con->insert_id = 7;
    $db->setCon($con);
    $this->assertEquals(
      7,
      $db->insertQuery(
        'table',
        array('name' => 'SampleName', 'value' => 'SampleValue') 
      )
    );
  }

  /**
  * @covers Database::updateQuery
  */
  public function testUpdateQuery() {
    $db = new Database();
    $con = $this->getMock('GenericDb', array('real_escape_string', 'query'));
    $con
      ->expects($this->atLeastOnce())
      ->method('real_escape_string')
      ->will(
          $this->onConsecutiveCalls(
            $this->returnValue('table'),
            $this->returnValue('name'),
            $this->returnValue('SampleName'),
            $this->returnValue('value'),
            $this->returnValue('SampleValue'),
            $this->returnValue(1)
         )
       );
    $con->affected_rows = 1;
    $db->setCon($con);
    $this->assertEquals(
      1,
      $db->updateQuery(
        'table',
        array('name' => 'SampleName', 'value' => 'SampleValue'),
        'id = %d',
        array(1)
      )
    );
  }

  /**
  * @covers Database::updateQuery
  */
  public function testUpdateQueryExpectingException() {
    $db = new Database();
    try {
       $db->updateQuery('test', array());
       $this->fail('Expected InvalidArgumentException not thrown.');
    } catch (InvalidArgumentException $e) { }
  }

  /**
  * @covers Database::getAffectedRows
  */
  public function testGetAffectedRows() {
    $db = new Database();
    $con = $this->getMock('GenericDb');
    $con->affected_rows = 2;
    $db->setCon($con);
    $this->assertEquals(2, $db->getAffectedRows());
  }

  /**
  * @covers Database::getLastInsertId
  */
  public function testGetLastInsertId() {
    $db = new Database();
    $con = $this->getMock('GenericDb');
    $con->insert_id = 3;
    $db->setCon($con);
    $this->assertEquals(3, $db->getLastInsertId());
  }
}

