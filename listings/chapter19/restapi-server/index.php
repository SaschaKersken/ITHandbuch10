<?php

require_once(__DIR__.'/config.inc.php');
require_once(__DIR__.'/Autoloader.php');

spl_autoload_register(array('Autoloader', 'autoload'));

$application = new Application();
$application->run();
