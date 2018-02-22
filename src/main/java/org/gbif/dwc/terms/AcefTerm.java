package org.gbif.dwc.terms;

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
  TransliteratedName,
  Country,
  Area,
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
  NameReferencesLinks,

  // NAME REFERENCES
  NameReferences(true, "NameReferenceLinks", "NameReferencesLinks", "NameReferecesLinks"),
  ReferenceType,

  // SOURCE DATABASE
  SourceDatabase(true),
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
  static final String[] PREFIXES = {PREFIX + ":", "col:"};

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

}
