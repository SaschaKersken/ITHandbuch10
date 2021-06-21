from sklearn.datasets import load_digits
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split

# Digits-Datenmenge laden
X, y = load_digits(return_X_y=True)

# 80% Trainings-, 20% Testdaten
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2
)

# KNN erzeugen und mit Trainingsdaten trainieren
ann = MLPClassifier(
    hidden_layer_sizes = (100,),
    activation = 'logistic',
    max_iter = 1000
)
ann.fit(X_train, y_train)

# Genauigkeit der Vorhersage für die Testdaten testen
accuracy = ann.score(X_test, y_test)
print(f"Genauigkeit: {accuracy}")
