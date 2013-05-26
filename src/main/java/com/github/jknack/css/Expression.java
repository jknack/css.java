package com.github.jknack.css;

public interface Expression {

  <E extends Expression> E adapt(Class<E> expressionType);
}