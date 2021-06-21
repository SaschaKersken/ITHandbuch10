import matplotlib.pyplot as plt
import csv
import numpy as np
from sys import argv, exit

if len(argv) < 2:
    print(f"Verwendung: python3 {argv[0]} CSV-Datei [p]")
    exit(1)

# Daten importieren
filename = argv[1]
data = []
data_file = open(filename, 'r')
reader = csv.reader(data_file)
for line in reader:
    data.append([float(line[0]), float(line[1])])
data_file.close()

# Koeffizienten der Gerade berechnen
x = [row[0] for row in data]
y = [row[1] for row in data]
n = len(data)
theta0 = (
    (sum(y) * sum([xn**2 for xn in x]) - sum(x) * sum([xn * yn for xn, yn in zip(x, y)])) /
    (n * sum([xn**2 for xn in x]) - sum(x)**2)
)
theta1 = (
    (n * sum([xn * yn for xn, yn in zip(x, y)]) - sum(x) * sum(y)) /
    (n * sum([xn**2 for xn in x]) - sum(x)**2)
)

if len(argv) >= 3 and argv[2] == 'p':
    # Modus Vorhersage
    print(f"Gefundene Geradengleichung: y = {theta0} + {theta1}x")
    print("Geben Sie x-Werte für Prognosen ein; Q zum Beenden")
    while True:
        v_input = input("> ")
        if v_input == 'q' or v_input == 'Q':
            break
        try:
            value = float(v_input)
            print(f"Prognose: {theta0 + theta1 * value}")
        except ValueError:
            print("Bitte nur Zahlen!")
else:
    # Modus Zeichnung
    plt.title(f"y = {theta0} + {theta1}x")
    plt.xlabel("Input data")
    plt.ylabel("Output data")
    plt.scatter(x, y, color='blue')
    xcoords = np.linspace(min(x), max(x), 100)
    ycoords = theta0 + theta1 * xcoords
    plt.plot(xcoords, ycoords, color='red')
    plt.show()
