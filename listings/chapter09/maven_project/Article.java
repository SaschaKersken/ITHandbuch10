public class Article {

    private double price;
    private int vat;

    public Article() {
        this.price = 0;
        this.vat = 19;
    }

    public Article(double price, int vat) {
        this.price = price;
        this.vat = vat;
    }

    public double getGrossPrice() {
        return this.price;
    }

    public double getVat() {
        return this.price / (100 + this.vat) * this.vat;
    }

    public double getNetPrice() {
        return this.price - this.getVat();
    }
}

