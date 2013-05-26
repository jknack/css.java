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
package com.github.jknack.css;

import static org.apache.commons.lang3.Validate.notNull;

import java.nio.charset.Charset;
import java.util.Deque;
import java.util.LinkedList;

public class StyleSheet {

  private Charset charset;

  private final Deque<Rule> rules = new LinkedList<Rule>();

  public StyleSheet add(final Rule rule) {
    rules.add(rule);
    return this;
  }

  public StyleSheet charset(final Charset charset) {
    this.charset = notNull(charset, "The charset is required.");
    return this;
  }

  public Charset charset() {
    return charset;
  }

  public Rule first() {
    return rules.getFirst();
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    if (charset != null) {
      buffer.append("@charset \"").append(charset).append("\";");
    }
    for (Rule rule : rules) {
      buffer.append(rule).append("\n");
    }
    return buffer.toString();
  }
}
