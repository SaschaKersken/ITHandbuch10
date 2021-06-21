import matplotlib.pyplot as plt
import csv
import numpy as np
from sklearn.linear_model import LinearRegression
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

# Lineare Regression
x = np.array([[row[0]] for row in data])
y = np.array([row[1] for row in data])
reg = LinearRegression().fit(x, y)
theta0 = reg.intercept_
theta1 = reg.coef_[0]

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
            prediction = reg.predict(np.array([[value]]))
            print(f"Prognose: {prediction}")
        except ValueError as e:
            print(e)
else:
    # Modus Zeichnung
    plt.title(f"y = {theta0} + {theta1}x")
    plt.xlabel("Input data")
    plt.ylabel("Output data")
    plt.scatter(x, y, color='blue')
    xcoords = np.linspace(min(x), max(x), 100)
    ycoords = reg.predict(xcoords)
    plt.plot(xcoords, ycoords, color='red')
    plt.show()
