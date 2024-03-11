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
 * All GGBN terms with namespace http://data.ggbn.org/schemas/ggbn/terms/.
 */
public enum GgbnTerm implements Term, AlternativeNames, Serializable {
  Amplification(true),
  Preservation(true),
  Loan(true),
  Cloning(true),
  Permit(true),
  GelImage(true),
  MaterialSample(true),
  Preparation(true),

  amplificationDate,
  amplificationMethod,
  amplificationStaff,
  amplificationSuccess,
  amplificationSuccessDetails,
  barcodeSequence,
  blocked,
  blockedUntil,
  BOLDProcessID(false),
  cloneStrain,
  cloningDate,
  cloningMethod,
  cloningStaff,
  concentration,
  concentrationUnit,
  consensusSequence,
  consensusSequenceChromatogramFileURI,
  consensusSequenceLength,
  DNADNAHybridization(false),
  DNAMeltingPoint(false),
  DNAThreshold(false),
  GC_content(false, "GC-content"),
  gelBuffer,
  gelConcentration,
  gelDuration,
  gelLadder,
  gelRemarks,
  gelStain,
  gelStainConcentration,
  gelVoltage,
  geneticAccessionNumber,
  geneticAccessionURI,
  haplotype,
  loanConditions,
  loanDate,
  loanDestination,
  loanIdentifier,
  marker,
  markerAccordance,
  markerSubfragment,
  materialSampleType,
  methodDeterminationConcentrationAndRatios,
  methodDeterminationWeight,
  percentAboveThreshold,
  permitStatus,
  permitStatusQualifier,
  permitText,
  permitType,
  permitURI,
  plasmid,
  preparationDate,
  preparationMaterials,
  preparationProcess,
  preparationType,
  preparedBy,
  preservationDateBegin,
  preservationTemperature,
  preservationType,
  primerNameForward,
  primerNameReverse,
  primerReferenceCitationForward,
  primerReferenceCitationReverse,
  primerReferenceLinkForward,
  primerReferenceLinkReverse,
  primerSequenceForward,
  primerSequenceReverse,
  purificationMethod,
  quality,
  qualityCheckDate,
  qualityRemarks,
  ratioOfAbsorbance260_230,
  ratioOfAbsorbance260_280,
  receivedFrom,
  sampleDesignation,
  sequence,
  stainingMethod,
  volume,
  volumeUnit,
  weight,
  weightUnit;

  private static final String PREFIX = "ggbn";
  private static final String NS = "http://data.ggbn.org/schemas/ggbn/terms/";
  private static final URI NS_URI = URI.create(NS);
  public final boolean isClass;
  public final String[] alternatives;

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String simpleName() {
    if (this == GC_content) {
      return "GC-content";
    }
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  /**
   * @return true if the GGBN term is defining a class instead of a property, e.g. Amplification
   */
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

  GgbnTerm() {
    this(false);
  }

  GgbnTerm(Boolean isClass, String... alternatives) {
    this.isClass = isClass;
    this.alternatives = alternatives;
  }
}
