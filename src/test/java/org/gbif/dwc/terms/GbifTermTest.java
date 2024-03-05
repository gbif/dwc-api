/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.dwc.terms;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GbifTermTest extends TermBaseTest<GbifTerm> {

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
    assertEquals(2, new HashSet<>(datasetTerms).size());

    List<GbifTerm> occurrenceTerms = GbifTerm.listByGroup(DwcTerm.GROUP_OCCURRENCE);
    assertEquals(13, occurrenceTerms.size());
    assertEquals(13, new HashSet<>(occurrenceTerms).size());

    List<GbifTerm> locationTerms = GbifTerm.listByGroup(DwcTerm.GROUP_LOCATION);
    assertEquals(9, locationTerms.size());
    assertEquals(9, new HashSet<>(locationTerms).size());

    List<GbifTerm> rowTerms = GbifTerm.listByGroup(GbifTerm.GROUP_ROW_TYPE);
    assertEquals(9, rowTerms.size());
    assertEquals(9, new HashSet<>(rowTerms).size());

    List<GbifTerm> distributionTerms = GbifTerm.listByGroup(GbifTerm.GROUP_SPECIES_DISTRIBUTION_EXTENSION);
    assertEquals(2, distributionTerms.size());
    assertEquals(2, new HashSet<>(distributionTerms).size());

    List<GbifTerm> profileTerms = GbifTerm.listByGroup(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION);
    assertEquals(10, profileTerms.size());
    assertEquals(10, new HashSet<>(profileTerms).size());

    List<GbifTerm> taxonTerms = GbifTerm.listByGroup(DwcTerm.GROUP_TAXON);
    assertEquals(15, taxonTerms.size());
    assertEquals(15, new HashSet<>(taxonTerms).size());

    List<GbifTerm> crawlingTerms = GbifTerm.listByGroup(GbifTerm.GROUP_CRAWLING);
    assertEquals(3, crawlingTerms.size());
    assertEquals(3, new HashSet<>(crawlingTerms).size());

    List<GbifTerm> vernacularTerms = GbifTerm.listByGroup(GbifTerm.GROUP_VERNACULAR_NAME_EXTENSION);
    assertEquals(3, vernacularTerms.size());
    assertEquals(3, new HashSet<>(vernacularTerms).size());
  }

  @Test
  public void testGroupCoverage() {
    HashSet<GbifTerm> arrayTerms = new HashSet<>();
    for (GbifTerm t : GbifTerm.TAXONOMIC_TERMS) {
      arrayTerms.add(t);
      assertFalse(t.isClass());
      assertEquals(DwcTerm.GROUP_TAXON, t.getGroup());
    }

    for (GbifTerm t : GbifTerm.listByGroup(DwcTerm.GROUP_TAXON)) {
      if (!arrayTerms.contains(t)) {
        assertTrue(arrayTerms.contains(t), "Missing taxonomic term in GbifTerm.TAXONOMIC_TERMS: " + t.qualifiedName());
      }
    }
  }

  @Test
  public void testDeprecated() {
    assertFalse(GbifTerm.gbifID.isDeprecated());
    assertTrue(GbifTerm.coordinateAccuracy.isDeprecated());
  }
}
