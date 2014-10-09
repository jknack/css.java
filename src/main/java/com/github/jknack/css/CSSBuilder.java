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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.github.jknack.css.expression.ExpressionList;
import com.github.jknack.css.expression.FunctionExpression;
import com.github.jknack.css.expression.HexColorExpression;
import com.github.jknack.css.expression.IdExpression;
import com.github.jknack.css.expression.NumberExpression;
import com.github.jknack.css.expression.StringExpression;
import com.github.jknack.css.expression.URLExpression;
import com.github.jknack.css.internal.CssBaseVisitor;
import com.github.jknack.css.internal.CssParser;
import com.github.jknack.css.internal.CssParser.AttributeContext;
import com.github.jknack.css.internal.CssParser.BlockContext;
import com.github.jknack.css.internal.CssParser.CharSetContext;
import com.github.jknack.css.internal.CssParser.ClassSelectorContext;
import com.github.jknack.css.internal.CssParser.CombinatorContext;
import com.github.jknack.css.internal.CssParser.DeclarationContext;
import com.github.jknack.css.internal.CssParser.ExpressionContext;
import com.github.jknack.css.internal.CssParser.FunctionContext;
import com.github.jknack.css.internal.CssParser.HexColorExprContext;
import com.github.jknack.css.internal.CssParser.IdExprContext;
import com.github.jknack.css.internal.CssParser.IdSelectorContext;
import com.github.jknack.css.internal.CssParser.IdentNamespaceTypeSelectorContext;
import com.github.jknack.css.internal.CssParser.IdentNamespaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.NegationContext;
import com.github.jknack.css.internal.CssParser.NonamespaceTypeSelectorContext;
import com.github.jknack.css.internal.CssParser.NonamespaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.NumberContext;
import com.github.jknack.css.internal.CssParser.NumberExprContext;
import com.github.jknack.css.internal.CssParser.PseudoContext;
import com.github.jknack.css.internal.CssParser.RuleSetContext;
import com.github.jknack.css.internal.CssParser.SelectorContext;
import com.github.jknack.css.internal.CssParser.SelectorGroupContext;
import com.github.jknack.css.internal.CssParser.SelectorTypeContext;
import com.github.jknack.css.internal.CssParser.StatementContext;
import com.github.jknack.css.internal.CssParser.StringExprContext;
import com.github.jknack.css.internal.CssParser.StyleSheetContext;
import com.github.jknack.css.internal.CssParser.TermContext;
import com.github.jknack.css.internal.CssParser.TypeSelectorContext;
import com.github.jknack.css.internal.CssParser.UniversalNamepaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.UniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.UnivesalNamespaceTypeSelectorContext;
import com.github.jknack.css.internal.CssParser.UrlExprContext;
import com.github.jknack.css.selector.AbstractSelector;
import com.github.jknack.css.selector.AttributeSelector;
import com.github.jknack.css.selector.AttributeSelector.ValueSelector;
import com.github.jknack.css.selector.ChildCombinator;
import com.github.jknack.css.selector.ClassSelector;
import com.github.jknack.css.selector.DescendantCombinator;
import com.github.jknack.css.selector.GeneralSiblingCombinator;
import com.github.jknack.css.selector.IdSelector;
import com.github.jknack.css.selector.NotSelector;
import com.github.jknack.css.selector.PseudoSelector;
import com.github.jknack.css.selector.SiblingCombinator;
import com.github.jknack.css.selector.TypeSelector;
import com.github.jknack.css.selector.UniversalSelector;

class CSSBuilder extends CssBaseVisitor<Object> {

//	public static boolean debug = true;
//	public static int debugLevel = 2;
//	public static int debugLevel = 1;
	public static int debugLevel = 0;
	
  @Override
  public Object visit(final ParseTree tree) {
    return super.visit(tree);
  }

  @Override
  public Charset visitCharSet(final CharSetContext ctx) {
	if (debugLevel > 0) {
		System.out.println("visitCharSet");
	}
	
    String charSet = text(ctx.STRING());
    charSet = charSet.substring(1, charSet.length() -1);
    return Charset.forName(charSet);
  }

//  @Override
//  public Object visitStyleSheet(CssParser.StyleSheetContext ctx) { 
//	if (debugLevel > 0) {
//		System.out.println("visitStyleSheet");
//	}	  
//	return visitChildren(ctx);
//  }  
  
  @Override
  public Object visitStyleSheet(final StyleSheetContext ctx) {
	if (debugLevel > 0) {
		System.out.println("visitStyleSheet");
	}
		
    StyleSheet sheet = new StyleSheet();
    // charset
    if (ctx.charSet() != null) {
      sheet.charset(visitCharSet(ctx.charSet()));
    }
    
    for (StatementContext statementCtx : ctx.statement()) {
    	if (debugLevel > 0) System.out.println("visitStyleSheet: statement: " + statementCtx.getText());
    	
		  Object candidate = visitChildren(statementCtx);
		  if (candidate == null) {
			  if (debugLevel > 0) System.out.println("visitStyleSheet: skipping null object");
		  } else {      
		      if (candidate instanceof Rule) {
		    	  if (debugLevel > 0) System.out.println("visitStyleSheet: adding Rule object: " + ((Rule) candidate).first().name());
		    	  sheet.add((Rule) candidate);
		      } else if (candidate instanceof KeyframeBlock){
		    	  if (debugLevel > 0) System.out.println("visitStyleSheet: adding KeyframeBlock object: " + ((KeyframeBlock) candidate).name);
		    	  sheet.add((KeyframeBlock) candidate);			  
		      } else {
		    	  if (debugLevel > 0) System.out.println("visitStyleSheet: skipping object type: " + candidate.toString());
			  }
		  }
		  
		  //candidate = visitKeyframes((CssParser.KeyframesContext) statementCtx);
//		  if (candidate == null) {
//			  System.out.println("visitStyleSheet: skipping null object");
//		  } else {
//			  System.out.println("visitStyleSheet: skipping object type: " + candidate.toString());
//		  }
    }
    return sheet;
  }

  // Lootsie
  @Override
  public Object visitKeyframes(CssParser.KeyframesContext ctx) { 
	if (debugLevel > 0) {
		System.out.println("visitKeyframes " + ctx.IDENT().getText());
		//ctx.getText() 
	}
	  
	KeyframeBlock keyframeBlock = new KeyframeBlock();	
	keyframeBlock.name = ctx.IDENT().getText();
	
	visitKeyframeblock(keyframeBlock, ctx.keyframeblock());
	
	return keyframeBlock;
//	return null;
//	  return visitChildren(ctx); 
  }
  
  // Lootsie
  // kekyframeblock child of keyframes
//  @Override
//  public Object visitKeyframeblock(CssParser.KeyframeblockContext ctx) {
  public Object visitKeyframeblock(final KeyframeBlock keyframeBlock, CssParser.KeyframeblockContext ctx) {  
	if (debugLevel > 0) System.out.println("\t visitKeyframeblock " + ctx.getText());
	
	List<NumberContext> numlist = ctx.number();
	List<BlockContext> blocklist = ctx.block();
	for (int i=0; (i<numlist.size()) && (i<blocklist.size());i++) {
		NumberContext num = numlist.get(i);
		BlockContext block = blocklist.get(i);
		
		Keyframe keyframe = new Keyframe();
		NumberExpression numex = new NumberExpression(num.getText());
		keyframe.percentage = numex.number().intValue(); 
				
		if (debugLevel > 0) System.out.println("\t visitKeyframeblock number["+i+"]:" + num.getText());		
		if (debugLevel > 0) System.out.println("\t visitKeyframeblock block["+i+"]" + block.getText());		
		
		//visitChildren(block);
		visitBlock(keyframe, block);		
		
		if (debugLevel > 1) System.out.println("\t visitKeyframeblock keyframe: " + keyframe.toString());
		keyframeBlock.add(keyframe);
	}
	
	if (debugLevel > 1) System.out.println("\t visitKeyframeblock keyframeBlock: " + keyframeBlock.toString());
	
	
//	 return visitChildren(ctx);
	 return null;
  }  
  
  @Override
  public Object visitRuleSet(final RuleSetContext ctx) {
	if (debugLevel > 0) {
		System.out.println("visitRuleSet");
	}
		
    Rule rule = new Rule();
    List<Selector> selectors = visitSelectorGroup(ctx.selectorGroup());
    for (Selector selector : selectors) {
      rule.add(selector);
    }
    visitBlock(rule, ctx.block());
    
	if (debugLevel > 0) {
		System.out.println("\t visitRuleSet finished: " + rule.first().toString());
	}    
    return rule;
  }

  // Lootsie
  @Override 
  public Object visitBlock(CssParser.BlockContext ctx) { 
	  if (debugLevel > 0) System.out.println("\t visitBlock " + ctx.getText());
		
	  return visitChildren(ctx); 
  }  
  
  // for block in subtree of keyframe
  private void visitBlock(final Keyframe keyframe, final BlockContext block) {
	if (debugLevel > 0) {
		System.out.println("\t visitBlock (Keyframe) ");
	}
	  
    for (DeclarationContext declarationCtx : block.declaration()) {
      String name = text(declarationCtx.IDENT());
	  	if (debugLevel > 0) System.out.println("\t visitBlock: (Keyframe) " + name);
	  	
//      Expression expression = visitExpression(declarationCtx.expression());
	  	List<Expression> expressionList = new ArrayList<Expression>();
	  	for (ExpressionContext expressionCtx : declarationCtx.expression()) {
	  		Expression expression = (Expression) visitExpression(expressionCtx);
	  		// I should have a list of expressions per name?
	  		expressionList.add(expression);
	  	}

	  	keyframe.addProperty(name, expressionList);
    }
  }  
  
  // for Block in subtree of rule
  private void visitBlock(final Rule rule, final BlockContext block) {
	if (debugLevel > 0) System.out.println("\t visitBlock (Rule)");
	  
    for (DeclarationContext declarationCtx : block.declaration()) {
      String name = text(declarationCtx.IDENT());
	  	if (debugLevel > 0) System.out.println("\t visitBlock: (Rule) " + name);

	  	
//	  	Expression expression = visitExpression(declarationCtx.expression());
	  	List<Expression> expressionList = new ArrayList<Expression>();
	  	for (ExpressionContext expressionCtx : declarationCtx.expression()) {
	  		Expression expression = (Expression) visitExpression(expressionCtx);
	  		// I should have a list of expressions per name?
	  		expressionList.add(expression);
	  	}
	  	
	  	rule.property(name, expressionList);
	  	
    }
  }

  @Override 
  public Object visitDeclaration(CssParser.DeclarationContext ctx) { 
		if (debugLevel > 0) {
			System.out.println("\t\t visitDeclaration ident: " + ctx.IDENT().getText());
//			System.out.println("\t\t visitDeclaration expression: " + ctx.expression().getText());
		}
	  
	  return visitChildren(ctx); 
  }
  
  @Override
  public Expression visitExpression(final ExpressionContext ctx) {
	if (debugLevel > 0) {
		System.out.println("\t\t visitExpression " + ctx.getText());
	}

		
    Expression expr = (Expression) visit(ctx.left);
    if (ctx.right != null && ctx.right.size() > 0) {
      ExpressionList list = new ExpressionList();
      list.add(expr);
      for (TermContext right : ctx.right) {
        list.add((Expression) visit(right));
      }
      expr = list;
    }
    return expr;
  }

  @Override
  public Object visitNumberExpr(final NumberExprContext ctx) {	 
//	if (debugLevel > 0) System.out.println("\t\t\t visitNumberExpr " + ctx.getText());
    return new NumberExpression(ctx.getText());
  }

  @Override
  public Object visitStringExpr(final StringExprContext ctx) {
    return new StringExpression(ctx.getText());
  }

  @Override
  public Object visitIdExpr(final IdExprContext ctx) {
    return new IdExpression(ctx.getText());
  }

  @Override
  public Object visitUrlExpr(final UrlExprContext ctx) {
    return new URLExpression(ctx.getText());
  }

  @Override
  public Object visitHexColorExpr(final HexColorExprContext ctx) {
    return new HexColorExpression(ctx.getText());
  }

  @Override
  public Object visitFunction(final FunctionContext ctx) {
    return new FunctionExpression(text(ctx.IDENT()), (Expression) visit(ctx.expression()));
  }

  @Override
  public List<Selector> visitSelectorGroup(final SelectorGroupContext ctx) {
    List<Selector> result = new ArrayList<Selector>();
    for (SelectorContext selectorCtx : ctx.selector()) {
      result.add(visitSelector(selectorCtx));
    }
    return result;
  }

  @Override
  public Selector visitSelector(final SelectorContext ctx) {
    AbstractSelector root = buildSelector(ctx.selectorType());
    for (CombinatorContext combinatorCxt : ctx.combinator()) {
      root = visitCombinator(root, combinatorCxt);
    }
    return root;
  }

  private AbstractSelector visitCombinator(final Selector parent, final CombinatorContext ctx) {
    String scombinator = ctx.COMBINATOR.getText();
    AbstractSelector selector = buildSelector(ctx.selectorType());
    final AbstractSelector combinator;
    if (scombinator.equals(">")) {
      combinator = new ChildCombinator(parent, selector);
    } else if (scombinator.equals("+")) {
      combinator = new SiblingCombinator(parent, selector);
    } else {
      combinator = new GeneralSiblingCombinator(parent, selector);
    }
    return combinator;
  }

  private AbstractSelector buildSelector(final Iterable<SelectorTypeContext> selectorTypes) {
    int idx = -1;
    AbstractSelector root = null;
    AbstractSelector current = null;
    for (SelectorTypeContext ctx : selectorTypes) {
      if (current == null) {
        root = (AbstractSelector) visit(ctx);
        current = root;
      } else {
        AbstractSelector next = (AbstractSelector) visit(ctx);
        if (idx != -1 && idx + 1 != ctx.start.getStartIndex()) {
          root = new DescendantCombinator(current, next);
          current = root;
        } else {
          current.next(next);
          current = next;
        }
      }
      // buffer.append(ctx.getText());
      idx = ctx.stop.getStopIndex();
    }
    return root;
  }

  @Override
  public Object visitPseudo(final PseudoContext ctx) {
    String operator = ":";
    if (ctx.twoColon != null) {
      operator += ":";
    }
    if (ctx.id != null) {
      return new PseudoSelector(text(ctx.id), operator);
    }
    return new PseudoSelector(ctx.functionalPseudo().getText(), operator);
  }

  @Override
  public Object visitNegation(final NegationContext ctx) {
    Selector selector = (Selector) visit(ctx.selectorType());
    return new NotSelector(selector);
  }

  @Override
  public Object visitUniversalNamepaceUniversalSelector(
      final UniversalNamepaceUniversalSelectorContext ctx) {
    return new UniversalSelector("*");
  }

  @Override
  public Object visitIdentNamespaceUniversalSelector(
      final IdentNamespaceUniversalSelectorContext ctx) {
    return new UniversalSelector(text(ctx.prefix));
  }

  @Override
  public Object visitNonamespaceUniversalSelector(final NonamespaceUniversalSelectorContext ctx) {
    return new UniversalSelector(null);
  }

  @Override
  public Object visitUnivesalNamespaceTypeSelector(final UnivesalNamespaceTypeSelectorContext ctx) {
    return new TypeSelector("*", text(ctx.IDENT()));
  }

  @Override
  public Object visitIdentNamespaceTypeSelector(final IdentNamespaceTypeSelectorContext ctx) {
    return new TypeSelector(text(ctx.prefix), text(ctx.id));
  }

  @Override
  public Object visitNonamespaceTypeSelector(final NonamespaceTypeSelectorContext ctx) {
    return new TypeSelector(null, text(ctx.IDENT()));
  }

  @Override
  public Object visitUniversalSelector(final UniversalSelectorContext ctx) {
    return new UniversalSelector(null);
  }

  @Override
  public Object visitTypeSelector(final TypeSelectorContext ctx) {
    return new TypeSelector(null, text(ctx.IDENT()));
  }

  @Override
  public AbstractSelector visitClassSelector(final ClassSelectorContext ctx) {
    return new ClassSelector(ctx.CLASS().getText());
  }

  @Override
  public AbstractSelector visitIdSelector(final IdSelectorContext ctx) {
    return new IdSelector(text(ctx.HASH()));
  }

  @Override
  public AbstractSelector visitAttribute(final AttributeContext ctx) {
    return new AttributeSelector(text(ctx.prefix), text(ctx.name),
        ValueSelector.of(text(ctx.operator)), text(ctx.value));
  }

  private String text(final TerminalNode ctx) {
    return ctx == null ? null : ctx.getText();
  }

  private String text(final Token token) {
    return token == null ? null : token.getText();
  }
}
