package com.github.jknack.css.expression;

import static org.apache.commons.lang3.Validate.notNull;

import com.github.jknack.css.Expression;

public abstract class AbstractExpression implements Expression {

  @Override
  public <E extends Expression> E adapt(final Class<E> expressionType) {
    notNull(expressionType, "The expressionType is required.");
    if (expressionType.isInstance(this)) {
      return expressionType.cast(this);
    }
    return null;
  }

}
