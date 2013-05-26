package com.github.jknack.css.expression;

public class StringExpression  extends AbstractExpression {

  private String value;

  public StringExpression(final String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
