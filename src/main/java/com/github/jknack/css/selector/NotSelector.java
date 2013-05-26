package com.github.jknack.css.selector;

import com.github.jknack.css.Combinator;
import com.github.jknack.css.Selector;

public class NotSelector extends AbstractSelector implements Combinator {
  private Selector selector;

  public NotSelector(final Selector selector) {
    this.selector = selector;
  }

  @Override
  public String nameInternal() {
    return ":not(" + selector.name() + ")";
  }

}
