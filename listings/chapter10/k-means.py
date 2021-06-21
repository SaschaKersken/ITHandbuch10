import math
import csv
import numpy as np
from statistics import mean
from random import random

# Einzelner Punkt mit Originaldaten, skalierten Daten
# und optionaler Info.
class Point:

    def __init__(self, original, scaled, info = ''):
        self.original = original
        self.scaled = scaled
        self.info = info

    def __repr__(self):
        return f"{self.info} ({self.original})"


# Einzelnes Cluster
class Cluster:
    def __init__(self, dimensions):
        self.data = []
        self.centroid = [random() for _ in range(0, dimensions)]

    def add_point(self, point):
        self.data.append(point)

    def empty(self):
        self.data = []

    def __repr__(self):
        return f"({self.data})"


# Die Klasse für den k-Means-Algorithmus.
class KMeans:

    # k: Anzahl der Cluster
    # data: die zu verarbeitenden Daten
    # runs: maximale Anzahl Durchläufe (Standardwert 100)
    # with_info: letzte Spalte der Daten Info-String? (Stw. False)
    def __init__(self, k, data, runs=100, with_info=False):
        self.clusters = []
        self.data = []
        self.dimensions = len(data[0])
        if with_info:
            self.dimensions -= 1
        for _ in range(0, k):
            self.clusters.append(Cluster(self.dimensions))
        info = []
        if with_info:
            for point in data:
                info.append(point.pop())
        scaled_data = self.scale(data)
        for i, point in enumerate(data):
            scaled_point = scaled_data[i]
            p_info = ''
            if with_info:
                p_info = info[i]
            self.data.append(Point(point, scaled_point, info=p_info))
        self.runs = runs

    # Eine bestimmte Dimension (Spalte) aus einer Datenmenge auslesen
    def dimension(self, data, n):
        if len(data) == 0:
            return []
        if type(data[0]) is Point:
            return [row.scaled[n] for row in data]
        return [row[n] for row in data]

    # Feature-Skalierung
    def scale(self, data):
        new_data = []
        for n in range(0, self.dimensions):
            dim = np.array(self.dimension(data, n))
            dim = (dim - min(dim)) / (max(dim) - min(dim))
            new_data.append(dim)
        return np.array(new_data).transpose()
    
    # Euklidischer Abstand        
    def distance(self, point, centroid):
        sum_points = 0
        for p0, p1 in zip(point.scaled, centroid):
            sum_points += (p0 - p1) ** 2
        return math.sqrt(sum_points)

    # Einen Punkt ins jeweils passendste Cluster einsortieren
    def cluster(self, point):
        distances = []
        for cluster in self.clusters:
            distances.append(self.distance(point, cluster.centroid))
        self.clusters[distances.index(min(distances))].add_point(point)

    # Den Algorithmus ausführen
    def run(self):
        for i in range(0, self.runs):
            # Cluster leeren
            for cluster in self.clusters:
                cluster.empty()
            # Zuordnung zu den Clustern
            for point in self.data:
                self.cluster(point)
            converged = True
            # Zentroide verschieben
            for cluster in self.clusters:
                old_centroid = cluster.centroid[:]
                new_centroid = []
                for n in range(0, self.dimensions):      
                    dim = self.dimension(cluster.data, n)
                    if len(dim) == 0:
                        continue
                    new_centroid.append(mean(dim))
                if old_centroid != new_centroid:
                    converged = False
                    cluster.centroid = new_centroid
            # Bei Konvergenz beenden
            if converged:
                print(f"Konvergenz nach {i} Durchläufen.")
                return


# Hauptprogramm: k-Means mit den Irisdaten testen
if __name__ == '__main__':
    irises = []
    with open('iris.csv', 'r') as iris_file:
        for line in csv.reader(iris_file):
            irises.append([float(line[0]), float(line[1]), float(line[2]), float(line[3]), line[4]])
    kmeans = KMeans(3, irises, with_info=True)
    kmeans.run()
    for cluster in kmeans.clusters:
        cluster_info = {}
        for point in cluster.data:
            if point.info not in cluster_info:
                cluster_info[point.info] = 0
            cluster_info[point.info] += 1
        print(cluster_info)
