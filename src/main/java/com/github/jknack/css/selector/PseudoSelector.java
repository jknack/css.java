package com.github.jknack.css.selector;


public class PseudoSelector extends AbstractSelector {

  private String name;

  private String operator;

  public PseudoSelector(final String name, final String operator) {
    this.name = name;
    this.operator = operator;
  }

  @Override
  public String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(operator);
    buffer.append(name);
    return buffer.toString();
  }
}
