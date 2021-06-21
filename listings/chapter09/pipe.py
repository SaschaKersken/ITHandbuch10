import os, sys

reader, writer = os.pipe()
pid = os.fork()

if pid == 0:
    # Child schreibt nur; lesende Seite schließen
    os.close(reader)
    # Schreibende Seite tatsächlich zum Schreiben öffnen
    writer = os.fdopen(writer, 'w')
    for i in range(1, 101):
        print(f"    Child ist bei: {i}")
        writer.write(str(i) + "\n")
    sys.exit(0)
else:
    # Parent liest nur; schreibende Seite schließen
    os.close(writer)
    # Lesende Seite tatsächlich zum Lesen öffnen
    reader = os.fdopen(reader, 'r')
    # Endlosschleife zum Lesen
    while True:
        line = reader.readline()
        # Ende, wenn keine Daten mehr kommen
        if not line:
            sys.exit(0)
        i = int(line)
        print(f"Parent berechnet Quadrat von {i}: {i ** 2}")
