package org.gbif.dwc.terms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TermComparatorTest {

  @Test
  public void testCompare() throws Exception {
    TermComparator comp = new TermComparator();

    List<Term> terms = new ArrayList<Term>();
    terms.add(DwcTerm.basisOfRecord);
    terms.add(DcTerm.title);
    terms.add(DcTerm.references);
    terms.add(DwcTerm.scientificName);
    terms.add(IucnTerm.threatStatus);
    terms.add(GbifTerm.canonicalName);
    terms.add(GbifTerm.depth);

    Collections.sort(terms, comp);

    assertEquals(IucnTerm.threatStatus, terms.get(0));
    assertEquals(DcTerm.references, terms.get(1));
    assertEquals(DcTerm.title, terms.get(2));
    assertEquals(GbifTerm.canonicalName, terms.get(3));
    assertEquals(GbifTerm.depth, terms.get(4));
    assertEquals(DwcTerm.basisOfRecord, terms.get(5));
    assertEquals(DwcTerm.scientificName, terms.get(6));
  }
}