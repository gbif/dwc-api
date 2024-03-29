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

/**
 * Terms used in DwC archives from https://plazi.org
 */
public enum PlaziTerm implements Term, AlternativeNames {
  combinationYear,
  combinationAuthors,
  basionymYear,
  basionymAuthors,
  verbatimScientificName;

  private static final String PREFIX = "plazi";
  private static final String NS = "http://plazi.org/terms/1.0/";
  private static final URI NS_URI = URI.create(NS);

  private final boolean isClass;
  private final String[] alternatives;

  PlaziTerm(boolean isClass, String ... alternatives) {
    this.alternatives = alternatives;
    this.isClass = isClass;
  }

  PlaziTerm(String ... alternatives) {
    this(false, alternatives);
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String[] alternativeNames() {
    return this.alternatives;
  }

  @Override
  public boolean isClass() {
    return isClass;
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

}
