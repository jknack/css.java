package com.github.jknack.css.selector;

import static org.apache.commons.lang3.Validate.notEmpty;


public class IdSelector extends AbstractSelector {

  private String name;

  public IdSelector(final String name) {
    this.name = notEmpty(name, "The name can't be null/empty.");
  }

  @Override
  protected String nameInternal() {
    return name;
  }
}
