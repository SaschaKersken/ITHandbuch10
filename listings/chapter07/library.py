class Author:
    """Represents a book's author

    Attributes:
      firstname (str): the author's first name
      lastname (str): the author's last name
    """
    def __init__(self, firstname, lastname):
        """Constructor for Author instances

        Args:
          firstname (str): the new author's first name
          lastname (str): the new author's last name
        """
        self.firstname = firstname
        self.lastname = lastname
    def __str__(self):
        """Represents an Author instance in string context
        """
        return "{} {}".format(self.firstname, self.lastname)

class Genre:
    """Represents a book's genre

    Genres can be nested using their parent attribute

    Attributes:
      title (str): the genre's name
      parent (Genre): the parent genre, or None for top-level genres
    """
    def __init__(self, title, parent = None):
        """Constructor for Genre instances

        Args:
          title (str): the new genre's name
          parent (Genre, optional): the parent genre, if any
        """
        self.title = title
        self.parent = parent
    def __str__(self):
        """Represents a Genre instance in string context
        """
        result = self.title
        if self.parent is not None:
            result = "{} > {}".format(self.parent.__str__(), result)
        return result

class Book:
    """Represents a book

    All attributes except for the title are optional and default to None

    Attributes:
      title (str): the book's title
      authors (Author or list of Author objects): the book's author(s)
      genre (Genre): the book's genre
      year (int): the book's publication year
    """
    def __init__(self, title, authors = None, genre = None, year = None):
        """Constructor for Book instances

        Args:
          title (str): the new book's title
          authors (Author or list of Author objects, optional):
            the new book's author(s)
          genre (Genre, optional): the new book's genre
          year (int, optional): the new book's publication year
        """
        self.title = title
        self.authors = authors
        self.genre = genre
        self.year = year
    def has_author(self, author):
        """Checks whether a specific author is among the book's authors

        Args:
          author (Author): the author to search for
        """
        result = False
        if hasattr(self.authors, "__getitem__") and author in self.authors:
            result = True
        elif self.authors is author:
            result = True
        return result
    def in_genre(self, genre):
        """Checks whether the book belongs to a specific genre/subgenre

        Args:
          genre (Genre): the genre to search for
        """
        result = False
        current_genre = self.genre
        while result == False and current_genre is not None:
            if current_genre is genre:
                result = True
            current_genre = current_genre.parent
        return result
    def __str__(self):
        """Represents a Book instance in string context
        """
        result = ""
        if self.authors is not None:
            if hasattr(self.authors, "__getitem__"):
                author_strings = [author.__str__() for author in self.authors]
                result += ", ".join(author_strings)
            else:
                result += self.authors.__str__()
            result += ": "
        result += "'{}'".format(self.title)
        if self.year is not None:
            result += ", {}".format(self.year)
        if self.genre is not None:
            result += " ({})".format(self.genre)
        return result

class Ebook(Book):
    """Represents an ebook as a specialization of a book

    See parent class for details; only additional features are described here

    Attributes:
      tech (str): the ebook's technology or file format,
        e.g. "PDF", "mobi", or "Kindle"
    """
    def __init__(self, title, authors = None, genre = None, year = None,
        tech = None):
        """Constructor for Ebook instances

        See parent constructor for details; only additional
        features are described here

        Args:
          tech (str, optional): the new ebook's technology or file format
        """
        super().__init__(title, authors, genre, year)
        self.tech = tech
    def __str__(self):
        """Represents an Ebook instance in string context
        """
        result = super().__str__()
        if self.tech is not None:
            result += " [{}]".format(self.tech)
        return result

class Library:
    """Represents a collection of books

    Provides a method to filter the list, e.g. by genre

    Attributes:
      books (list): the list of books/ebooks in the collection
    """
    def __init__(self, books = []):
        """Constructor for Library instances

        Args:
          books (list, optional): the initial list of books
        """
        self.books = books
    def add(self, book):
        """Adds a book to the collection

        Args:
          book (Book): the book to add
        """
        self.books.append(book)
    def remove(self, book):
        """Removes a book from the collection

        Args:
          book (Book): the book to remove
        """
        try:
            self.books.remove(book)
        except ValueError:
            pass
    def filter(self, field, value):
        """Returns a list of books that match a filter

        Args:
          field (str): field to filter for, one of "author", "genre", or "year"
          value (mixed): value to match the field against
        """
        if field == "author":
            return [book for book in self.books if book.has_author(value)]
        if field == "genre":
            return [book for book in self.books if book.in_genre(value)]
        if field == "year":
            return [book for book in self.books if book.year == value]
        raise ValueError("{} is not a valid field.".format(field))

if __name__ == "__main__":
    author_martin = Author("George R. R.", "Martin")
    author_tuttle = Author("Lisa", "Tuttle")
    author_weir = Author("Andy", "Weir")
    genre_fiction = Genre("Fiction")
    genre_scifi = Genre("Science Fiction", genre_fiction)
    genre_hardscifi = Genre("Hard Science Fiction", genre_scifi)
    genre_fantasy = Genre("Fantasy", genre_fiction)
    book_windhaven = Book("Windhaven", authors = [author_martin, author_tuttle],
      genre = genre_scifi, year = 1981)
    book_clash_of_kings = Book("A Clash of Kings", authors = author_martin,
      genre = genre_fantasy, year = 1998)
    ebook_silverbough = Ebook("The Silver Bough", authors = author_tuttle,
      genre = genre_fantasy, year = 2012, tech = "Kindle")
    book_martian = Book("The Martian", authors = author_weir,
      genre = genre_hardscifi, year = 2014)
    library = Library([book_windhaven, book_clash_of_kings, ebook_silverbough])
    library.add(book_martian)
    print("All books:")
    for book in library.books:
        print("- {}".format(book))
    print()
    print("Books in the Science Fiction genre:")
    for book in library.filter("genre", genre_scifi):
        print("- {}".format(book))
    print()
    print("Books co-written by Lisa Tuttle:")
    for book in library.filter("author", author_tuttle):
        print("- {}".format(book))

