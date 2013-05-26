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


public class TypeSelector extends AbstractSelector {

  private String prefix;

  private String name;

  public TypeSelector(final String prefix, final String name) {
    this.prefix = prefix;
    this.name = name;
  }

  public String prefix() {
    return prefix;
  }

  @Override
  public String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    if (prefix != null) {
      buffer.append(prefix).append("|");
    }
    buffer.append(name);
    return buffer.toString();
  }
}
