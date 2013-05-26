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
