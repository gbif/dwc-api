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
 * All Audubon Core terms with namespace http://rs.tdwg.org/ac/terms/.
 * See
 * <ul>
 *   <li>http://terms.tdwg.org/wiki/Audubon_Core_Term_List</li>
 *   <li>http://terms.tdwg.org/wiki/Audubon_Core</li>
 * </ul>
 */
public enum AcTerm implements Term, AlternativeNames {

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
  fundingAttribution,
  furtherInformationURL,
  hasServiceAccessPoint,
  hashFunction,
  hashValue,
  IDofContainingCollection,
  licenseLogoURL,
  licensingException,
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
// we leave this AC term out as the simple name exists already in DwC!
//  relatedResourceID,
  resourceCreationTechnique,
  reviewer,
  reviewerComments,
  reviewerLiteral,
  serviceExpectation,
  subjectCategoryVocabulary,
  subjectOrientation,
  subjectPart,
  subtype,
  subtypeLiteral,
  tag,
  taxonCount,
  taxonCoverage,
  timeOfDay,
  variant,
  variantDescription,
  variantLiteral;

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
}
