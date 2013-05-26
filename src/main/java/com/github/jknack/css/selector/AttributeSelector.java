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

import static org.apache.commons.lang3.StringUtils.isNotEmpty;


public class AttributeSelector extends AbstractSelector {

  public enum ValueSelector {
    ANY(""),
    EQUALS("="),
    INCLUDES("~="),
    DASH_MATCH("|="),
    PREFIX_MATCH("^="),
    SUFFIX_MATCH("$="),
    SUBSTRING_MATCH("*=");

    private String operator;

    private ValueSelector(final String operator) {
      this.operator = operator;
    }

    public String operator() {
      return operator;
    }

    @Override
    public String toString() {
      return operator;
    }

    public static ValueSelector of(final String operator) {
      for (ValueSelector selector : values()) {
        if (selector.operator.equals(operator)) {
          return selector;
        }
      }
      return ANY;
    }
  }

  private String prefix;

  private String name;

  private ValueSelector operator;

  private String value;

  public AttributeSelector(final String prefix, final String name, final ValueSelector operator,
      final String value) {
    this.prefix = prefix;
    this.name = name;
    this.operator = operator;
    this.value = value;
  }

  @Override
  protected String nameInternal() {
    StringBuilder buffer = new StringBuilder();
    buffer.append('[');
    if (isNotEmpty(prefix)) {
      buffer.append(prefix).append("|");
    }
    buffer.append(name);
    if (operator != ValueSelector.ANY) {
      buffer.append(operator.operator);
      buffer.append(value);
    }
    buffer.append(']');
    return buffer.toString();
  }
}
