public class WithChocolateFrosting extends CookieDecorator {	
  public WithChocolateFrosting(Cookie cookie) {	
    super(cookie);	
  }	
	
  @Override	
  public double getPrice() {	
    return super.getPrice() + 0.5;	
  }	
	
  @Override	
  public String getIngredients() {	
    return super.getIngredients() + ", Schokoguss";	
  }	
}

