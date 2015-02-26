package org.gbif.dwc.terms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * Generic tests for all Term implementing classes.
 */
public abstract class TermBaseTest<T extends Term> {

  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  private final Class<T> clazz;
  private final T[] values;
  private final String[] prefixes;

  public TermBaseTest(Class<T> clazz, String[] prefixes) {
    this.clazz = clazz;
    this.prefixes = prefixes;
    values = clazz.getEnumConstants();
  }

  @Test
  public void testNames() throws Exception {
    for (T t : values) {
      assertFalse("Bad term: " + t.simpleName(), t.simpleName().contains("_"));
      assertFalse("Bad term: " + t.qualifiedName(), t.qualifiedName().contains("_"));
    }
  }

  /**
   * Test that iterates over all term values and uses the simple name alone as input to the factory and then verifies
   * the Term returned is the same as the original one.
   */
  @Test
  public void testTermEquality() throws Exception {
    for (T t : values) {
      Term found = TERM_FACTORY.findTerm(t.simpleName());
      assertEquals(t, found);
    }
  }

  @Test
  public void testFindQualifiedTerm() {
    for (T t : values) {
      Term found = TERM_FACTORY.findTerm(t.qualifiedName());
      assertEquals(t, found);
    }
  }

  @Test
  public void testPrefixedTerms() {
    for (T t : values) {
      for (String pre : prefixes) {
        Term found = TERM_FACTORY.findTerm(pre+t.simpleName());
        assertEquals(t, found);
      }
    }
  }
}
