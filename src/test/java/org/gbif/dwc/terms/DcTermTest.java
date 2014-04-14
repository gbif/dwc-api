package org.gbif.dwc.terms;

import org.junit.Test;
import static org.junit.Assert.assertFalse;


/**
 *
 */
public class DcTermTest {

  @Test
  public void testSimpleName() throws Exception {
    for (DcTerm t : DcTerm.values()) {
      assertFalse(t.simpleName().contains("_"));
    }
  }
}
