package com.github.jknack.css;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class Fixtures {

  public static String get(final String path) {
    InputStream is = null;
    try {
      is = Fixtures.class.getResourceAsStream("/" + path);
      return IOUtils.toString(is);
    } catch (IOException ex) {
      throw new IllegalArgumentException(path, ex);
    } finally {
      IOUtils.closeQuietly(is);
    }
  }

  public static void main(final String[] args) {
    System.out.println(Integer.toOctalString(1114111));
    System.out.println(Integer.toHexString(1114111));
  }
}
