import junit.framework.*;

public class ArticleTest extends TestCase {
   public ArticleTest(String name) {
      super(name);
   }

   public void testGetNetPrice() {
      Article article1 = new Article(119, 19);
      assertTrue(article1.getNetPrice() == 100);
   }

   public static void main(String[] args) {
      junit.swingui.TestRunner.run(ArticleTest.class);
   }
}

