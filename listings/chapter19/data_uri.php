<?php

// Wenn außer dem Dateinamen keine Argumente vorhanden sind,
// Verwendungsinfo anzeigen und beenden
if (count($argv) < 2) {
  echo "Usage: php ".$argv[0]." file [mime type]\n";
  exit;
}
// MIME-Type: text/plain annehmen, optional aus 2. Argument lesen
$mimeType = 'text/plain';
if (isset($argv[2])) {
  $mimeType = $argv[2];
}
// Data-URI zusammensetzen
$result = 'data:';
$result .= $mimeType.';base64,';
$result .= base64_encode(file_get_contents($argv[1]));
// Ausgabe
echo $result."\n";

