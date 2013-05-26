package com.github.jknack.css;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SelectorTest extends AbstractTest {

  @Test
  public void universal() {
    selector("* {margin: 0; padding: 0;}", "*");
  }

  @Test
  public void elementTypeSelector() {
    selector("ul {margin: 0; padding: 0;}", "ul");
  }

  @Test
  public void classSelector() {
    selector("p.intro {color: #444;}", "p.intro");

    selector(".intro {color: #444;}", ".intro");

    selector("div.foo.bar {}", "div.foo.bar");

    selector("div.foo.bar[title^=\"Help\"] {}", "div.foo.bar[title^=\"Help\"]");
  }

  @Test
  public void idSelector() {
    selector("#breadcumbs {color: #444;}", "#breadcumbs");

    selector("ul#breadcumbs {color: #444;}", "ul#breadcumbs");
  }

  @Test
  public void attributeSelector() {
    selector("input[type = \"submit\"]{color: #444;}", "input[type=\"submit\"]");

    selector("[href]{color: #444;}", "[href]");

    selector("a[href]{color: #444;}", "a[href]");

    selector("a[foo|att=val]{color: #444;}", "a[foo|att=val]");
  }

  @Test
  public void hypenAttributeSelector() {
    selector("[hreflang|=\"en\"]{color: #444;}", "[hreflang|=\"en\"]");
  }

  @Test
  public void oneOfAttributeSelector() {
    selector("[hreflang~=\"warning\"] {}", "[hreflang~=\"warning\"]");
  }

  @Test
  public void containsAttributeSelector() {
    selector("a[href*=\"example.com\"] {}", "a[href*=\"example.com\"]");
  }

  @Test
  public void startWithAttributeSelector() {
    selector("a[href ^=\"http:\"] {}", "a[href^=\"http:\"]");
  }

  @Test
  public void endWithAttributeSelector() {
    selector("img[src$=\".png\"] {}", "img[src$=\".png\"]");
  }

  @Test
  public void selectorGroup() {
    StyleSheet sheet = selector("#foo td, td, th {}", "#foo td");
    assertEquals(3, sheet.first().selectors().size());
    assertEquals("td", sheet.first().selectors().get(1).name());
    assertEquals("th", sheet.first().selectors().get(2).name());
  }

  @Test
  public void childSelector() {
    selector("ul > li{}", "ul>li");

    selector("div ol>li p{}", "div ol>li p");

    selector("div > p > span > a{}", "div>p>span>a");
  }

  @Test
  public void descendantSelector() {
    selector("ul li{}", "ul li");

    selector("div * p {}", "div * p");

    selector("div p *[href] {}", "div p *[href]");
  }

  @Test
  public void adjacentSelector() {
//    selector("h2+p{}", "h2+p");

    selector("h2+p+span{}", "h2+p+span");

    selector("math + p {}", "math+p");

    selector("h1.opener + h2 {}", "h1.opener+h2");
  }

  @Test
  public void generalSiblingSelector() {
    selector("h2~p{}", "h2~p");
  }

  @Test
  public void pseudoClassesCCS1() {
    selector("a:link {}", "a:link");
    selector("a:hover {}", "a:hover");
    selector("a:visited {}", "a:visited");
    selector("a:focus:hover {}", "a:focus:hover");
    selector(":link {}", ":link");

    selector("ul:first-child {}", "ul:first-child");

    selector("p.note:target {}", "p.note:target");

    selector("*:target::before {}", "*:target::before");
  }

  @Test
  public void pseudoClassesCCS2() {
    selector(":lang(fr){}", ":lang(fr)");
  }

  @Test
  public void pseudoClassesCCS3() {
    selector("tr:nth-child(odd){}", "tr:nth-child(odd)");

    selector("tr:nth-child(2n){}", "tr:nth-child(2n)");

    selector("tr:nth-child(n){}", "tr:nth-child(n)");

    selector("tr:nth-child(N){}", "tr:nth-child(N)");

    selector("tr:nth-child(n-1){}", "tr:nth-child(n-1)");

    selector("tr:nth-child(2n+1){}", "tr:nth-child(2n+1)");

    selector("tr:nth-child(+7){}", "tr:nth-child(+7)");

    selector("tr:nth-child(-7){}", "tr:nth-child(-7)");

    selector("tr:nth-child(17){}", "tr:nth-child(17)");

    selector("tr:last-child{}", "tr:last-child");

    selector("div>p:first-of-type {}", "div>p:first-of-type");

    selector("foo:nth-child(0n+5) {}", "foo:nth-child(0n+5)");

    selector(":nth-child( +3n - 2 ) {}", ":nth-child(+3n-2)");

    selector(":nth-child( -n + 6 ) {}", ":nth-child(-n+6)");
  }

  @Test
  public void pseudoElements() {
    selector("p:first-letter {}", "p:first-letter");

    selector("textarea::selection {}", "textarea::selection");

    selector("p::first-line { text-transform: uppercase }", "p::first-line");
  }

  @Test
  public void typeSelectorsWithNamespace() {
    selector("foo|h1 { color: blue }", "foo|h1");
    selector("foo|* { color: yellow }", "foo|*");
    selector("|h1 { color: red }", "h1");
    selector(" *|h1 { color: green }", "*|h1");
  }

  @Test
  public void negationSelector() {
    selector("html|*:not(:link):not(:visited) {}", "html|*:not(:link):not(:visited)");

    selector(":not(table) {}", ":not(table)");
  }
}
