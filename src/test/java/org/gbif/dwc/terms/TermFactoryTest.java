package org.gbif.dwc.terms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TermFactoryTest {

  @Test
  public void testFindTerm() {
    TermFactory factory = new TermFactory();

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

}
