package org.gbif.dwc.terms;

import java.util.ArrayList;
import java.util.List;

public enum GbifTerm implements Term, AlternativeNames {
  // row types
  Description(GbifTerm.GROUP_ROW_TYPE),
  Distribution(GbifTerm.GROUP_ROW_TYPE),
  Identifier(GbifTerm.GROUP_ROW_TYPE),
  Image(GbifTerm.GROUP_ROW_TYPE, "Images"),
  Reference(GbifTerm.GROUP_ROW_TYPE, "References"),
  SpeciesProfile(GbifTerm.GROUP_ROW_TYPE, "SpeciesMiniProfile", "SpeciesInfo", "SpeciesData", "SpeciesFactsheet"),
  TypesAndSpecimen(GbifTerm.GROUP_ROW_TYPE, "Specimen", "Types", "TypeDesignation"),
  VernacularName(GbifTerm.GROUP_ROW_TYPE, "VernacularNames", "Vernacular", "Vernaculars"),
  Multimedia(GbifTerm.GROUP_ROW_TYPE),

  // Dataset properties
  datasetKey(GbifTerm.GROUP_DATASET),
  publishingCountry(GbifTerm.GROUP_DATASET),

  // Occurrence properties
  gbifID(DwcTerm.GROUP_OCCURRENCE),
  lastInterpreted(DwcTerm.GROUP_OCCURRENCE),
  coordinateAccuracy(DwcTerm.GROUP_OCCURRENCE),
  elevation(DwcTerm.GROUP_OCCURRENCE),
  elevationAccuracy(DwcTerm.GROUP_OCCURRENCE),
  depth(DwcTerm.GROUP_OCCURRENCE),
  depthAccuracy(DwcTerm.GROUP_OCCURRENCE),
  distanceAboveSurface(DwcTerm.GROUP_OCCURRENCE),
  distanceAboveSurfaceAccuracy(DwcTerm.GROUP_OCCURRENCE),
  issue(DwcTerm.GROUP_OCCURRENCE),
  mediaType(DwcTerm.GROUP_OCCURRENCE),
  // experimental Occurrence properties
  verbatimLabel(DwcTerm.GROUP_OCCURRENCE),
  infraspecificMarker(DwcTerm.GROUP_OCCURRENCE),
  // Types and Specimen checklist extension
  typeDesignatedBy(DwcTerm.GROUP_OCCURRENCE),
  typeDesignationType(DwcTerm.GROUP_OCCURRENCE),
  // booleans for geospatial issues
  hasCoordinate(DwcTerm.GROUP_OCCURRENCE),
  hasGeospatialIssues(DwcTerm.GROUP_OCCURRENCE),

  // Taxon properties
  /**
   * The GBIF backbone key.
   * taxonID is only used for the source ids similar to occurrenceID
   */
  taxonKey(DwcTerm.GROUP_TAXON),
  kingdomKey(DwcTerm.GROUP_TAXON),
  phylumKey(DwcTerm.GROUP_TAXON),
  classKey(DwcTerm.GROUP_TAXON),
  orderKey(DwcTerm.GROUP_TAXON),
  familyKey(DwcTerm.GROUP_TAXON),
  genusKey(DwcTerm.GROUP_TAXON),
  subgenusKey(DwcTerm.GROUP_TAXON),
  speciesKey(DwcTerm.GROUP_TAXON),
  species(DwcTerm.GROUP_TAXON),
  // experimental Taxon properties
  canonicalName(DwcTerm.GROUP_TAXON),
  nameType(DwcTerm.GROUP_TAXON),
  genericName(DwcTerm.GROUP_TAXON),    // proposed as dwc, but not yet ratified
  typifiedName(DwcTerm.GROUP_IDENTIFICATION),    // proposed as dwc, but not yet ratified

  // GBIF Crawling
  protocol(GbifTerm.GROUP_CRAWLING),
  lastParsed(GbifTerm.GROUP_CRAWLING),
  lastCrawled(GbifTerm.GROUP_CRAWLING),

  // Species Profile checklist extension
  isMarine(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  isTerrestrial(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  isFreshwater(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  isHybrid(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  isExtinct(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  livingPeriod(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION, "timePeriod"),
  lifeForm(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  ageInDays(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  sizeInMillimeter(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION),
  massInGram(GbifTerm.GROUP_SPECIES_PROFILE_EXTENSION, "weightInGram"),

  // Vernacular Name checklist extension
  organismPart(GbifTerm.GROUP_VERNACULAR_NAME_EXTENSION),
  isPlural(GbifTerm.GROUP_VERNACULAR_NAME_EXTENSION),
  isPreferredName(GbifTerm.GROUP_VERNACULAR_NAME_EXTENSION),

  // Distribution checklist extension
  appendixCITES(GbifTerm.GROUP_SPECIES_DISTRIBUTION_EXTENSION);

  public static final String NS = "http://rs.gbif.org/terms/1.0/";
  public static final String PREFIX = "gbif";
  static final String[] PREFIXES = {NS, PREFIX + ":"};
  public static final String GROUP_CRAWLING = "Crawling";
  public static final String GROUP_DATASET = "Dataset";
  public static final String GROUP_ROW_TYPE = "RowType";
  public static final String GROUP_SPECIES_DISTRIBUTION_EXTENSION = "SpeciesDistribution";
  public static final String GROUP_SPECIES_PROFILE_EXTENSION = "SpeciesProfile";
  public static final String GROUP_VERNACULAR_NAME_EXTENSION = "VernacularName";

  /**
   * Lists all GBIF term groups.
   */
  public static final String[] GROUPS = {GROUP_CRAWLING, GROUP_DATASET, DwcTerm.GROUP_OCCURRENCE, GROUP_ROW_TYPE,
    GROUP_SPECIES_DISTRIBUTION_EXTENSION, GROUP_SPECIES_PROFILE_EXTENSION, DwcTerm.GROUP_TAXON,
    GROUP_VERNACULAR_NAME_EXTENSION};

  /**
   * Lists all GBIF terms in taxon group.
   */
  public static final GbifTerm[] TAXONOMIC_TERMS =
  {GbifTerm.taxonKey, GbifTerm.kingdomKey, GbifTerm.phylumKey, GbifTerm.classKey, GbifTerm.orderKey,
    GbifTerm.familyKey, GbifTerm.genusKey, GbifTerm.subgenusKey, GbifTerm.speciesKey, GbifTerm.species,
    GbifTerm.canonicalName, GbifTerm.nameType, GbifTerm.genericName};

  private final String groupName;
  public final String[] normAlts;

  private GbifTerm(String groupName, String... alternatives) {
    this.groupName = groupName;
    normAlts = alternatives;
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

  /**
   * Array of alternative simple names in use for the term.
   *
   * @return simple term name
   */
  @Override
  public String[] alternativeNames() {
    return normAlts;
  }

  /**
   * The optional group the term is grouped in.
   * For example Occurrence, Taxon, etc.
   */
  public String getGroup() {
    return groupName;
  }


  /**
   * List all terms that belong to a given group.
   *
   * @param group the group to list terms for
   * @return the list of GBIF terms in the given group
   */
  public static List<GbifTerm> listByGroup(String group) {
    List<GbifTerm> terms = new ArrayList<GbifTerm>();
    for (GbifTerm t : GbifTerm.values()) {
      if (t.getGroup().equalsIgnoreCase(group)) {
        terms.add(t);
      }
    }
    return terms;
  }

  @Override
  public String toString() {
    return PREFIX + ":" + name();
  }

  /**
   * @return true if the term is defining a class or row type instead of a property, e.g. VernacularName
   */
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

}
