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

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Wgs84GeoPositioningTermTest extends TermBaseTest<Wgs84GeoPositioningTerm> {

  public Wgs84GeoPositioningTermTest() {
    super(Wgs84GeoPositioningTerm.class, new String[]{"-"}, false);
  }

  @Test
  public void testClassTerms () {
    assertEquals(2, Stream.of(Wgs84GeoPositioningTerm.values()).filter(Term::isClass).count());
  }
}
