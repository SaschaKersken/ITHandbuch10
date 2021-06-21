var anfrage = null;
try {
  anfrage = new XMLHttpRequest();
} catch (err_ff) {
  try {
    anfrage = new ActiveXObject("Msxml2.XMLHTTP");
  } catch (err_ms1) {
    try {
      anfrage = new ActiveXObject("Microsoft.XMLHTTP");
    } catch (err_all) {
      anfrage = null;
    }
  }
}
if (anfrage == null) {
  alert("Sie verwenden einen nicht Ajax-fähigen Browser.");
}

