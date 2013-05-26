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

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Rule implements Iterable<Selector> {

  private final LinkedList<Selector> selectors = new LinkedList<Selector>();

  private final Map<String, Expression> properties = new LinkedHashMap<String, Expression>();

  public void add(final Selector selector) {
    selectors.add(selector);
  }

  public Selector first() {
    return selectors.getFirst();
  }

  public List<Selector> selectors() {
    return Collections.unmodifiableList(selectors);
  }

  @Override
  public Iterator<Selector> iterator() {
    return selectors().iterator();
  }

  public Rule property(final String name, final Expression expression) {
    properties.put(name, expression);
    return this;
  }

  public Expression property(final String name) {
    return properties.get(name);
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(join(selectors, ", "));
    buffer.append(" {\n");
    for (Entry<String, Expression> entry : properties.entrySet()) {
      buffer.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(";\n");
    }
    buffer.append("}\n");
    return buffer.toString();
  }

}
