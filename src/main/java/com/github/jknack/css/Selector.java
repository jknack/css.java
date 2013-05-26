package com.github.jknack.css;

public interface Selector {

  String name();

  Selector next();

  <S extends Selector> S adapt(Class<S> selectorType);
}
