public class WithSprinkles extends CookieDecorator {	
  private final String type;	
	
  public WithSprinkles(Cookie cookie, String type) {	
    super(cookie);	
    this.type = type;	
  }	
	
  @Override	
  public double getPrice() {	
    return super.getPrice() + 0.3;	
  }	
	
  @Override	
  public String getIngredients() {	
    return super.getIngredients() + ", " + type + "streusel";	
  }	
}

