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
 * As per CoL Data Submission Format, ver. 4 of 29th September 2014: List of tables and fields
 */
public enum AcefTerm implements Term, AlternativeNames {
  AcceptedSpecies(true),
  AcceptedTaxonID,
  Kingdom,
  Phylum,
  Class,
  Order,
  Superfamily,
  Family,
  Genus,
  SubGenusName,
  SpeciesEpithet,
  AuthorString,
  GSDNameStatus,
  Sp2000NameStatus,
  IsFossil,
  IsRecent,
  IsExtinct,
  HasPreHolocene,
  HasModern,
  LifeZone,
  AdditionalData,
  LTSSpecialist,
  LTSDate,
  SpeciesURL,
  GSDTaxonGUID,
  GSDNameGUID,

  //
  AcceptedInfraSpecificTaxa(true),
  ParentSpeciesID("parentID"),
  InfraSpeciesEpithet("InfraSpecies"),
  InfraSpeciesAuthorString,
  InfraSpeciesMarker,
  InfraSpeciesURL,

  // SYNONYMS
  Synonyms(true),
  ID,

  // COMMON NAMES
  CommonNames(true, "CommonName"),
  CommonName,
  TransliteratedName("TransliteratedNames"),
  Country,
  Area("Areas"),
  Language,
  ReferenceID,

  // DISTRIBUTION
  Distribution(true, "Distributions"),
  DistributionElement,
  StandardInUse,
  DistributionStatus,

  // REFERENCE
  Reference(true, "References"),
  Author("Authors"),
  Year,
  Title,
  Details("Detail", "Source"),

  // NAME REFERENCES
  NameReferencesLinks(true, "NameReferences", "NameReferenceLinks", "NameReferenceLink", "NameReferecesLinks"),
  ReferenceType,

  // SOURCE DATABASE
  SourceDatabase(true),
  DatabaseFullName,
  DatabaseShortName(false, "DatabaseName"),
  DatabaseVersion,
  ReleaseDate,
  AuthorsEditors,
  TaxonomicCoverage,
  GroupNameInEnglish,
  Abstract,
  Organisation(false, "Organization"),
  HomeURL,
  Coverage,
  Completeness,
  Confidence,
  LogoFileName,
  ContactPerson;

  private static final String PREFIX = "acef";
  private static final String NS = "https://terms.catalogueoflife.org/acef/";
  private static final URI NS_URI = URI.create(NS);

  private final boolean isClass;
  private final String[] alternatives;

  AcefTerm(boolean isClass, String ... alternatives) {
    this.alternatives = alternatives;
    this.isClass = isClass;
  }

  AcefTerm(String ... alternatives) {
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
