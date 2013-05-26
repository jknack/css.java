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
package com.github.jknack.css.expression;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.github.jknack.css.Expression;

public class ExpressionList extends AbstractExpression {

  private List<Expression> expressions = new LinkedList<Expression>();

  public ExpressionList add(final Expression expression) {
    expressions.add(expression);
    return this;
  }

  public List<Expression> expressions() {
    return Collections.unmodifiableList(expressions);
  }

  public Expression get(final int idx) {
    return expressions.get(idx);
  }

  public int size() {
    return expressions.size();
  }

  @Override
  public String toString() {
    return join(expressions, ", ");
  }
}
