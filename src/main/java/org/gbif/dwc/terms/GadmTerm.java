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

import java.net.URI;

public enum GadmTerm implements Term, AlternativeNames {
  level0Gid,
  level0Name,
  level1Gid,
  level1Name,
  level2Gid,
  level2Name,
  level3Gid,
  level3Name;

  private static final String PREFIX = "gadm";
  private static final String NS = "http://rs.gbif.org/terms/gadm/3.0/";
  private static final URI NS_URI = URI.create(NS);

  public final String[] normAlts;

  GadmTerm(String... alternatives) {
    normAlts = alternatives;
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return normAlts;
  }

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  @Override
  public boolean isClass() {
    return false;
  }
}
