package org.gbif.dwc.terms;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TermFactoryTest {
  final TermFactory TF = TermFactory.instance();

  /**
   * GBIF code assumes a term coming from any of the Term enumerations mostly have unique simple names.
   * This tests verifies that!
   *
   * AcefTerm is known to overlap, so its excluded, see skipSimple in AcefTermTest.
   */
  @Test
  public void testKnownTermUniqueness() {
    Set<String> names = new HashSet<String>();

    addTerms(names, DwcTerm.values());
    addTerms(names, DcTerm.values());
    addTerms(names, GbifTerm.values());
    addTerms(names, GbifInternalTerm.values());
    addTerms(names, IucnTerm.values());

    addTerms(names, AcTerm.values());
    addTerms(names, XmpTerm.values());
    addTerms(names, XmpRightsTerm.values());
  }

  private void addTerms(Set<String> names, Term[] terms) {
    for (Term t : terms) {
      assertFalse("Duplicate simple name "+t.simpleName(), names.contains(t.simpleName()));
      names.add(t.simpleName());
    }
  }

  @Test
  public void testFindTerm() {
    assertEquals(DwcTerm.scientificName, TF.findTerm("ScientificName"));
    assertEquals(DwcTerm.scientificName, TF.findTerm("dwc:scientificName"));
    assertEquals(DwcTerm.scientificName, TF.findTerm("http://rs.tdwg.org/dwc/terms/scientificName"));
    assertEquals(DcElement.identifier, TF.findTerm("dc:identifier"));
    assertEquals(GbifTerm.Identifier, TF.findTerm("identifier"));
    assertEquals(GbifTerm.Identifier, TF.findClassTerm("identifier"));
    assertEquals(DcTerm.identifier, TF.findPropertyTerm("identifier"));
    assertEquals(DcTerm.identifier, TF.findTerm("ID"));
    assertEquals(DwcTerm.parentNameUsageID, TF.findTerm("dwc:higherNameUsageID"));

    assertEquals(GbifInternalTerm.unitQualifier, TF.findTerm("UNIT_QUALIFIER"));

    assertEquals("threatStatus", TF.findTerm("http://rs.gbif.org/terms/1.0/threatStatus").simpleName());
    assertEquals("threatStatus", TF.findTerm("http://rs.gbif.org/terms/1321.43/threatStatus").simpleName());

    assertEquals(DwcTerm.catalogNumber, TF.findTerm("\"catalogNumber\""));
    assertEquals(AcefTerm.Source, TF.findTerm("acef:source"));
    assertEquals(DwcTerm.family, TF.findTerm("dwc:family"));
    assertEquals(DwcTerm.family, TF.findTerm("family"));
    assertEquals(AcefTerm.Family, TF.findTerm("acef:family"));
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
