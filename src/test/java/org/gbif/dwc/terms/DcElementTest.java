package org.gbif.dwc.terms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DcElementTest {

  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  @Test
  public void testNames() throws Exception {
    for (DcElement t : DcElement.values()) {
      assertFalse("Bad term: " + t.simpleName(), t.simpleName().contains("_"));
      assertFalse("Bad term: " + t.qualifiedName(), t.qualifiedName().contains("_"));
      assertFalse("Bad term: " + t.qualifiedName(), t.isClass());
    }
  }

  /**
   * Test that iterates over all DcElement values and uses the qualified name alone as input to the factory and then
   * verifies the Term returned is the same as the original one.
   */
  @Test
  public void testTermEquality() throws Exception {
    for (DcTerm t : DcTerm.values()) {
      Term found = TERM_FACTORY.findTerm(t.qualifiedName());
      assertEquals(t, found);
    }
  }

  /**
   * A lookup for "http://purl.org/dc/elements/1.1/rights" shouldn't return "http://purl.org/dc/terms/rights"
   */
  @Test
  public void testFindTermWithValidNamespace() {
    String qualifiedName = "http://purl.org/dc/elements/1.1/rights";
    Term term = TERM_FACTORY.findTerm(qualifiedName);
    assertEquals(qualifiedName, term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the prefix "dc", the DcElement under the /elements/1.1/ namespace
   * is returned, not the DcTerm under the /terms namespace.
   */
  @Test
  public void testFindTermWithDcPrefix() {
    String name = "dc:rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/elements/1.1/rights", term.qualifiedName());
  }
}
