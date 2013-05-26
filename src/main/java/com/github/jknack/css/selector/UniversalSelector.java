package com.github.jknack.css.selector;


public class UniversalSelector extends AbstractSelector {

  private String prefix;

  public UniversalSelector(final String prefix) {
    this.prefix = prefix;
  }

  public String prefix() {
    return prefix;
  }

  @Override
  protected String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    if (prefix != null) {
      buffer.append(prefix).append("|");
    }
    buffer.append("*");
    return buffer.toString();
  }
}
