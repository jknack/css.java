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
