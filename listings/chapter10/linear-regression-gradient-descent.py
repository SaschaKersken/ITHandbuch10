import matplotlib.pyplot as plt
import csv
import numpy as np
from random import random
from sys import argv, exit

if len(argv) < 2:
    print(f"Verwendung: python3 {argv[0]} CSV-Datei [p]")
    exit(1)

# Daten importieren
filename = argv[1]
data = []
with open(filename, 'r') as data_file:
    reader = csv.reader(data_file)
    for line in reader:
        data.append([float(line[0]), float(line[1])])

# x- und y-Werte in NumPy-Array speichern.
# min_x und max_x speichern Minimum und Maximum der Originalwerte,
# damit bei der Prognose Eingabewerte skaliert werden können.
x = np.array([row[0] for row in data])
min_x = min(x)
max_x = max(x)
y = np.array([row[1] for row in data])
n = len(data)

# Feature-Skalierung
x = (x - min_x) / (max_x - min_x)

# Lernrate
learning_rate = 0.2

# Beispielwerte für die grafische Darstellung
theta0s = []
theta1s = []

# Bei theta_j = 0 beginnen
theta0 = 0
theta1 = 0

# Gradientenabstieg
for step in range(0, 1000):
    y_pred = theta0 + x * theta1
    theta1_new = theta1 - learning_rate * (1 / n) * sum(x * (y_pred - y))
    theta0_new = theta0 - learning_rate * (1 / n) * sum(y_pred - y)
    theta0, theta1 = theta0_new, theta1_new
    if step % 50 == 0:
        theta0s.append(theta0)
        theta1s.append(theta1)
 
if len(argv) >= 3 and argv[2] == 'p':
    # Modus Vorhersage
    print("Geben Sie x-Werte für Prognosen ein; Q zum Beenden")
    while True:
        v_input = input("> ")
        if v_input == 'q' or v_input == 'Q':
            break
        try:
            value = float(v_input)
            value = (value - min_x) / (max_x - min_x)
            print(f"Prognose: {theta0 + theta1 * value}")
        except ValueError:
            print("Bitte nur Zahlen!")
else:
    # Modus Zeichnung
    plt.xlabel("Input data")
    plt.ylabel("Output data")
    plt.scatter(x, y, color='blue')
    xcoords = np.linspace(min(x), max(x), 100)
    ycoords = theta0 + theta1 * xcoords
    for i, old_theta0 in enumerate(theta0s):
        old_theta1 = theta1s[i]
        ycoords = old_theta0 + old_theta1 * xcoords
        plt.plot(xcoords, ycoords, '--', color=(i / len(theta0s), 0, 0))
    plt.plot(xcoords, ycoords, color='red')
    plt.show()
