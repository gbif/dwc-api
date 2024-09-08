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

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public enum GbifTerm implements Term, AlternativeNames, Serializable {
  // row types

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/Description">GBIF Taxon Description</a>
   * extension row type.
   */
  Description(GbifTerm.GROUP_ROW_TYPE),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/Distribution">GBIF Species Distribution</a>
   * extension row type.
   */
  Distribution(GbifTerm.GROUP_ROW_TYPE),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/Identifier">GBIF Alternative Identifiers</a>
   * extension row type.
   */
  Identifier(GbifTerm.GROUP_ROW_TYPE),

  /**
   * The deprecated <a href="http://rs.gbif.org/terms/1.0/Image">GBIF Simple Images</a>
   * extension row type.
   */
  Image(GbifTerm.GROUP_ROW_TYPE, "Images"),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/References">GBIF Literature References</a>
   * extension row type.
   */
  Reference(GbifTerm.GROUP_ROW_TYPE, "References"),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/SpeciesProfile">GBIF Species Profile</a>
   * extension row type.
   */
  SpeciesProfile(GbifTerm.GROUP_ROW_TYPE, "SpeciesMiniProfile", "SpeciesInfo", "SpeciesData", "SpeciesFactsheet"),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/TypesAndSpecimen">GBIF Types and Specimen</a>
   * extension row type.
   */
  TypesAndSpecimen(GbifTerm.GROUP_ROW_TYPE, "Specimen", "Types", "TypeDesignation"),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/VernacularNames">GBIF Vernacular Names</a>
   * extension row type.
   */
  VernacularName(GbifTerm.GROUP_ROW_TYPE, "VernacularNames", "Vernacular", "Vernaculars"),

  /**
   * The <a href="http://rs.gbif.org/terms/1.0/Multimedia">GBIF Simple Multimedia</a>
   * extension row type.
   */
  Multimedia(GbifTerm.GROUP_ROW_TYPE),

  /**
   * The UUID key for the dataset registered in GBIF.
   */
  datasetKey(GbifTerm.GROUP_DATASET),

  /**
   * The ISO 3166 2-letter code of the country of the organization that publishes the dataset to which the occurrence belongs.
   */
  publishingCountry(GbifTerm.GROUP_DATASET),

  /**
   * Numerical, stable identifier assigned by GBIF to an Occurrence record.
   */
  gbifID(DwcTerm.GROUP_OCCURRENCE),

  /**
   * Timestamp of the last time the record was (re)interpreted by GBIF.
   */
  lastInterpreted(DwcTerm.GROUP_OCCURRENCE),

  /**
   * The uncertainty radius for the latitude and longitude in decimal degrees.
   */
  @Deprecated
  coordinateAccuracy(DwcTerm.GROUP_OCCURRENCE),

  /**
   * Elevation in metres above sea level (altitude).
   * <p>
   * The elevation is the absolute vertical position of the observed location (z-coordinate).
   * If depth is given or not will not impact the 3-dimensional position.
   * For example a location 100m below the surface of a lake in 2000m altitude has a depth of 100 and
   * an elevation of 1900.
   * </p>
   * <p>
   * If minimum and maximum values are given the elevation is calculated using the equation:
   * <code>(minimumElevationInMeters + maximumElevationInMeters) / 2</code>.
   * For consistency and ease of use GBIF decided to always use a value in metres plus its accuracy instead of
   * min/max values which are sometimes used in Darwin Core. See also depth & distanceAboveSurface.
   * </p>
   */
  elevation(DwcTerm.GROUP_LOCATION),

  /**
   * Elevation accuracy is the uncertainty for the elevation in metres.
   * <p>
   * The elevation accuracy is calculated using the equation: <code>(maximumElevationInMeters - minimumElevationInMeters) / 2</code>
   * in case a minimum and maximum verbatim value is given.
   * </p>
   */
  elevationAccuracy(DwcTerm.GROUP_LOCATION),

  /**
   * Depth in metres below the surface.
   * <p>
   * Complimentary and relative to elevation, depth indicates the distance to the earth surface, whether that is water
   * or ground.
   * For example a location 100m below the surface of a lake in 2000m altitude has a depth of 100 and
   * an elevation of 1900.
   * </p>
   * <p>
   * The depth is calculated using the equation: <code>(minimumDepthInMeters + maximumDepthInMeters) / 2</code>.
   * For consistency and ease of use GBIF decided to always use a value in meters plus it's accuracy instead of
   * min/max values which are sometimes used in Darwin Core. See also elevation & distanceAboveSurface.
   * </p>
   */
  depth(DwcTerm.GROUP_LOCATION),

  /**
   * Depth accuracy is the uncertainty for the depth in metres.
   * <p>
   * The depth accuracy is calculated using the equation: <code>(maximumDepthInMeters - minimumDepthInMeters) / 2</code>
   * in case a minimum and maximum verbatim value is given.
   * </p>
   */
  depthAccuracy(DwcTerm.GROUP_LOCATION),

  /**
   * Replaced by Darwin Core terms <a href="http://rs.tdwg.org/dwc/terms/minimumDistanceAboveSurfaceInMeters">dwc:minimumDistanceAboveSurfaceInMeters</a>
   * and <a href="http://rs.tdwg.org/dwc/terms/maximumDistanceAboveSurfaceInMeters">dwc:maximumDistanceAboveSurfaceInMeters</a>.
   */
  @Deprecated
  distanceAboveSurface(DwcTerm.GROUP_LOCATION),

  /**
   * Replaced by Darwin Core terms <a href="http://rs.tdwg.org/dwc/terms/minimumDistanceAboveSurfaceInMeters">dwc:minimumDistanceAboveSurfaceInMeters</a>
   * and <a href="http://rs.tdwg.org/dwc/terms/maximumDistanceAboveSurfaceInMeters">dwc:maximumDistanceAboveSurfaceInMeters</a>.
   */
  @Deprecated
  distanceAboveSurfaceAccuracy(DwcTerm.GROUP_LOCATION),

  /**
   * The distance in metres from a known centroid, e.g. a country centroid.
   */
  distanceFromCentroidInMeters(DwcTerm.GROUP_LOCATION),

  /**
   * Any issue found during processing and interpretation or the record.
   * See <a href="https://gbif.github.io/gbif-api/apidocs/org/gbif/api/vocabulary/OccurrenceIssue.html">OccurrenceIssue enumeration</a> for possible values.
   */
  issue(DwcTerm.GROUP_OCCURRENCE),

  /**
   * The media type given as Dublin Core type values, in particular StillImage, MovingImage or Sound.
   */
  mediaType(DwcTerm.GROUP_OCCURRENCE),

  // experimental Occurrence properties
  infraspecificMarker(DwcTerm.GROUP_OCCURRENCE),

  // Types and Specimen checklist extension
  typeDesignatedBy(DwcTerm.GROUP_OCCURRENCE),
  typeDesignationType(DwcTerm.GROUP_OCCURRENCE),

  /**
   * Boolean indicating that a valid latitude and longitude exists.
   * Even if existing it might still have issues, see hasGeospatialIssues and issue.
   */
  hasCoordinate(DwcTerm.GROUP_OCCURRENCE),

  /**
   * Boolean indicating that some spatial validation rule has not passed.
   * Primarily used to indicate that the record should not be displayed on a map.
   */
  hasGeospatialIssues(DwcTerm.GROUP_OCCURRENCE),


  /**
   * The checklist key.
   * <p>
   * The taxonomic checklist to use when search using a taxonKey, a higher taxonKey e.g. phylumKey
   * or when searching with a scientific name
   * </p>
   */
  checklistKey(DwcTerm.GROUP_TAXON),

  /**
   * The GBIF backbone key.
   * <p>
   * The best matching, accepted GBIF backbone name usage representing this occurrence.
   * In case the verbatim scientific name and its classification can only be matched to a higher rank this will
   * represent the lowest matching rank. In the worst case this could just be for example Animalia.
   * </p>
   * <p>
   * In contrast dwc:taxonID is only used for the source ids similar to occurrenceID
   * </p>
   */
  taxonKey(DwcTerm.GROUP_TAXON),


  /**
   * The GBIF backbone key of the accepted taxon key.
   */
  acceptedTaxonKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted kingdom in the GBIF backbone.
   */
  kingdomKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted phylum in the GBIF backbone.
   */
  phylumKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted class in the GBIF backbone.
   */
  classKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted order in the GBIF backbone.
   */
  orderKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted family in the GBIF backbone.
   */
  familyKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted genus in the GBIF backbone.
   */
  genusKey(DwcTerm.GROUP_TAXON),

  /**
   * The key to the accepted subgenus in the GBIF backbone.
   */
  subgenusKey(DwcTerm.GROUP_TAXON),

  /**
   * The backbone key to the accepted species.
   * In case the taxonKey is of a higher rank than species (e.g. genus) speciesKey is null.
   * In case taxonKey represents an infraspecific taxon the speciesKey points to the species
   * the infraspecies is classified as. In case of taxonKey being a species the speciesKey is the same.
   */
  speciesKey(DwcTerm.GROUP_TAXON),

  /**
   * The canonical name without authorship of the accepted species.
   */
  species(DwcTerm.GROUP_TAXON),

  // experimental Taxon properties
  canonicalName(DwcTerm.GROUP_TAXON),
  nameType(DwcTerm.GROUP_TAXON),

  /**
   * The scientific name the type associated acceptedNubKey.
   */
  acceptedScientificName(DwcTerm.GROUP_TAXON),

  /**
   * Scientific name as provided by the source.
   */
  verbatimScientificName(DwcTerm.GROUP_TAXON),

  /**
   * The scientific name the type status of this specimen applies to.
   * Term proposed in Darwin Core, but not yet ratified.
   */
  typifiedName(DwcTerm.GROUP_IDENTIFICATION),

  /**
   * The kind of protocol used when the record was last crawled by GBIF.
   * See <a href="http://gbif.github.io/gbif-api/apidocs/org/gbif/api/vocabulary/EndpointType.html">EndpointType enumeration</a> for possible values.
   */
  protocol(GbifTerm.GROUP_CRAWLING),

  /**
   * The date this record was last parsed from raw xml/json into a verbatim GBIF record.
   */
  lastParsed(GbifTerm.GROUP_CRAWLING),

  /**
   * The date this record was last crawled/harvested by GBIF from the endpoint.
   */
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
  appendixCITES(GbifTerm.GROUP_SPECIES_DISTRIBUTION_EXTENSION),
  numberOfOccurrences(GbifTerm.GROUP_SPECIES_DISTRIBUTION_EXTENSION),

  /** Boolean indicating if the publishing country is different to the location country. */
  repatriated(DwcTerm.GROUP_OCCURRENCE),

  /** Calculated relative organism quantity, based on organism and sample measure types */
  relativeOrganismQuantity(DwcTerm.GROUP_MATERIAL_SAMPLE),

  /** An identifier for a project to which a record belongs. */
  projectId(DwcTerm.GROUP_OCCURRENCE),

  /** Flag occurrence when associatedSequences/extension exists */
  isSequenced(DwcTerm.GROUP_OCCURRENCE),

  /** GBIF region based on country code */
  gbifRegion(DwcTerm.GROUP_LOCATION),

  /** GBIF region based on publishing country code */
  publishedByGbifRegion(DwcTerm.GROUP_LOCATION);

  private static final String PREFIX = "gbif";
  private static final String NS = "http://rs.gbif.org/terms/1.0/";
  private static final URI NS_URI = URI.create(NS);

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
    GROUP_VERNACULAR_NAME_EXTENSION, DwcTerm.GROUP_LOCATION};

  /**
   * Lists all GBIF terms in taxon group.
   */
  public static final GbifTerm[] TAXONOMIC_TERMS =
  {GbifTerm.checklistKey, GbifTerm.taxonKey, GbifTerm.acceptedTaxonKey, GbifTerm.kingdomKey, GbifTerm.phylumKey, GbifTerm.classKey,
   GbifTerm.orderKey, GbifTerm.familyKey, GbifTerm.genusKey, GbifTerm.subgenusKey, GbifTerm.speciesKey,
   GbifTerm.species, GbifTerm.canonicalName, GbifTerm.nameType, GbifTerm.acceptedScientificName,
   GbifTerm.verbatimScientificName};

  private final String groupName;
  private final boolean isDeprecated;
  public final String[] normAlts;

  GbifTerm(String groupName, String... alternatives) {
    this.groupName = groupName;
    this.normAlts = alternatives;
    boolean deprecatedAnnotationPresent = false;
    try {
      deprecatedAnnotationPresent = GbifTerm.class.getField(this.name()).isAnnotationPresent(Deprecated.class);
    } catch (NoSuchFieldException ignore) { }
    this.isDeprecated = deprecatedAnnotationPresent;
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
    return prefixedName();
  }

  @Override
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  /**
   *
   * @return true if the Term is annotated with @Deprecated.
   */
  public boolean isDeprecated(){
    return isDeprecated;
  }

}
