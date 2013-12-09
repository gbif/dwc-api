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
  public void testListByGroup() {
    assertEquals(34, DwcTerm.listByGroup(DwcTerm.GROUP_TAXON).size());
    assertEquals(11, DwcTerm.listByGroup("Record").size());
    assertEquals(0, DwcTerm.listByGroup("Random").size());
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
      if (!t.isClass()) {
        assertTrue("Missing taxonomic term in DwcTerm.TAXONOMIC_TERMS: " + t.qualifiedName(), arrayTerms.contains(t));
      }
    }
  }

}
