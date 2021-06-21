import csv

iris_file = open('iris-tabs.csv', 'r')
reader = csv.reader(iris_file, delimiter = "\t", quoting=csv.QUOTE_NONNUMERIC)
captions = next(reader, None)
irises = []
for line in reader:
    irises.append(line)
iris_file.close()
print(captions)
print(len(irises))
print(irises[0])
print(irises[50])
print(irises[100])
