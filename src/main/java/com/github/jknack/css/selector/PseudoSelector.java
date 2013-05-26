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


public class PseudoSelector extends AbstractSelector {

  private String name;

  private String operator;

  public PseudoSelector(final String name, final String operator) {
    this.name = name;
    this.operator = operator;
  }

  @Override
  public String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(operator);
    buffer.append(name);
    return buffer.toString();
  }
}
