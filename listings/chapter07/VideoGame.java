public class VideoGame implements Article {
    private String title;
    private String system;
    private int articleNumber;
    private double price;

    public VideoGame(String title, String system, int articleNumber,
        double price) {
        this.title = title;
        this.system = system;
        this.articleNumber = articleNumber;
        this.price = price;
    }

    public String getSystem() {
        return this.system;
    }

    public String getTitle() {
        return this.title;
    }

    public String toString() {
        return this.title + " (" + this.system + ")";
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
        // Bei Videospielen 19%
        return price / 119 * 19;
    }
}
