package com.github.jknack.css.selector;

import static org.apache.commons.lang3.Validate.notNull;

import com.github.jknack.css.Combinator;
import com.github.jknack.css.Selector;

public class GeneralSiblingCombinator extends AbstractSelector implements Combinator {
  private Selector parent;

  private Selector child;

  public GeneralSiblingCombinator(final Selector parent, final Selector child) {
    this.parent = notNull(parent, "The parent can't be null.");
    this.child = notNull(child, "The child can't be null.");
  }

  @Override
  public String nameInternal() {
    return parent.name() + "~" + child.name();
  }

}
