// Das Paket http importieren
var http = require('http');

// Den Server definieren
var server = http.createServer(
  (request, response) => {
    // Steht in der Anfrage-URL etwas hinter dem "/"?
    // Dann den / entfernen und in name speichern
    let name = request.url.length > 1 ? request.url.substr(1) : '';
    // Ausgabe generieren
    let output = 'Hallo Welt!';
    if (name) {
      output += ' Hallo ' + name + '!';
    }
    // Header (hier nur Status) ausgeben
    response.writeHead(200);
    // Body ausgeben
    response.end(output);
  }
);

// Der Server soll an Port 3001 lauschen.
server.listen(3001);

