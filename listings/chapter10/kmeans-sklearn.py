from sklearn.datasets import load_iris
from sklearn.cluster import KMeans
import numpy as np

X, y = load_iris(return_X_y=True)
km = KMeans(n_clusters = 3)
km.fit(X)

groups = np.array(km.labels_).reshape(3, 50)
for group in groups:
    print(np.unique(group, return_counts=True))
