package com.github.jknack.css;

import java.util.Deque;
import java.util.LinkedList;

public class StyleSheet {

  private final Deque<Rule> rules = new LinkedList<Rule>();

  public void add(final Rule rule) {
    rules.add(rule);
  }

  public Rule first() {
    return rules.getFirst();
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    for (Rule rule : rules) {
      buffer.append(rule).append("\n");
    }
    return buffer.toString();
  }
}
