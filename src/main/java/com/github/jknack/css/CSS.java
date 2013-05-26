package com.github.jknack.css;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import com.github.jknack.css.internal.CssLexer;
import com.github.jknack.css.internal.CssParser;
import com.github.jknack.css.internal.CssParser.StyleSheetContext;

public class CSS {

  private static class ErrorReporter implements ANTLRErrorListener {

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol,
        final int line, final int charPositionInLine, final String msg,
        final RecognitionException e) {
      throw new ParseCancellationException(msg, e);
    }

    @Override
    public void reportAmbiguity(final Parser recognizer, final DFA dfa, final int startIndex,
        final int stopIndex, final BitSet ambigAlts, final ATNConfigSet configs) {
    }

    @Override
    public void reportAttemptingFullContext(final Parser recognizer, final DFA dfa,
        final int startIndex, final int stopIndex, final ATNConfigSet configs) {
    }

    @Override
    public void reportContextSensitivity(final Parser recognizer, final DFA dfa,
        final int startIndex, final int stopIndex, final ATNConfigSet configs) {
    }

  }

  public static boolean debug = false;

  public CSS() {
    // TODO Auto-generated constructor stub
  }

  public StyleSheet parse(final String content) {
    CssLexer lexer = new CssLexer(new ANTLRInputStream(content));
//    lexer.removeErrorListeners();
    if (!debug) {
      lexer.addErrorListener(new ErrorReporter());
    }
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    tokens.fill();
    CssParser parser = new CssParser(tokens);
//    parser.setErrorHandler(new BailErrorStrategy());
//    parser.removeParseListeners();
    if (!debug) {
      parser.addErrorListener(new ErrorReporter());
    }
    if (debug) {
      for (Token tok : tokens.getTokens()) {
        CommonToken ct = (CommonToken) tok;
        String[] tokenNames = lexer.getTokenNames();
        int type = ct.getType();
        System.out.println((type > 0 ? tokenNames[type] : "EOF") + "(" + ct.getText() + ")");
      }
    }
    StyleSheetContext tree = parser.styleSheet();
    if (debug) {
      System.out.println(tree.toStringTree(parser));
    }
    CSSBuilder builder = new CSSBuilder();
    return (StyleSheet) builder.visit(tree);
  }
}
