public class Book implements Article {
    private String author;
    private String title;
    private int articleNumber;
    private double price;

    public Book(String author, String title, int articleNumber,
        double price) {
        this.author = author;
        this.title = title;
        this.articleNumber = articleNumber;
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String toString() {
        return this.author + ": '" + this.title + "'";
    }

    // Methoden von Article

    @Override
    public int getArticleNumber() {
        return articleNumber;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getVAT() {
        // Bei Büchern 7%
        return price / 107 * 7;
    }
}

