package org.gbif.dwc.terms;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class GbifTermTest extends TermBaseTest {

  public GbifTermTest() {
    super(GbifTerm.class);
  }

  @Test
  public void testTerm() {
    assertEquals("taxonKey", GbifTerm.taxonKey.simpleName());
    assertEquals("http://rs.gbif.org/terms/1.0/taxonKey", GbifTerm.taxonKey.qualifiedName());

    assertEquals("lastInterpreted", GbifTerm.lastInterpreted.simpleName());
    assertEquals("http://rs.gbif.org/terms/1.0/lastInterpreted", GbifTerm.lastInterpreted.qualifiedName());
  }

  @Test
  public void testIsClass() {
    assertTrue(GbifTerm.VernacularName.isClass());
    assertTrue(GbifTerm.Distribution.isClass());
    assertFalse(GbifTerm.datasetKey.isClass());
  }

  @Test
  public void testNumberOfGroups() {
    assertEquals(9, GbifTerm.GROUPS.length);
  }

  /**
   * Test each group contains the expected number of terms, and contains no duplicates.
   */
  @Test
  public void testListByGroup() {
    List<GbifTerm> datasetTerms = GbifTerm.listByGroup(GbifTerm.GROUP_DATASET);
    assertEquals(2, datasetTerms.size());
    assertEquals(2, new HashSet<GbifTerm>(datasetTerms).size());

    List<GbifTerm> occurrenceTerms = GbifTerm.listByGroup(DwcTerm.GROUP_OCCURRENCE);
    assertEquals(13, occurrenceTerms.size());
    assertEquals(13, new HashSet<GbifTerm>(occurrenceTerms).size());

    List<GbifTerm> locationTerms = GbifTerm.listByGroup(DwcTerm.GROUP_LOCATION);
    assertEquals(6, locationTerms.size());
    assertEquals(6, new HashSet<GbifTerm>(locationTerms).size());

    List<GbifTerm> rowTerms = GbifTerm.listByGroup(GbifTerm.GROUP_ROW_TYPE);
    assertEquals(9, rowTerms.size());
    assertEquals(9, new HashSet<GbifTerm>(rowTerms).size());

    List<GbifTerm> distributionTerms = GbifTerm.listByGroup(GbifTerm.GROUP_SPECIES_DISTRIBUTION_EXTENSION);
    assertEquals(2, distributionTerms.size());
    assertEquals(2, new HashSet<GbifTerm>(distributionTerms).size());

    List<GbifTerm> profileTerms = GbifTerm.listByGroup(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION);
    assertEquals(10, profileTerms.size());
    assertEquals(10, new HashSet<GbifTerm>(profileTerms).size());

    List<GbifTerm> taxonTerms = GbifTerm.listByGroup(DwcTerm.GROUP_TAXON);
    assertEquals(16, taxonTerms.size());
    assertEquals(16, new HashSet<GbifTerm>(taxonTerms).size());

    List<GbifTerm> crawlingTerms = GbifTerm.listByGroup(GbifTerm.GROUP_CRAWLING);
    assertEquals(3, crawlingTerms.size());
    assertEquals(3, new HashSet<GbifTerm>(crawlingTerms).size());

    List<GbifTerm> vernacularTerms = GbifTerm.listByGroup(GbifTerm.GROUP_VERNACULAR_NAME_EXTENSION);
    assertEquals(3, vernacularTerms.size());
    assertEquals(3, new HashSet<GbifTerm>(vernacularTerms).size());
  }

  @Test
  public void testGroupCoverage() {
    HashSet<GbifTerm> arrayTerms = new HashSet<GbifTerm>();
    for (GbifTerm t : GbifTerm.TAXONOMIC_TERMS) {
      arrayTerms.add(t);
      assertFalse(t.isClass());
      assertEquals(DwcTerm.GROUP_TAXON, t.getGroup());
    }

    for (GbifTerm t : GbifTerm.listByGroup(DwcTerm.GROUP_TAXON)) {
      if (!arrayTerms.contains(t)) {
        assertTrue("Missing taxonomic term in GbifTerm.TAXONOMIC_TERMS: " + t.qualifiedName(), arrayTerms.contains(t));
      }
    }
  }

  @Test
  public void testDeprecated() {
    assertFalse(GbifTerm.gbifID.isDeprecated());
    assertTrue(GbifTerm.coordinateAccuracy.isDeprecated());
  }

}
