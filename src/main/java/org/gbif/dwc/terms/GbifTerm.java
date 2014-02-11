package org.gbif.dwc.terms;

public enum GbifTerm implements Term, AlternativeNames {
  // row types
  Description,
  Distribution,
  Identifier,
  Image("Images"),
  Reference("References"),
  SpeciesProfile("SpeciesMiniProfile", "SpeciesInfo", "SpeciesData", "SpeciesFactsheet"),
  TypesAndSpecimen("Specimen", "Types", "TypeDesignation"),
  VernacularName("VernacularNames", "Vernacular", "Vernaculars"),

  // Dataset properties
  datasetKey,

  // Occurrence properties
  lastInterpreted,
  lastParsed,
  lastCrawled,
  publishingCountry,
  coordinateAccuracy,
  elevation,
  elevationAccuracy,
  depth,
  depthAccuracy,
  distanceAboveSurface,
  distanceAboveSurfaceAccuracy,
  kingdomKey,
  phylumKey,
  classKey,
  orderKey,
  familyKey,
  genusKey,
  speciesKey,
  unitQualifier,
  issue,

  // species profile checklist extension
  isMarine,
  isTerrestrial,
  isFreshwater,
  isHybrid,
  isExtinct,
  livingPeriod("timePeriod"),
  lifeForm,
  ageInDays,
  sizeInMillimeter,
  massInGram("weightInGram"),

  // vernacular name checklist extension
  organismPart,
  isPlural,
  isPreferredName,

  // distribution checklist extension
  appendixCITES,

  // types checklist extension
  typeDesignatedBy,
  typeDesignationType,

  // experimental
  canonicalName,
  nameType,
  verbatimLabel,
  infraspecificMarker;

  public static final String NS = "http://rs.gbif.org/terms/1.0/";
  public static final String PREFIX = "gbif";
  static final String[] PREFIXES = {NS, PREFIX + ":"};

  public final String[] normAlts;

  GbifTerm(String... alternatives) {
    normAlts = alternatives;
  }

  @Override
  public String qualifiedName() {
    return NS + name();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return normAlts;
  }

  @Override
  public String toString() {
    return PREFIX + ":" + name();
  }

  /**
   * @return true if the term is defining a class instead of a property, e.g. VernacularName
   */
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

}
