abstract class CookieDecorator implements Cookie {	
  private final Cookie decoratedCookie;	
	
  public CookieDecorator(Cookie cookie) {	
    this.decoratedCookie = cookie;	
  }	
	
  @Override	
  public double getPrice() {	
    return decoratedCookie.getPrice();	
  }	
	
  @Override	
  public String getIngredients() {	
    return decoratedCookie.getIngredients();	
  }	
}

