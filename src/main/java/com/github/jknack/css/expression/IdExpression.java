package com.github.jknack.css.expression;

public class IdExpression extends AbstractExpression {

  private String value;

  public IdExpression(final String value) {
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
