// Array für die Staaten vorbereiten
var staaten = new Array();

// Globale Variablen für Mauspositionen
var mausX = null;
var mausY = null;
// Wartezustand
var warten = true;

// loescheListe(): Bisherige Anzeige löschen
function loescheListe() {
  liste = document.getElementById("laenderliste");
  while (liste.hasChildNodes()) {
    liste.removeChild(liste.firstChild);
  }
}

//loescheFilter(): Eingabefeld leeren; alles ausgeben
function loescheFilter() {
  document.filter.eingabe.value = "";
  document.filter.eingabe.focus();
  filterListe();
}

// liesStaaten(): Ajax-Anfrage zu Beginn, um die Liste
// aller Staaten auszulesen
function liesStaaten() {
  // URL des PHP-Skripts für die Staatenliste
  var url = "laender_json.php";
  // Anfrage eröffnen: Methode GET, URL, asynchron
  anfrage.open("GET", url, true);
  anfrage.onreadystatechange = holeListe;
  anfrage.send(null);
}

// holeListe(): Liste der Staaten abholen und anzeigen
function holeListe() {
  // Antwort angekommen? 
  if (anfrage.readyState == 4) {
    // Antwort akzeptabel?
    if (anfrage.status == 200) {
      warten = false;
      staaten = JSON.parse(anfrage.responseText);
      // Anzeige löschen
      loescheListe();
      // Alle Staaten eintragen (leerer Filter!)
      filterListe();
    } else {
      alert("Staatenliste nicht verfuegbar!");
    }
  }
}

// zeigeHauptstadt(): Ajax-Anfrage zum Anzeigen der
// Hauptstadt
function zeigeHauptstadt(e) {
  if (warten) {
    return;
  }
  mausX = document.all ? window.event.offsetX + 10 :
          e.pageX + 10;
  mausY = document.all ? window.event.offsetY + 10 :
          e.pageY + 10;
  var id = e.target.toString();
  id = id.substring(id.indexOf("#") + 1);
  var url = "hauptstadt_json.php?id=" + id;
  anfrage.open("GET", url, true);
  anfrage.onreadystatechange = holeHauptstadt;
  anfrage.send(null);
}

// holeHauptstadt(): Hauptstadt abholen und anzeigen
function holeHauptstadt() {
  if (anfrage.readyState == 4 && anfrage.status == 200) {
    var hs = anfrage.responseText;
    var dieBox = document.getElementById("hauptstadt");
    dieBox.firstChild.nodeValue = hs;
    dieBox.style["visibility"] = "visible";
    dieBox.style["left"] = mausX + "px";
    dieBox.style["top"] = mausY + "px";
  }
}

// versteckeHauptstadt(): HS-Ebene verstecken
function versteckeHauptstadt() {
  var dieBox = document.getElementById("hauptstadt");
  dieBox.style["visibility"] = "hidden";
}

// filterListe(): Liste anhand der Texteingabe filtern
function filterListe() {
  // Bisherigen Text entfernen
  loescheListe();
  // Suchfilter lesen
  var fText = document.filter.eingabe.value;
  // Alle Staaten eintragen, auf die der Filter passt
  for (i in staaten) {
    var eintrag = staaten[i];
    var id = eintrag[0];
    var land = eintrag[1];
    if (land.toLowerCase().indexOf
        (fText.toLowerCase()) >= 0) {
      var aNode = document.createElement("a");
      var href = document.createAttribute("href");
      href.nodeValue = "#" + id;
      aNode.setAttributeNode(href);
      aNode.addEventListener(
        'mouseover',
        (evt) => zeigeHauptstadt(evt)
      );
      aNode.addEventListener(
        'mouseout',
        (evt) => versteckeHauptstadt(evt)
      );
      var tNode = document.createTextNode(land);
      var bNode = document.createElement("br");
      aNode.appendChild(tNode);
      liste.appendChild(aNode);
      liste.appendChild(bNode);
    }
  }
}

