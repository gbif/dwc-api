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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DcElementTest {

  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  @Test
  public void testNames() {
    for (DcElement t : DcElement.values()) {
      assertFalse(t.simpleName().contains("_"), "Bad term: " + t.simpleName());
      assertFalse(t.qualifiedName().contains("_"), "Bad term: " + t.qualifiedName());
      assertFalse(t.isClass(), "Bad term: " + t.qualifiedName());
    }
  }

  /**
   * Test that iterates over all DcElement values and uses the qualified name alone as input to the factory and then
   * verifies the Term returned is the same as the original one.
   */
  @Test
  public void testTermEquality() {
    for (DcTerm t : DcTerm.values()) {
      assertEquals(t, TERM_FACTORY.findTerm(t.qualifiedName()));
    }
  }

  /**
   * A lookup for "http://purl.org/dc/elements/1.1/rights" shouldn't return "http://purl.org/dc/terms/rights"
   */
  @Test
  public void testFindTermWithValidNamespace() {
    String qualifiedName = "http://purl.org/dc/elements/1.1/rights";
    Term term = TERM_FACTORY.findTerm(qualifiedName);
    assertEquals(qualifiedName, term.qualifiedName());
  }

  /**
   * There are 15 terms in DcElement that share the same name with DcTerm elements. This test ensures, that
   * when a term lookup is done with the prefix "dc", the DcElement under the /elements/1.1/ namespace
   * is returned, not the DcTerm under the /terms namespace.
   */
  @Test
  public void testFindTermWithDcPrefix() {
    String name = "dc:rights";
    Term term = TERM_FACTORY.findTerm(name);
    assertEquals("http://purl.org/dc/elements/1.1/rights", term.qualifiedName());
  }
}
