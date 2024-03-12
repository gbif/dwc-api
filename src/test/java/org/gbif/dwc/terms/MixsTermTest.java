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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MixsTermTest extends TermBaseTest<MixsTerm> {

  public MixsTermTest() {
    super(MixsTerm.class, new String[]{"-"}, false);
  }

  @Test
  public void testMixsTerm() {
    assertEquals("samp_size", MixsTerm.samp_size.simpleName());
    assertEquals("https://w3id.org/mixs/0000001", MixsTerm.samp_size.qualifiedName());
    assertEquals("https://w3id.org/gensc/terms/MIXS:0000001", MixsTerm.samp_size.alternativeNames()[1]);
 }

  @Test
  public void testIsClass() {
    assertFalse(MixsTerm.samp_size.isClass());
  }
}
