package com.github.jknack.css;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class AttributeSelector extends AbstractSelector {

  public enum ValueSelector {
    ANY(""),
    EQUALS("="),
    INCLUDES("~="),
    DASH_MATCH("|="),
    PREFIX_MATCH("^="),
    SUFFIX_MATCH("$="),
    SUBSTRING_MATCH("*=");

    private String operator;

    private ValueSelector(final String operator) {
      this.operator = operator;
    }

    public String operator() {
      return operator;
    }

    @Override
    public String toString() {
      return operator;
    }

    public static ValueSelector of(final String operator) {
      for (ValueSelector selector : values()) {
        if (selector.operator.equals(operator)) {
          return selector;
        }
      }
      return ANY;
    }
  }

  private String prefix;

  private String name;

  private ValueSelector operator;

  private String value;

  public AttributeSelector(final String prefix, final String name, final ValueSelector operator,
      final String value) {
    this.prefix = prefix;
    this.name = name;
    this.operator = operator;
    this.value = value;
  }

  @Override
  protected String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    buffer.append('[');
    if (isNotEmpty(prefix)) {
      buffer.append(prefix).append("|");
    }
    buffer.append(name);
    if (operator != ValueSelector.ANY) {
      buffer.append(operator.operator);
      buffer.append(value);
    }
    buffer.append(']');
    return buffer.toString();
  }
}
