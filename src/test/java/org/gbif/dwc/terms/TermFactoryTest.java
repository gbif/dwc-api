package org.gbif.dwc.terms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TermFactoryTest {

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
