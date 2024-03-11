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
 * All Germplasm terms with namespace http://purl.org/germplasm/germplasmTerm#.
 */
public enum GermplasmTerm implements Term, AlternativeNames, Serializable {
  GermplasmAccession,
  MeasurementScore,
  MeasurementTrait,
  MeasurementTrial,

  acquisitionDate,
  acquisitionID,
  acquisitionRemarks,
  acquisitionSource,
  ancestralData,
  biologicalStatus,
  breedingCountry,
  breedingCountryCode,
  breedingID,
  breedingIdentifier,
  breedingInstitute,
  breedingInstituteID,
  breedingPerson,
  breedingRemarks,
  breedingYear,
  collectingInstituteID,
  donorInstitute,
  donorInstituteID,
  donorsID,
  donorsIdentifier,
  germplasmID,
  germplasmIdentifier,
  measurementByInstituteID,
  measurementGrowthStage,
  measurementTrailID,
  measurementTrailIdentifier,
  measurementTrailRemarks,
  measurementTrailReport,
  measurementTrailYear,
  measurementTraitCategory,
  measurementTraitID,
  measurementTraitIdentifier,
  measurementTraitName,
  measurementTraitRemarks,
  measurementTraitScale,
  measurementTraitSource,
  measurementTrialID,
  measurementTrialIdentifier,
  mlsStatus,
  purdyPedigree,
  safetyDuplicationDate,
  safetyDuplicationID,
  safetyDuplicationInstitute,
  safetyDuplicationInstituteID,
  safetyDuplicationRemarks,
  storageCondition("http://purl.org/germplasm/germplasmType#storageCondition"),
  treatyOrRegulationGoverningBody,
  treatyOrRegulationID,
  treatyOrRegulationName;

  private static final String PREFIX = "germplasm";
  private static final String NS = "http://purl.org/germplasm/germplasmTerm#";
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
   * @return true if the germplasm term is defining a class instead of a property, e.g. GermplasmAccession
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

  GermplasmTerm(String... alternatives) {
    this.alternatives = alternatives;
  }
}
