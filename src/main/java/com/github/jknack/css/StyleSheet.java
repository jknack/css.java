package com.github.jknack.css;

import static org.apache.commons.lang3.Validate.notNull;

import java.nio.charset.Charset;
import java.util.Deque;
import java.util.LinkedList;

public class StyleSheet {

  private Charset charset;

  private final Deque<Rule> rules = new LinkedList<Rule>();

  public StyleSheet add(final Rule rule) {
    rules.add(rule);
    return this;
  }

  public StyleSheet charset(final Charset charset) {
    this.charset = notNull(charset, "The charset is required.");
    return this;
  }

  public Charset charset() {
    return charset;
  }

  public Rule first() {
    return rules.getFirst();
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    if (charset != null) {
      buffer.append("@charset \"").append(charset).append("\";");
    }
    for (Rule rule : rules) {
      buffer.append(rule).append("\n");
    }
    return buffer.toString();
  }
}
