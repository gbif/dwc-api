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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Terms}
 */
public class TermsTest {

  @Test
  public void testGetValueOfFirst(){
    Map<Term, String> record = new HashMap<Term, String>();
    record.put(DcTerm.title, "The DcTerm title");
    //empty value should be ignored
    record.put(AcTerm.caption, "");
    record.put(DcElement.creator, "Steven Seagal");

    assertEquals("The DcTerm title", Terms.getValueOfFirst(record, AcTerm.caption, DcTerm.title));

    record = new HashMap<Term, String>();
    record.put(AcTerm.caption, "The AcTerm title");
    record.put(DcElement.creator, "Steven Seagal");
    assertEquals("The AcTerm title", Terms.getValueOfFirst(record, AcTerm.caption, DcTerm.title));
  }

  @Test
  public void testIsTermValueBlank(){
    assertTrue(Terms.isTermValueBlank("\\N"));
    assertTrue(Terms.isTermValueBlank("NULL"));
    assertFalse(Terms.isTermValueBlank("ANULLSTRING"));
  }
}
