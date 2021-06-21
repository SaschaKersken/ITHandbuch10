import math
import csv
import numpy as np
from random import shuffle
from sys import argv

filename = 'setosa-versicolor.csv'
species0 = 'Iris-setosa'
if len(argv) > 1 and argv[1] == '2':
    filename = 'versicolor-virginica.csv'
    species0 = 'Iris-versicolor'

# Daten einlesen
with open(filename, 'r') as data_file:
    data = []
    reader = csv.reader(data_file)
    for line in reader:
        data.append(line)

# Daten aufbereiten
shuffle(data)
original_x = [[float(element) for element in line[0:4]] for line in data]
# Feature-Skalierung
x = []
for i in range(0, len(original_x[0])):
    column = [row[i] for row in original_x]
    column_scaled = [(v - min(column)) / (max(column) - min(column))
        for v in column
    ]
    x.append(column_scaled)
x = np.array(x).transpose()
species = [row[4] for row in data]
y = np.array([0.0 if s == species0 else 1.0 for s in species])

# Split zwischen Trainings- und Testdaten
train_x = x[0:70]
train_y = y[0:70]
test_x = x[70:]
test_y = y[70:]

# Einzelne Vorhersage
def predict(x, theta):
    y_hat = theta[0]
    for i in range(0, len(x)):
        y_hat += theta[i + 1] * x[i]
    return 1.0 / (1.0 + math.exp(-y_hat))

# Modell trainieren
learning_rate = 0.1
theta = [0.0 for _ in range(0, len(train_x[0]) + 1)]
for epoch in range(0, 1000):
    for index, x in enumerate(train_x):
        y_hat = predict(x, theta)
        error = y[index] - y_hat
        theta[0] += learning_rate * error * y_hat * (1.0 - y_hat)
        for i in range(0, len(x)):
            theta[i + 1] += (learning_rate * error * y_hat *
                (1.0 - y_hat) * x[i]
            )

# Vorhersagen der Testdaten
correct = 0
for index, x in enumerate(test_x):
    y_hat = predict(x, theta)
    result = 1.0 if y_hat >= 0.5 else 0.0
    if result == test_y[index]:
        correct += 1
percent = correct / len(test_x) * 100.0
print(f"{correct} von {len(test_x)} ({percent}%) korrekt ermittelt.")
