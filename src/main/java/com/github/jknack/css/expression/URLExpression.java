package com.github.jknack.css.expression;

public class URLExpression extends AbstractExpression {

  private String value;

  public URLExpression(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
