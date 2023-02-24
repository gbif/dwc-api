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
 * All Audiovisual Core terms with namespace http://rs.tdwg.org/ac/terms/.
 * See
 * <ul>
 *   <li>http://rs.tdwg.org/ac/doc/termlist/2022-02-23 verison implemented here</li>
 *   <li>https://ac.tdwg.org/termlist/</li>
 * </ul>
 */
public enum AcTerm implements Term, AlternativeNames {

  Multimedia, // this row type term is not defined in the AC termlist, but used in the GBIF extension and required for DwC-A
  RegionOfInterest,
  ServiceAccessPoint,

  accessURI,
  associatedObservationReference,
  associatedSpecimenReference,
  attributionLinkURL,
  attributionLogoURL,
  caption,
  captureDevice,
  commenter,
  commenterLiteral,
  comments,
  derivedFrom,
  digitizationDate,
  endTime,
  endTimestamp,
  frameRate,
  freqHigh,
  freqLow,
  fundingAttribution,
  furtherInformationURL,
  hashFunction,
  hashValue,
  hasROI,
  hasServiceAccessPoint,
  heightFrac,
  IDofContainingCollection,
  isROIOf,
  licenseLogoURL,
  licensingException,
  mediaDuration,
  mediaSpeed,
  metadataCreator,
  metadataCreatorLiteral,
  metadataLanguage,
  metadataLanguageLiteral,
  metadataProvider,
  metadataProviderLiteral,
  otherScientificName,
  physicalSetting,
  provider,
  providerID,
  providerLiteral,
  providerManagedID,
  radius,
  relatedResourceID,
  resourceCreationTechnique,
  reviewer,
  reviewerComments,
  reviewerLiteral,
  serviceExpectation,
  startTime,
  startTimestamp,
  subjectCategoryVocabulary,
  subjectOrientation,
  subjectOrientationLiteral,
  subjectPart,
  subjectPartLiteral,
  subtype,
  subtypeLiteral,
  tag,
  taxonCount,
  taxonCoverage,
  timeOfDay,
  variant,
  variantDescription,
  variantLiteral,
  widthFrac,
  xFrac,
  yFrac;

  private static final String PREFIX = "ac";
  private static final String NS = "http://rs.tdwg.org/ac/terms/";
  private static final URI NS_URI = URI.create(NS);

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
    return new String[]{};
  }

  @Override
  public boolean isClass() {
    return this == Multimedia || this == RegionOfInterest || this == ServiceAccessPoint;
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
