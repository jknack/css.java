package com.github.jknack.css;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Rule implements Iterable<Selector> {

  private final LinkedList<Selector> selectors = new LinkedList<Selector>();

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

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(join(selectors, ", "));
    return buffer.toString();
  }

}
