package com.example.maven_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {
  @Test
  public void testGetNetPrice() {
    Article article1 = new Article(119, 19);
    assertEquals(100, article1.getNetPrice());
  }

  @Test
  public void testGetDMGrossPrice() {
    Article article1 = new Article(100, 19);
    assertEquals(195.583, article1.getDMGrossPrice());
  }
}
