package com.github.jknack.css.expression;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.github.jknack.css.Expression;

public class ExpressionList extends AbstractExpression {

  private List<Expression> expressions = new LinkedList<Expression>();

  public ExpressionList add(final Expression expression) {
    expressions.add(expression);
    return this;
  }

  public List<Expression> expressions() {
    return Collections.unmodifiableList(expressions);
  }

  public Expression get(final int idx) {
    return expressions.get(idx);
  }

  public int size() {
    return expressions.size();
  }

  @Override
  public String toString() {
    return join(expressions, ", ");
  }
}
