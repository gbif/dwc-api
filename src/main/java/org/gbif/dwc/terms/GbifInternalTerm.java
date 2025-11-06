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
 * Internal GBIF terms used for processing, fragmenting, crawling, ...
 * These are not exposed in downloads or the public API.
 */
public enum GbifInternalTerm implements Term, AlternativeNames {
  identifierCount,
  crawlId,
  fragment,
  fragmentHash,
  fragmentCreated,
  xmlSchema,
  publishingOrgKey,
  unitQualifier,
  networkKey,
  installationKey,
  programmeAcronym,
  collectionKey,
  institutionKey,
  hostingOrganizationKey,
  isInCluster,
  dwcaExtension,
  datasetTitle,

  // Lower and upper endpoints for eventDates
  eventDateGte,
  eventDateLte,

  //Experimental Terms for Events indexing
  parentEventGbifId,

  // multi taxonomy classifications
  taxonomicStatuses,
  classifications,
  classificationDetails,

  // humboldt
  humboldtEventDurationValueInMinutes;

  private static final String PREFIX = "gbint";
  private static final String NS = "http://rs.gbif.org/terms/internal/";
  private static final URI NS_URI = URI.create(NS);

  private static final String[] EMPTY = new String[0];

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
    return EMPTY;
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
