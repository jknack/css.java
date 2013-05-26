package com.github.jknack.css;

public class TypeSelector extends AbstractSelector {

  private String prefix;

  private String name;

  public TypeSelector(final String prefix, final String name) {
    this.prefix = prefix;
    this.name = name;
  }

  public String prefix() {
    return prefix;
  }

  @Override
  public String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    if (prefix != null) {
      buffer.append(prefix).append("|");
    }
    buffer.append(name);
    return buffer.toString();
  }
}
