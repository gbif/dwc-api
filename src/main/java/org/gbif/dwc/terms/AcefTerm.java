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
  Source,
  Details("Detail"),

  // NAME REFERENCES
  NameReferencesLinks(true, "NameReferences", "NameReferenceLinks", "NameReferenceLink", "NameReferecesLinks"),
  ReferenceType,

  // SOURCE DATABASE
  SourceDatabase(true),
  DatabaseFullName(false, "DatabaseName"),
  DatabaseShortName,
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
  private static final String NS = "http://rs.col.plus/terms/acef/";
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

  /**
   * The full qualified term uri including the namespace.
   * For example http://rs.gbif.org/terms/1.0/taxonKey.
   *
   * @return full qualified term uri
   */
  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  /**
   * The simple term name without a namespace.
   * For example taxonKey.
   *
   * @return simple term name
   */
  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String prefixedName() {
    return PREFIX + ":" + name();
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
