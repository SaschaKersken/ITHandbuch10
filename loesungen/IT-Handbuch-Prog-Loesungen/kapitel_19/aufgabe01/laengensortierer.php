<?php
function lenSort($a, $b) {
  if (strlen($a) < strlen($b)) {
    return -1;
  }
  if (strlen($a) > strlen($b)) {
    return 1;
  }
  return 0;
}

// Beispielstring; kann beliebig geändert werden.
$string = "Dies ist ein recht langer Satz. Der hier ist kurz. Und hier ein mittellanger. Und ein kurzer. Dieser Satz ist der allerlängste und soll daher in der sortierten Liste ganz hinten stehen.";

$saetze = preg_split("([.?!]\s*)m", $string);
usort($saetze, "lenSort");
foreach ($saetze as $satz) {
  echo "$satz\n";
}
