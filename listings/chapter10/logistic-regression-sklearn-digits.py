from sklearn.datasets import load_digits
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split

# Handschriftenerkennung laden
X, y = load_digits(return_X_y=True)

# In Trainings- und Testdaten unterteilen, dabei mischen
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2
)

# Logistische Regression mit Trainingsdaten trainieren
lr = LogisticRegression(max_iter=10000).fit(X_train, y_train)

# Genauigkeit der Vorhersage für die Testdaten testen
accuracy = lr.score(X_test, y_test)
print(f"Genauigkeit: {accuracy}")
