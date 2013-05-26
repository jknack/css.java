package com.github.jknack.css;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.github.jknack.css.AttributeSelector.ValueSelector;
import com.github.jknack.css.internal.CssBaseVisitor;
import com.github.jknack.css.internal.CssParser.AttributeContext;
import com.github.jknack.css.internal.CssParser.ClassSelectorContext;
import com.github.jknack.css.internal.CssParser.CombinatorContext;
import com.github.jknack.css.internal.CssParser.IdSelectorContext;
import com.github.jknack.css.internal.CssParser.IdentNamespaceTypeSelectorContext;
import com.github.jknack.css.internal.CssParser.IdentNamespaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.NegationContext;
import com.github.jknack.css.internal.CssParser.NonamespaceTypeSelectorContext;
import com.github.jknack.css.internal.CssParser.NonamespaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.PseudoContext;
import com.github.jknack.css.internal.CssParser.RuleSetContext;
import com.github.jknack.css.internal.CssParser.SelectorContext;
import com.github.jknack.css.internal.CssParser.SelectorGroupContext;
import com.github.jknack.css.internal.CssParser.SelectorTypeContext;
import com.github.jknack.css.internal.CssParser.StatementContext;
import com.github.jknack.css.internal.CssParser.StyleSheetContext;
import com.github.jknack.css.internal.CssParser.TypeSelectorContext;
import com.github.jknack.css.internal.CssParser.UniversalNamepaceUniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.UniversalSelectorContext;
import com.github.jknack.css.internal.CssParser.UnivesalNamespaceTypeSelectorContext;

public class CSSBuilder extends CssBaseVisitor<Object> {

  @Override
  public Object visit(final ParseTree tree) {
    return super.visit(tree);
  }

  @Override
  public Object visitStyleSheet(final StyleSheetContext ctx) {
    StyleSheet sheet = new StyleSheet();
    for (StatementContext statementCtx : ctx.statement()) {
      Object candidate = visitChildren(statementCtx);
      if (candidate instanceof Rule) {
        sheet.add((Rule) candidate);
      }
    }
    return sheet;
  }

  @Override
  public Object visitRuleSet(final RuleSetContext ctx) {
    Rule rule = new Rule();
    List<Selector> selectors = visitSelectorGroup(ctx.selectorGroup());
    for (Selector selector : selectors) {
      rule.add(selector);
    }
    return rule;
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