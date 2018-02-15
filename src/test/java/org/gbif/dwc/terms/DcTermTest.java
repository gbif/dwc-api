package org.gbif.dwc.terms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 *
 */
public class DcTermTest {

  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  @Test
  public void testNames() throws Exception {
    for (DcTerm t : DcTerm.values()) {
      assertFalse("Bad term: " + t.simpleName(), t.simpleName().contains("_"));
      assertFalse("Bad term: " + t.qualifiedName(), t.qualifiedName().contains("_"));
    }
  }

  /**
   * Test that iterates over all DcTerm values and uses the simple name alone as input to the factory and then verifies
   * the Term returned is the same as the original one.
   */
  @Test
  public void testTermEquality() throws Exception {
    for (DcTerm t : DcTerm.values()) {
      assertEquals(t, TERM_FACTORY.findTerm(t.qualifiedName()));
      assertEquals(t, TERM_FACTORY.findTerm(t.simpleName(), t.isClass()));
    }
  }

  /**
   * A lookup for "http://purl.org/dc/terms/rights" shouldn't return "http://purl.org/dc/elements/1.1/rights".
   */
  @Test
  public void testFindTermWithValidNamespace() {
    String qualifiedName = "http://purl.org/dc/terms/rights";
    Term term = TERM_FACTORY.findTerm(qualifiedName);
    assertEquals(qualifiedName, term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the simple name only (no prefix, no namespace), the DcTerm under the /terms
   * namespace is returned, not the DcElement under the /elements/1.1/rights namespace.
   */
  @Test
  public void testFindTermWithNoNamespaceAndNoPrefix() {
    String name = "rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/terms/rights", term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the prefix "dcterms", the DcTerm under the /terms namespace
   * is returned, not the DcElements term under the /elements/1.1/ namespace.
   */
  @Test
  public void testFindTermWithDctermsPrefix() {
    String name = "dcterms:rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/terms/rights", term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the prefix "dct", the DcTerm under the /terms namespace
   * is returned, not the DcElements term under the /elements/1.1/ namespace.
   */
  @Test
  public void testFindTermWithDctPrefix() {
    String name = "dct:rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/terms/rights", term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the prefix "dcterm", the DcTerm under the /terms namespace
   * is returned, not the DcElements term under the /elements/1.1/ namespace.
   */
  @Test
  public void testFindTermWithDctermPrefix() {
    String name = "dcterm:rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/terms/rights", term.qualifiedName());
  }
}
