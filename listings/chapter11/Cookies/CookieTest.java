public class CookieTest {	
  public static void cookieInfo(Cookie cookie) {	
    System.out.println("Zutaten: " + cookie.getIngredients());	
    System.out.println("Preis:   " + cookie.getPrice() + "\n");	
  }	
	
  public static void main(String[] args) {	
    // Ersten Keks schrittweise aufbauen	
    Cookie cookie = new PlainCookie();	
    cookieInfo(cookie);	
    cookie = new WithChocolateFrosting(cookie);	
    cookieInfo(cookie);	
    cookie = new WithSprinkles(cookie, "Zucker");	
    cookieInfo(cookie);	
    // Zweiten Keks in einem Aufruf verschachelt aufbauen	
    Cookie cookie2 = new WithChocolateFrosting(	
      new WithSprinkles(	
        new PlainCookie(),	
        "Schoko"	
      )	
    );	
    cookieInfo(cookie2);	
  }	
}

