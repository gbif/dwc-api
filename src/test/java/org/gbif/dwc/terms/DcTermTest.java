package org.gbif.dwc.terms;

import org.junit.Test;
import static org.junit.Assert.assertFalse;


/**
 *
 */
public class DcTermTest {

  @Test
  public void testNames() throws Exception {
    for (DcTerm t : DcTerm.values()) {
      assertFalse("Bad term: " + t.simpleName(), t.simpleName().contains("_"));
      assertFalse("Bad term: " + t.qualifiedName(), t.qualifiedName().contains("_"));
    }
  }
}
