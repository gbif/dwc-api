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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibTexTermTest {

  @Test
  public void build() {
    BibTexTerm t = new BibTexTerm("creator");
    assertEquals("creator", t.simpleName());
    assertEquals("http://bibtex.org/creator", t.qualifiedName());
    assertEquals("bib:creator", t.prefixedName());
    assertFalse(t.isClass());

    t = BibTexTerm.buildFromURI("http://bibtex.org/creator");
    assertEquals("creator", t.simpleName());
    assertEquals("http://bibtex.org/creator", t.qualifiedName());
    assertEquals("bib:creator", t.prefixedName());
    assertFalse(t.isClass());

    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("https://bibtex.org/creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("bibtex.org/creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("bibtex.org.creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("creator"));

    assertEquals("BibTeX", BibTexTerm.CLASS_TERM.simpleName());
    assertEquals("http://bibtex.org/BibTeX", BibTexTerm.CLASS_TERM.qualifiedName());
    assertEquals("bib:BibTeX", BibTexTerm.CLASS_TERM.prefixedName());
    assertTrue(BibTexTerm.CLASS_TERM.isClass());
  }

}