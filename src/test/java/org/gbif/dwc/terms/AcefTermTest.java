package org.gbif.dwc.terms;


import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class AcefTermTest extends TermBaseTest<AcefTerm>{

  public AcefTermTest() {
    super(AcefTerm.class, new String[]{"_","-"}, true);
  }

  @Test
  public void testClassTerms () {
    assertEquals(8, Stream.of(AcefTerm.values()).filter(Term::isClass).count());
  }
}
