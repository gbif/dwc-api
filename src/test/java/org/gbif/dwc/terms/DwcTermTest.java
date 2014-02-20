/***************************************************************************
 * Copyright 2010 Global Biodiversity Information Facility Secretariat
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ***************************************************************************/

package org.gbif.dwc.terms;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DwcTermTest {

  @Test
  public void testClassTerm() {
    assertEquals("class", DwcTerm.class_.simpleName());
    assertEquals("http://rs.tdwg.org/dwc/terms/class", DwcTerm.class_.qualifiedName());

    assertEquals("order", DwcTerm.order.simpleName());
    assertEquals("http://rs.tdwg.org/dwc/terms/order", DwcTerm.order.qualifiedName());
  }

  @Test
  public void testIsClass() {
    assertTrue(DwcTerm.Taxon.isClass());
    assertTrue(DwcTerm.Event.isClass());
    assertFalse(DwcTerm.scientificName.isClass());
  }

  @Test
  public void testNumberOfGroups() {
    assertEquals(9, DwcTerm.GROUPS.length);
  }

  /**
   * Test each group contains the expected number of terms, and contains no duplicates.
   */
  @Test
  public void testListByGroup() {
    List<DwcTerm> taxonTerms = DwcTerm.listByGroup(DwcTerm.GROUP_TAXON);
    assertEquals(35, taxonTerms.size());
    assertEquals(35, new HashSet<DwcTerm>(taxonTerms).size());

    List<DwcTerm> materialTerms = DwcTerm.listByGroup(DwcTerm.GROUP_MATERIAL_SAMPLE);
    assertEquals(2, materialTerms.size());
    assertEquals(2, new HashSet<DwcTerm>(materialTerms).size());

    List<DwcTerm> locationTerms = DwcTerm.listByGroup(DwcTerm.GROUP_LOCATION);
    assertEquals(44, locationTerms.size());
    assertEquals(44, new HashSet<DwcTerm>(locationTerms).size());

    List<DwcTerm> eventTerms = DwcTerm.listByGroup(DwcTerm.GROUP_EVENT);
    assertEquals(16, eventTerms.size());
    assertEquals(16, new HashSet<DwcTerm>(eventTerms).size());

    List<DwcTerm> identificationTerms = DwcTerm.listByGroup(DwcTerm.GROUP_IDENTIFICATION);
    assertEquals(10, identificationTerms.size());
    assertEquals(10, new HashSet<DwcTerm>(identificationTerms).size());

    List<DwcTerm> occurrenceTerms = DwcTerm.listByGroup(DwcTerm.GROUP_OCCURRENCE);
    assertEquals(24, occurrenceTerms.size());
    assertEquals(24, new HashSet<DwcTerm>(occurrenceTerms).size());

    List<DwcTerm> relationshipTerms = DwcTerm.listByGroup(DwcTerm.GROUP_RESOURCERELATIONSHIP);
    assertEquals(8, relationshipTerms.size());
    assertEquals(8, new HashSet<DwcTerm>(relationshipTerms).size());

    List<DwcTerm> measurementTerms = DwcTerm.listByGroup(DwcTerm.GROUP_MEASUREMENTORFACT);
    assertEquals(10, measurementTerms.size());
    assertEquals(10, new HashSet<DwcTerm>(measurementTerms).size());

    List<DwcTerm> geologicalTerms = DwcTerm.listByGroup(DwcTerm.GROUP_GEOLOGICALCONTEXT);
    assertEquals(19, geologicalTerms.size());
    assertEquals(19, new HashSet<DwcTerm>(geologicalTerms).size());

    List<DwcTerm> recordTerms = DwcTerm.listByGroup("Record");
    assertEquals(11, recordTerms.size());
    assertEquals(11, new HashSet<DwcTerm>(recordTerms).size());

    List<DwcTerm> randomTerms = DwcTerm.listByGroup("Random");
    assertEquals(0, randomTerms.size());
    assertEquals(0, new HashSet<DwcTerm>(randomTerms).size());
  }

  @Test
  public void testGroupCoverage() {
    HashSet<DwcTerm> arrayTerms = new HashSet<DwcTerm>();
    for (DwcTerm t : DwcTerm.TAXONOMIC_TERMS) {
      arrayTerms.add(t);
      assertFalse(t.isClass());
      assertEquals(DwcTerm.GROUP_TAXON, t.getGroup());
    }

    for (DwcTerm t : DwcTerm.listByGroup(DwcTerm.GROUP_TAXON)) {
      if (!arrayTerms.contains(t) && !t.isClass()) {
        assertTrue("Missing taxonomic term in DwcTerm.TAXONOMIC_TERMS: " + t.qualifiedName(), arrayTerms.contains(t));
      }
    }
  }

}
