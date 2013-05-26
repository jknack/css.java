package com.github.jknack.css;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.css.expression.FunctionExpression;
import com.github.jknack.css.expression.HexColorExpression;
import com.github.jknack.css.expression.IdExpression;
import com.github.jknack.css.expression.NumberExpression;
import com.github.jknack.css.expression.NumberExpression.Unit;

public class ValueTest extends AbstractTest {
  /** The logging system. */
  private static final Logger log = LoggerFactory.getLogger(ValueTest.class);

  @Test
  public void simple() {
    Rule rule = rule("table { border-collapse: separate }");
    log.info("{}", rule);

    Expression expr = rule.property("border-collapse");
    assertNotNull(expr);
    assertEquals("separate", expr.toString());
  }

  @Test
  public void doubleQuoteWithSingleQoute() {
    Rule rule = rule(Fixtures.get("string1.css"));
    log.info("{}", rule);

    Expression expr = rule.property("content");
    assertNotNull(expr);
    assertEquals("\"this is a 'string'.\"", expr.toString());
  }

  @Test
  public void singleQuoteWithDoubleQoute() {
    Rule rule = rule(Fixtures.get("string2.css"));
    log.info("{}", rule);

    Expression expr = rule.property("content");
    assertNotNull(expr);
    assertEquals("'this is a \"string\".'", expr.toString());
  }

  @Test
  public void escapeDoubleQuote() {
    Rule rule = rule(Fixtures.get("string3.css"));
    log.info("{}", rule);

    Expression expr = rule.property("content");
    assertNotNull(expr);
    assertEquals("\"this is a \\\"string\\\".\"", expr.toString());
  }

  @Test
  public void escapeSingleQuote() {
    Rule rule = rule(Fixtures.get("string4.css"));
    log.info("{}", rule);

    Expression expr = rule.property("content");
    assertNotNull(expr);
    assertEquals("'this is a \\'string\\'.'", expr.toString());
  }

  @Test
  public void multilineString() {
    Rule rule = rule(Fixtures.get("string5.css"));
    log.info("{}", rule);

    Expression expr = rule.property("content");
    assertNotNull(expr);
    assertEquals("\"a not s\\\n" +
        "o very long title\"", expr.toString());
  }

  @Test
  public void integer() {
    Rule rule = rule("p {margin: 7}");
    log.info("{}", rule);

    NumberExpression number = rule.property("margin").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("7", number.toString());
    assertEquals(7, number.number());
    assertEquals(Unit.NONE, number.unit());
  }

  @Test
  public void percentage() {
    Rule rule = rule("p {width: 100%}");
    log.info("{}", rule);

    NumberExpression number = rule.property("width").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("100%", number.toString());
    assertEquals(100, number.number());
    assertEquals(Unit.PERCENTAGE, number.unit());
  }

  @Test
  public void emSize() {
    Rule rule = rule("h1 { line-height: 1.2em }");
    log.info("{}", rule);

    NumberExpression number = rule.property("line-height").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("1.2em", number.toString());
    assertEquals(1.2, number.number());
    assertEquals(Unit.EM, number.unit());
  }

  @Test
  public void exSize() {
    Rule rule = rule("h1 { line-height: 1.2ex }");
    log.info("{}", rule);

    NumberExpression number = rule.property("line-height").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("1.2ex", number.toString());
    assertEquals(1.2, number.number());
    assertEquals(Unit.EX, number.unit());
  }

  @Test
  public void chSize() {
    Rule rule = rule("h1 { line-height: 1.4ch }");
    log.info("{}", rule);

    NumberExpression number = rule.property("line-height").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("1.4ch", number.toString());
    assertEquals(1.4, number.number());
    assertEquals(Unit.CH, number.unit());
  }

  @Test
  public void remSize() {
    Rule rule = rule("h1 { line-height: 2rem }");
    log.info("{}", rule);

    NumberExpression number = rule.property("line-height").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("2rem", number.toString());
    assertEquals(2, number.number());
    assertEquals(Unit.REM, number.unit());
  }

  @Test
  public void vwSize() {
    Rule rule = rule("h1 { font-size: 8vw }");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("8vw", number.toString());
    assertEquals(8, number.number());
    assertEquals(Unit.VW, number.unit());
  }

  @Test
  public void vhSize() {
    Rule rule = rule("h1 { font-size: 8vh }");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("8vh", number.toString());
    assertEquals(8, number.number());
    assertEquals(Unit.VH, number.unit());
  }

  @Test
  public void vminSize() {
    Rule rule = rule("h1 { font-size: 8vmin }");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("8vmin", number.toString());
    assertEquals(8, number.number());
    assertEquals(Unit.V_MIN, number.unit());
  }

  @Test
  public void vmaxSize() {
    Rule rule = rule("h1 { font-size: 8vmax }");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("8vmax", number.toString());
    assertEquals(8, number.number());
    assertEquals(Unit.V_MAX, number.unit());
  }

  @Test
  public void inSize() {
    Rule rule = rule("h1 { margin: 0.5in }      /* inches  */");
    log.info("{}", rule);

    NumberExpression number = rule.property("margin").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("0.5in", number.toString());
    assertEquals(.5, number.number());
    assertEquals(Unit.IN, number.unit());
  }

  @Test
  public void cmSize() {
    Rule rule = rule("h2 { line-height: 3cm }   /* centimeters */");
    log.info("{}", rule);

    NumberExpression number = rule.property("line-height").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("3cm", number.toString());
    assertEquals(3, number.number());
    assertEquals(Unit.CM, number.unit());
  }

  @Test
  public void mmSize() {
    Rule rule = rule("h3 { word-spacing: 4mm }  /* millimeters */");
    log.info("{}", rule);

    NumberExpression number = rule.property("word-spacing").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("4mm", number.toString());
    assertEquals(4, number.number());
    assertEquals(Unit.MM, number.unit());
  }

  @Test
  public void ptSize() {
    Rule rule = rule("h4 { font-size: 12pt }    /* points */");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("12pt", number.toString());
    assertEquals(12, number.number());
    assertEquals(Unit.PT, number.unit());
  }

  @Test
  public void pcSize() {
    Rule rule = rule("h4 { font-size: 1pc }     /* picas */");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("1pc", number.toString());
    assertEquals(1, number.number());
    assertEquals(Unit.PC, number.unit());
  }

  @Test
  public void pxSize() {
    Rule rule = rule("p  { font-size: 12px }    /* px */");
    log.info("{}", rule);

    NumberExpression number = rule.property("font-size").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("12px", number.toString());
    assertEquals(12, number.number());
    assertEquals(Unit.PX, number.unit());
  }

  @Test
  public void deg() {
    Rule rule = rule(".myDiv { -sand-transform: 90deg;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sand-transform").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("90deg", number.toString());
    assertEquals(90, number.number());
    assertEquals(Unit.DEG, number.unit());
  }

  @Test
  public void grad() {
    Rule rule = rule(".myDiv { -sand-transform: 100grad;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sand-transform").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("100grad", number.toString());
    assertEquals(100, number.number());
    assertEquals(Unit.GRAD, number.unit());
  }

  @Test
  public void turn() {
    Rule rule = rule(".myDiv { -sand-transform: 0.25turn;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sand-transform").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("0.25turn", number.toString());
    assertEquals(.25, number.number());
    assertEquals(Unit.TURN, number.unit());
  }

  @Test
  public void rad() {
    Rule rule = rule(".myDiv { -sand-transform: 1.570796326794897rad;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sand-transform").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("1.570796326794897rad", number.toString());
    assertEquals(1.570796326794897, number.number());
    assertEquals(Unit.RAD, number.unit());
  }

  @Test
  public void seconds() {
    Rule rule = rule(".myDiv { -duration: 10s;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-duration").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("10s", number.toString());
    assertEquals(10, number.number());
    assertEquals(Unit.SECONDS, number.unit());
  }

  @Test
  public void millis() {
    Rule rule = rule(".myDiv { -duration: 100ms;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-duration").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("100ms", number.toString());
    assertEquals(100, number.number());
    assertEquals(Unit.MILLIS, number.unit());
  }

  @Test
  public void hertz() {
    Rule rule = rule(".myDiv { -sound: 200hz;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sound").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("200hz", number.toString());
    assertEquals(200, number.number());
    assertEquals(Unit.HZ, number.unit());
  }

  @Test
  public void khertz() {
    Rule rule = rule(".myDiv { -sound: 200khz;}");
    log.info("{}", rule);

    NumberExpression number = rule.property("-sound").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("200khz", number.toString());
    assertEquals(200, number.number());
    assertEquals(Unit.KHZ, number.unit());
  }

  @Test
  public void dpi() {
    Rule rule = rule(".media { min-resolution: 200dpi }");
    log.info("{}", rule);

    NumberExpression number = rule.property("min-resolution").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("200dpi", number.toString());
    assertEquals(200, number.number());
    assertEquals(Unit.DPI, number.unit());
  }

  @Test
  public void dpcm() {
    Rule rule = rule(".media { min-resolution: 200dpcm }");
    log.info("{}", rule);

    NumberExpression number = rule.property("min-resolution").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("200dpcm", number.toString());
    assertEquals(200, number.number());
    assertEquals(Unit.DPCM, number.unit());
  }

  @Test
  public void dppx() {
    Rule rule = rule(".media { min-resolution: 200dppx }");
    log.info("{}", rule);

    NumberExpression number = rule.property("min-resolution").adapt(NumberExpression.class);
    assertNotNull(number);
    assertEquals("200dppx", number.toString());
    assertEquals(200, number.number());
    assertEquals(Unit.DPPX, number.unit());
  }

  @Test
  public void colorKey() {
    Rule rule = rule("em { color: lime }");
    log.info("{}", rule);

    IdExpression value = rule.property("color").adapt(IdExpression.class);
    assertNotNull(value);
    assertEquals("lime", value.value());
  }

  @Test
  public void colorRgb() {
    Rule rule = rule("em { color: rgb(0,255,0) }");
    log.info("{}", rule);

    FunctionExpression value = rule.property("color").adapt(FunctionExpression.class);
    assertNotNull(value);
    assertEquals(3, value.paramenters().size());
    assertEquals(0, value.paramenters().get(0).adapt(NumberExpression.class).number());
    assertEquals(255, value.paramenters().get(1).adapt(NumberExpression.class).number());
    assertEquals(0, value.paramenters().get(2).adapt(NumberExpression.class).number());
  }

  @Test
  public void colorShortRGB() {
    Rule rule = rule("em { color: #f0e }");
    log.info("{}", rule);

    HexColorExpression value = rule.property("color").adapt(HexColorExpression.class);
    assertNotNull(value);
    assertEquals("#f0e", value.rgb());
    assertEquals(255, value.red());
    assertEquals(0, value.green());
    assertEquals(238, value.blue());
  }
}
