package org.gbif.dwc.terms;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Terms}
 *
 */
public class TermsTest {

  @Test
  public void testGetValueOfFirst(){
    Map<Term, String> record = new HashMap<Term, String>();
    record.put(DcTerm.title, "The DcTerm title");
    //empty value should be ignored
    record.put(AcTerm.caption, "");
    record.put(DcElement.creator, "Steven Seagal");

    assertEquals("The DcTerm title", Terms.getValueOfFirst(record, AcTerm.caption, DcTerm.title));

    record = new HashMap<Term, String>();
    record.put(AcTerm.caption, "The AcTerm title");
    record.put(DcElement.creator, "Steven Seagal");
    assertEquals("The AcTerm title", Terms.getValueOfFirst(record, AcTerm.caption, DcTerm.title));
  }

  @Test
  public void testIsTermValueBlank(){
    assertTrue(Terms.isTermValueBlank("\\N"));
    assertTrue(Terms.isTermValueBlank("NULL"));
    assertFalse(Terms.isTermValueBlank("ANULLSTRING"));
  }
}
