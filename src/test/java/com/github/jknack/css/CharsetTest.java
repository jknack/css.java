package com.github.jknack.css;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.charset.Charset;

import org.junit.Test;

public class CharsetTest extends AbstractTest {

  @Test
  public void charSet() {
    StyleSheet sheet = parse("@charset \"ISO-8859-1\";");
    assertNotNull(sheet);
    assertEquals(Charset.forName("ISO-8859-1"), sheet.charset());
  }
}
