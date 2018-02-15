package org.gbif.dwc.terms;

/**
 * As per CoL Data Submission Format, ver. 4 of 29th September 2014: List of tables and fields
 */
public enum AcefTerm implements Term, AlternativeNames {
  ACCEPTED_SPECIES,
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
  ACCEPTED_INFRA_SPECIFIC_TAXA,
  ParentSpeciesID,
  InfraSpeciesEpithet("InfraSpecies"),
  InfraSpeciesAuthorString,
  InfraSpeciesMarker,
  InfraSpeciesURL,

  // SYNONYMS
  SYNONYMS,

  // COMMON NAMES
  COMMON_NAMES,
  CommonName,
  TransliteratedName,
  Country,
  Area,
  Language,
  ReferenceID,

  // DISTRIBUTION
  DISTRIBUTION,
  DistributionElement,
  StandardInUse,
  DistributionStatus,

  // REFERENCE
  REFERENCE,
  Author,
  Year,
  Title,
  Source,

  // NAME REFERENCES LINKS
  NAME_REFERENCES_LINKS,
  ReferenceType,

  // SOURCE DATABASE
  SOURCE_DATABASE,
  DatabaseFullName,
  DatabaseShortName,
  DatabaseVersion,
  ReleaseDate,
  AuthorsEditors,
  TaxonomicCoverage,
  GroupNameInEnglish,
  Abstract,
  Organisation,
  HomeURL,
  Coverage,
  Completeness,
  Confidence,
  LogoFileName,
  ContactPerson;

  public static final String NS = "http://rs.col.plus/terms/acef/";
  public static final String PREFIX = "acef";
  static final String[] PREFIXES = {NS, PREFIX + ":", "col:"};

  private final String[] alternatives;

  AcefTerm(String ... alternatives) {
    this.alternatives = alternatives;
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
  public String toString() {
    return PREFIX + ":" + name();
  }

  @Override
  public String[] alternativeNames() {
    return this.alternatives;
  }

  @Override
  public boolean isClass() {
    return name().equals(name().toUpperCase());
  }

}
