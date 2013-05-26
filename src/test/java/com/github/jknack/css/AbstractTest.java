package com.github.jknack.css;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AbstractTest {

  public StyleSheet parse(final String content) {
    return parse(content, false);
  }

  public StyleSheet parse(final String content, final boolean debug) {
    CSS.debug = debug;
    if (debug) {
      System.out.println(content + ": ");
    }
    return new CSS().parse(content);
  }

  public StyleSheet selector(final String content, final String expected) {
    return selector(content, expected, false);
  }

  public StyleSheet selector(final String content, final String expected, final boolean debug) {
    StyleSheet sheet = parse(content, debug);
    assertNotNull(sheet);
    assertNotNull(sheet.first());
    assertNotNull(sheet.first().first());
    assertEquals(expected, sheet.first().first().name());
    return sheet;
  }
}
