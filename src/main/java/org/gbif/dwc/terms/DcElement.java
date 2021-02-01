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
 * All Dublin Core terms with namespace http://purl.org/dc/elements/1.1/. All terms exist as DcTerm under a different
 * namespace, but DcElement values are allowed to be free text, whereas DcTerm values must be a member of a specific
 * class and therefore given by URI.
 */
public enum DcElement implements Term, AlternativeNames {
  contributor,
  coverage,
  creator,
  date,
  description,
  format,
  identifier,
  language,
  publisher,
  relation,
  rights,
  source,
  subject,
  title,
  type;

  private static final String PREFIX = "dc";
  private static final String NS = "http://purl.org/dc/elements/1.1/";
  private static final URI NS_URI = URI.create(NS);

  public final String[] alternatives;

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  @Override
  public boolean isClass() {
    return false;
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  DcElement(String... alternatives) {
    this.alternatives = alternatives;
  }
}
