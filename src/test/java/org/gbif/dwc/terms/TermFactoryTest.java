package org.gbif.dwc.terms;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TermFactoryTest {

  /**
   * GBIF code assumes a term coming from any of the Term enumerations here have unique simple names.
   * This tests verifies that!
   */
  @Test
  public void testKnownTermUniqueness() {
    Set<String> names = new HashSet<String>();

    addTerms(names, DwcTerm.values());
    addTerms(names, DcTerm.values());
    addTerms(names, GbifTerm.values());
    addTerms(names, GbifInternalTerm.values());
    addTerms(names, IucnTerm.values());
  }

  private void addTerms(Set<String> names, Term[] terms) {
    for (Term t : terms) {
      assertFalse("Duplicate simple name "+t.simpleName(), names.contains(t.simpleName()));
      names.add(t.simpleName());
    }
  }

  @Test
  public void testFindTerm() {
    TermFactory factory = TermFactory.instance();

    assertEquals(DwcTerm.scientificName, factory.findTerm("ScientificName"));
    assertEquals(DwcTerm.scientificName, factory.findTerm("dwc:scientificName"));
    assertEquals(DwcTerm.scientificName, factory.findTerm("http://rs.tdwg.org/dwc/terms/scientificName"));
    assertEquals(DcTerm.identifier, factory.findTerm("dc:identifier"));
    assertEquals(DcTerm.identifier, factory.findTerm("identifier"));
    assertEquals(DcTerm.identifier, factory.findTerm("ID"));
    assertEquals(DwcTerm.parentNameUsageID, factory.findTerm("dwc:higherTaxonID"));

    assertEquals(GbifInternalTerm.unitQualifier, factory.findTerm("UNIT_QUALIFIER"));

    assertEquals("threatStatus", factory.findTerm("http://rs.gbif.org/terms/1.0/threatStatus").simpleName());
    assertEquals("threatStatus", factory.findTerm("http://rs.gbif.org/terms/1321.43/threatStatus").simpleName());

    assertEquals(DwcTerm.catalogNumber, factory.findTerm("\"catalogNumber\""));
  }

  @Test
  public void addUnknownTerm() {
    TermFactory factory = TermFactory.instance();

    Term me1 = factory.findTerm("http://me.com/#me");
    Term me2 = factory.findTerm("http://me.com/me");
    Term me3 = factory.findTerm("http://me.org/me");

    assertNotEquals(me1, me2);
    assertNotEquals(me1, me3);
    assertNotEquals(me2, me3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addBadTerm() {
    TermFactory factory = TermFactory.instance();
    factory.findTerm("Hallo Tim");
  }

  @Test
  public void addSimpleTerm() {
    TermFactory factory = TermFactory.instance();

    Term hallo = factory.findTerm("hallo");
    assertEquals(UnknownTerm.class, hallo.getClass());
    assertEquals("http://unknown.org/hallo", hallo.qualifiedName());
    assertEquals("hallo", hallo.simpleName());


    Term tim = factory.findTerm("Tim");
    assertEquals(UnknownTerm.class, tim.getClass());
    assertEquals("http://unknown.org/Tim", tim.qualifiedName());
    assertEquals("Tim", tim.simpleName());

    assertNotEquals(hallo, tim);
  }

  @Test
  public void addUnknownSimpleTerm() {
    TermFactory factory = TermFactory.instance();

    Term t1 = factory.findTerm("me");
    Term t2 = factory.findTerm("me");
    Term t3 = factory.findTerm("Ne");

    assertEquals(t1, t2);
    assertNotEquals(t1, t3);
    assertNotEquals(t2, t3);
  }


  /**
     * Not a real test, just a way of running many concurrent TermFactory.instance() calls to verify thread safety.
     */
  @Test
  public void testMultithreadStart() throws InterruptedException {
    int threadCount = 100;
    ExecutorService tp = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      tp.submit(new TermFactoryLoader());
    }
    tp.shutdown();
    tp.awaitTermination(30, TimeUnit.SECONDS);
  }

  private static class TermFactoryLoader implements Runnable {
    @Override
    public void run() {
      TermFactory.instance();
    }
  }
}
