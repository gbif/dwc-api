/*
 * Copyright 2024 Global Biodiversity Information Facility (GBIF)
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

/**
 * All Chronometric terms with namespace http://rs.tdwg.org/chrono/terms/.
 */
public enum ChronoTerm implements Term, AlternativeNames, Serializable {
  ChronometricAge,

  chronometricAgeConversionProtocol,
  chronometricAgeDeterminedBy,
  chronometricAgeDeterminedDate,
  chronometricAgeID,
  chronometricAgeProtocol,
  chronometricAgeReferences,
  chronometricAgeRemarks,
  chronometricAgeUncertaintyInYears,
  chronometricAgeUncertaintyMethod,
  earliestChronometricAge,
  // Misspelling in https://rs.gbif.org/extension/gbif/1.0/dna_derived_data_2022-02-23.xml
  earliestChronometricAgeReferenceSystem("earlliestChronometricAgeReferenceSystem"),
  latestChronometricAge,
  // Misspelling in https://rs.gbif.org/extension/gbif/1.0/dna_derived_data_2022-02-23.xml
  latestChronometricAgeReferenceSystem("lattestChronometricAgeReferenceSystem"),
  materialDated,
  materialDatedID,
  materialDatedRelationship,
  uncalibratedChronometricAge,
  verbatimChronometricAge;

  private static final String PREFIX = "chrono";
  private static final String NS = "http://rs.tdwg.org/chrono/terms/";
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

  /**
   * @return true if the chronometric term is defining a class instead of a property, e.g. ChronometricAge.
   */
  @Override
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  ChronoTerm(String... alternatives) {
    this.alternatives = alternatives;
  }
}
