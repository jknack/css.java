package com.github.jknack.css;

import static org.apache.commons.lang3.Validate.notNull;

public class DescendantCombinator extends AbstractSelector implements Combinator {

  private Selector ancestor;
  private Selector child;

  public DescendantCombinator(final Selector ancestor, final Selector child) {
    this.ancestor = notNull(ancestor, "The ancestor can't be null.");
    this.child = notNull(child, "The right can't be null.");
  }

  @Override
  public String nameInternal() {
    return ancestor.name() + " " + child.name();
  }

}
