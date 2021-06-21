import matplotlib.pyplot as plt
import csv

# Daten importieren
rent_data = []
rent_file = open('size-rent-4.csv', 'r')
reader = csv.reader(rent_file)
for line in reader:
    rent_data.append([float(line[0]), float(line[1])])
rent_file.close()

# Die Grafik erstellen
plt.title("Size to rent ratio")
plt.xlabel("Size in square meters")
plt.ylabel("Rent in Euros")
plt.scatter(
    [row[0] for row in rent_data],
    [row[1] for row in rent_data]
)
plt.show()
