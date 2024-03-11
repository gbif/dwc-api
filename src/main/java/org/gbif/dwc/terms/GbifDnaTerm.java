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

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Terms defined in the DNA extension.
 *
 * They aren't using the versioned namespace, which ought to be corrected in a future edition of the extension.
 */
public enum GbifDnaTerm implements Term, AlternativeNames, Serializable {
  dna_sequence,
  pcr_primer_forward,
  pcr_primer_name_forward,
  pcr_primer_name_reverse,
  pcr_primer_reference,
  pcr_primer_reverse;

  private static final String PREFIX = "gbifdna";
  private static final String NS = "http://rs.gbif.org/terms/";
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

  GbifDnaTerm(String... alternatives) {
    this.alternatives = alternatives;
  }
}
