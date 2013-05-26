/**
 * Copyright (c) 2012-2013 Edgar Espina
 *
 * This file is part of css.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
