class Book:
    # Konstruktor
    def __init__(self, author, title, year):
        self.author = author
        self.title = title
        self.year = year
    # String-Darstellung
    def __str__(self):
        return "{author}: '{title}' ({year})".format(
            author = self.author,
            title = self.title,
            year = self.year
        )

# Hauptprogramm
if __name__ == '__main__':
    # Instanzen von Book erzeugen
    book1 = Book(
        "Douglas Adams",
        "The Hitchhiker's Guide to the Galaxy",
        1979
    )
    book2 = Book("George R. R. Martin", "A Game of Thrones", 1996)
    # Ausgabe
    print(book1)
    print(book2)
