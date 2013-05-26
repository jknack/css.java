package com.github.jknack.css.expression;

import com.github.jknack.css.Expression;

public class FunctionExpression extends AbstractExpression {

  private String name;

  private ExpressionList paramenters;

  public FunctionExpression(final String name, final Expression expression) {
    this.name = name;
    if (expression instanceof ExpressionList) {
      this.paramenters = (ExpressionList) expression;
    } else {
      this.paramenters = new ExpressionList().add(expression);
    }
  }

  public ExpressionList paramenters() {
    return paramenters;
  }

  @Override
  public String toString() {
    return name + "(" + paramenters + ")";
  }
}
