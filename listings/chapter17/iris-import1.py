import csv

irises = []
iris_file = open('iris.csv', 'r')
for line in csv.reader(iris_file):
    irises.append(line)
iris_file.close()
print(irises[0])
print(irises[50])
print(irises[100])

