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

import com.github.jknack.css.Expression;

public class FunctionExpression extends AbstractExpression {

  private String name;

  private ExpressionList paramenters;

  public FunctionExpression(final String name, final Expression expression) {
    this.name = name;
    if (expression instanceof ExpressionList) {
      this.paramenters = (ExpressionList) expression;
    } else {
      this.paramenters = new ExpressionList().add(expression);
    }
  }

  public String getName() {
	  return name;
  }
  
  public ExpressionList paramenters() {
    return paramenters;
  }

  @Override
  public String toString() {
    return name + "(" + paramenters + ")";
  }
}
