package com.github.jknack.css.expression;

public class HexColorExpression extends AbstractExpression {

  private String red;

  private String green;

  private String blue;

  public HexColorExpression(final String value) {
    if (value.length() == 4) {
      // #rgb
      red = value.substring(1, 2);
      green = value.substring(2, 3);
      blue = value.substring(3, 4);
    } else {
      // #rfb
      red = value.substring(1, 3);
      green = value.substring(3, 5);
      blue = value.substring(5, 7);
    }
  }

  public int red() {
    return toInt(red);
  }

  public int blue() {
    return toInt(blue);
  }

  public int green() {
    return toInt(green);
  }

  public String rgb() {
    return "#" + red + green + blue;
  }

  private static String toHex(final String value) {
    return (value.length() == 1 ? value + value : value);
  }

  private static int toInt(final String value) {
    return Integer.parseInt(toHex(value), 16);
  }
  @Override
  public String toString() {
    return rgb();
  }
}
