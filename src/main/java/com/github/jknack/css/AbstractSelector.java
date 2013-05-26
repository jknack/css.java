package com.github.jknack.css;

public abstract class AbstractSelector implements Selector {

  private AbstractSelector next;

  @Override
  public Selector next() {
    return next;
  }

  public AbstractSelector next(final AbstractSelector next) {
    this.next = next;
    return this;
  }

  public AbstractSelector last() {
    AbstractSelector root = this;
    AbstractSelector last = this;
    while (root != null) {
      last = root;
      root = root.next;
    }
    return last;
  }

  @Override
  public <S extends Selector> S adapt(final Class<S> selectorType) {
    if (selectorType == getClass()) {
      return selectorType.cast(this);
    }
    return null;
  }

  @Override
  public final String name() {
    StringBuilder buffer = new StringBuilder();
    AbstractSelector root = this;
    while (root != null) {
      buffer.append(root.nameInternal());
      root = (AbstractSelector) root.next();
    }
    return buffer.toString();
  }

  protected abstract String nameInternal();

  @Override
  public String toString() {
    return name();
  }
}
