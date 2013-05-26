/**
 * Copyright (c) 2012-2013 Edgar Espina
 *
 * This file is part of css.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.css.selector;

import com.github.jknack.css.Selector;

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
