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

import static org.apache.commons.lang3.Validate.notNull;

public class NumberExpression extends AbstractExpression {

  public enum Unit {
    NONE(""),
    PERCENTAGE("%"),
    EM("em"),
    EX("ex"),
    CH("ch"),
    REM("rem"),
    VW("vw"),
    VH("vh"),
    V_MIN("vmin"),
    V_MAX("vmax"),
    CM("cm"),
    MM("mm"),
    IN("in"),
    PX("px"),
    PT("pt"),
    PC("pc"),
    DEG("deg"),
    RAD("rad"),
    GRAD("grad"),
    TURN("turn"),
    SECONDS("s"),
    MILLIS("ms"),
    DPI("dpi"),
    DPCM("dpcm"),
    DPPX("dppx"),
    HZ("hz"),
    KHZ("khz");

    private String unit;

    private Unit(final String unit) {
      this.unit = notNull(unit, "The unit is required.");
    }

    public String unit() {
      return unit;
    }

    public static Unit of(final String sunit) {
      for (Unit unit : values()) {
        if (unit.unit.equalsIgnoreCase(sunit)) {
          return unit;
        }
      }
      return NONE;
    }

    @Override
    public String toString() {
      return unit;
    }
  }

  private Unit unit;

  private Number number;

  public NumberExpression(final String value) {
    String[] parts = split(value);
    //System.out.println("\t\t\t\t NumberExpression " + parts[0]);
    try {
      number = Integer.parseInt(parts[0]);
    } catch (NumberFormatException ex) {
      number = Double.parseDouble(parts[0]);
    }
    this.unit = Unit.of(parts[1]);
  }

  private static String[] split(final String value) {
    for (int i = 0; i < value.length(); i++) {
      char ch = value.charAt(i);
      //if (!Character.isDigit(ch) && ch != '.') {
      // lootsie fix
      if (!Character.isDigit(ch) && (ch != '.') && (ch != '-')) {
        return new String[]{value.substring(0, i), value.substring(i) };
      }
    }
    return new String[]{value, null };
  }

  public Number number() {
    return number;
  }

  public Unit unit() {
    return unit;
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(number);
    buffer.append(unit);
    return buffer.toString();
  }
}
