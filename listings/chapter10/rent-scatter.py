import matplotlib.pyplot as plt
import csv
from sys import argv

# Farben für die verschiedenen Zimmerzahlen
color_mapping = {
        1: 'black',
        2: 'red',
        3: 'green',
        4: 'blue',
        5: 'magenta'
}

# Grafik für eine bestimmte Zimmeranzahl?
rooms = 0
if len(argv) > 1 and argv[1] in ['1', '2', '3', '4', '5']:
    rooms = int(argv[1])

# Daten importieren
rent_data = []
rent_file = open('size-rent.csv', 'r')
reader = csv.reader(rent_file)
next(reader, None)
for line in reader:
    rent_data.append(line)
rent_file.close()

# Die Grafik erstellen
plt.title("Size to rent ratio")
plt.xlabel("Size in square meters")
plt.ylabel("Rent in Euros")
for flat in rent_data:
    # Überspringen, falls "falsche" Zimmeranzahl
    if rooms and int(flat[1]) != rooms:
        continue
    plt.scatter(
        float(flat[0]),
        float(flat[2]),
        color = color_mapping[int(flat[1])],
        marker = f"${flat[1]}$"
    )
plt.show()
