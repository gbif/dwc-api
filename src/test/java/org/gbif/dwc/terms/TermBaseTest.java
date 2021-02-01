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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Generic tests for all Term implementing classes.
 */
public abstract class TermBaseTest<T extends Term> {

  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  private final Class<T> clazz;
  private final T[] values;
  private final String[] forbiddenChars;
  private final boolean skipSimple;

  public TermBaseTest(Class<T> clazz) {
    this(clazz, new String[]{"_","-"}, false);
  }

  public TermBaseTest(Class<T> clazz, String[] forbiddenChars, boolean skipSimple) {
    this.clazz = clazz;
    this.forbiddenChars = forbiddenChars;
    values = clazz.getEnumConstants();
    this.skipSimple = skipSimple;
  }

  @Test
  public void testNames() throws Exception {
    for (T t : values) {
      for (String c : forbiddenChars) {
        assertFalse("Term contains forbidden character " + c + " : " + t.simpleName(), t.simpleName().contains(c));
        assertFalse("Term contains forbidden character " + c + " : " + t.qualifiedName(), t.qualifiedName().contains(c));
      }
    }
  }

  /**
   * Test that iterates over all term values and uses the simple name alone as input to the factory and then verifies
   * the Term returned is the same as the original one.
   */
  @Test
  public void testFindSimpleTerm() throws Exception {
    if (!skipSimple) {
      for (T t : values) {
          assertEquals(t, TERM_FACTORY.findTerm(t.simpleName(), t.isClass()));
      }
    }
  }

  @Test
  public void testFindPrefixedTerms() {
    for (T t : values) {
      Term found = TERM_FACTORY.findTerm(t.prefixedName(), t.isClass());
      assertEquals(t, found);
    }
  }

  @Test
  public void testFindQualifiedTerm() {
    for (T t : values) {
      assertEquals(t, TERM_FACTORY.findTerm(t.qualifiedName(), t.isClass()));
    }
  }

}
