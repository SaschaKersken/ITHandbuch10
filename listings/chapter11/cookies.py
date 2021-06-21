class Cookie:	
  def get_price(self):	
    pass	
	
  def get_ingredients(self):	
    pass	
	
class PlainCookie(Cookie):	
  def get_price(self):	
    return 0.8	
	
  def get_ingredients(self):	
    return 'Teig'	
	
class CookieDecorator(Cookie):	
  def __init__(self, cookie):	
    self.cookie = cookie	
	
  def get_price(self):	
    return self.cookie.get_price()	
	
  def get_ingredients(self):	
    return self.cookie.get_ingredients()	
	
class WithChocolateFrosting(CookieDecorator):	
  def __init__(self, cookie):	
    super().__init__(cookie)	
	
  def get_price(self):	
    return super().get_price() + 0.5	
	
  def get_ingredients(self):	
    return super().get_ingredients() + ", Schokoguss"	
	
class WithSprinkles(CookieDecorator):	
  def __init__(self, cookie, type):	
    super().__init__(cookie)	
    self.type = type	
	
  def get_price(self):	
    return super().get_price() + 0.3	
	
  def get_ingredients(self):	
    return super().get_ingredients() + ", " + self.type + "streusel"	
	
def cookie_info(cookie):	
  print("Preis:   " + str(cookie.get_price()))	
  print("Zutaten: " + cookie.get_ingredients() + "\n")	
	
if __name__ == '__main__':
    cookie1 = PlainCookie()	
    cookie_info(cookie1)	
    cookie1 = WithChocolateFrosting(cookie1)	
    cookie_info(cookie1)	
    cookie1 = WithSprinkles(cookie1, "Zucker")	
    cookie_info(cookie1)	
    cookie2 = WithChocolateFrosting(
        WithSprinkles(PlainCookie(), "Schoko")
    )	
    cookie_info(cookie2)

