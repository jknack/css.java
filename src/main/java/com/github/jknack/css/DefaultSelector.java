package com.github.jknack.css;

public class DefaultSelector {

  private String name;

  public DefaultSelector(final String name) {
    this.name = name;
  }

  public boolean matches(final String selector) {
    return name.equalsIgnoreCase(selector);
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
