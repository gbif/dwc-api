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