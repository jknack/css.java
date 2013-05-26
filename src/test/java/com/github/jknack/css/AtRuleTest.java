package com.github.jknack.css;

import org.junit.Test;

public class AtRuleTest extends AbstractTest {

  @Test
  public void atCharset() {
    parse("@charset \"ISO-8859-15\";");

    parse("@chArseT \"UTF-8\";");

    parse("@CHARSET \"UTF-8\";");
  }

  @Test
  public void atImportWithString() {
    parse("@import \"local.css\";");
    parse("@import \"local.css\" screen, projection;");
    parse("@iMpoRt \"local.css\";");
    parse("@IMPORT \"local.css\";");
  }

  @Test
  public void atImportWithURI() {
    parse("@import url(\"css/main.css\");");
    parse("@import url(\"css/main.css\") screen, projection;");
    parse("@IMPORT url(\"css/main.css\");");
  }

  @Test
  public void atImportWithMedia() {
    parse("@import url(css/main.css) screen, projection;");
    parse("@IMPORT url(css/main.css) screen, projection;");
  }

  @Test
  public void atImportW3Example4() {
    parse("@import url(color.css) screen and (color);");
  }

  @Test
  public void atMediaW3Example5() {
    parse("@media all and (min-width:500px) { }");
    parse("@media all and (orientation: portrait) { }");
  }

  @Test
  public void atMediaW3Example6() {
    parse("@media screen and (color), projection and (color) { }");
  }

  @Test
  public void atMediaW3Example10() {
    parse("@import url(example.css) screen and (color), projection and (color);");
    parse("@media screen and (color), projection and (color) {}");
  }

  @Test
  public void atMedia() {
    parse("@media print { body {padding: 1in; border: 0.5pt solid #666;}}");
  }

  @Test
  public void atMediaWithMediums() {
    parse("@media screen, projection { html {background: #fffef0; color:#300;} body {max-width: 35em; margin: 0 auto;}}");
  }

  @Test
  public void atMediaCSS3() {
    parse("@media (min-width: 480px) {.header {width: 75%;}}");
    parse("@MeDiA (min-width: 480px) {.header {width: 75%;}}");
    parse("@MEDIA (min-width: 480px) {.header {width: 75%;}}");
  }

  @Test
  public void atMediaW3Example2() {
    parse("@media screen {  * { font-family: sans-serif } }");
  }

  @Test
  public void atPage() {
    parse("@page {margin: 1n 1.5in;}");
    parse("@pAgE {margin: 1n 1.5in;}");
    parse("@PAGE {margin: 1n 1.5in;}");
  }

  @Test
  public void atPageWithSeudoPage() {
    parse("@page :left{margin: 1n 1.5in;}");
  }

  @Test
  public void atFontFace() {
    parse("@font-face {font-family: \"Example Font\"; src: url(\"http://www.example.com/fonts\");}");

    parse("@fOnt-Face {font-family: \"Example Font\"; src: url(\"http://www.example.com/fonts\");}");

    parse("@FONT-FACE {font-family: \"Example Font\"; src: url(\"http://www.example.com/fonts\");}");
  }

  @Test
  public void atNamespace() {
    parse("@namespace \"http://example.com/q-markup\";");
  }

  @Test
  public void atNamespaceWithPrefix() {
    parse("@namespace Q \"http://example.com/q-markup\";");
  }

  @Test
  public void atNamespaceWithURL() {
    parse("@namespace url(http://example.com/q-markup) ;");
  }

  @Test
  public void atNamespaceWithPrefixAndURL() {
    parse("@namespace svg url(http://example.com/q-markup) ;");
  }
}
