public class ArticleTest {
    public static void printArticle(Article article) {
        System.out.println("- " + article); // -> toString()
        System.out.printf(
            " Artikelnummer %d, %.2f EUR\n",
            article.getArticleNumber(),
            article.getPrice()
        );
    }

    public static void main(String args[]) {
        Article[] shoppingList = {
            new Book(
                "Christian Ullenboom",
                "Java ist auch eine Insel",
                7737,
                49.9
            ),
            new Book(
                "Johannes Ernesti, Peter Kaiser",
                "Python 3",
                7926,
                44.9
            ),
            new VideoGame(
                "Pokémon Schild",
                "Nintendo Switch",
                7777,
                45.99
            ),
            new VideoGame(
                "The Legend of Zelda - Breath of the Wild",
                "Nintendo Switch",
                5678,
                53.99
            )
        };
        System.out.println("Ihre Bestellung:");
        double total = 0.0; // Summe der Preise
        double vat = 0.0;   // Summe der Mwst.
        for (int i = 0; i < shoppingList.length; i++) {
            total += shoppingList[i].getPrice();
            vat += shoppingList[i].getVAT();
            printArticle(shoppingList[i]);
        }
        System.out.println("----------------------");
        System.out.printf("Gesamtpreis: %6.2f\n", total);
        System.out.printf("inkl. Mwst.: %6.2f\n", vat);
    }
} 
