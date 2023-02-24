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

public class AcTermTest extends TermBaseTest<AcTerm> {

  public AcTermTest() {
    super(AcTerm.class, new String[]{"_","-"}, true);
  }

  @Test
  public void classTerms() {
    int counter = 0;
    int classCounter = 0;

    for (AcTerm t : AcTerm.values()) {
      counter++;
      if (t.isClass()) {
        classCounter++;
      }
    }
    assertEquals(3, classCounter);
    assertEquals(71, counter);

    assertEquals(AcTerm.Multimedia, TermFactory.instance().findTerm("ac:Multimedia", true));
    assertEquals(GbifTerm.Multimedia, TermFactory.instance().findTerm("Multimedia", true));
  }
}
