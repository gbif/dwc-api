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
import java.util.Arrays;
import java.util.List;

/**
 * All Darwin Core terms with namespace http://rs.tdwg.org/dwc/terms/ as an
 * enumeration with alternative term names found sometimes in data.
 * Old, deprecated terms are kept but marked as such.
 */
public enum DwcTerm implements Term, AlternativeNames, Serializable {

  /**
   * CLASS TERMS
   * Listed in the order given on the Darwin Core Quick Reference Guide.
   * @see <a href="http://rs.tdwg.org/dwc/terms/index.htm#theterms">DwC Quick Reference Guide</a>
   * Location is not on this list because it is a term in the dcterm namespace.
   */
  Occurrence(DwcTerm.GROUP_OCCURRENCE, "DarwinCore", "SimpleDarwinCore"),
  Organism(DwcTerm.GROUP_ORGANISM),
  MaterialSample(DwcTerm.GROUP_MATERIAL_SAMPLE),
  Event(DwcTerm.GROUP_EVENT),
  GeologicalContext(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  Identification(DwcTerm.GROUP_IDENTIFICATION),
  Taxon(DwcTerm.GROUP_TAXON),
  MeasurementOrFact(DwcTerm.GROUP_MEASUREMENTORFACT),
  ResourceRelationship(DwcTerm.GROUP_RESOURCERELATIONSHIP),

  /**
   * PROPERTY TERMS
   * Listed in the order given on the Darwin Core Quick Reference Guide.
   * @see <a href="http://rs.tdwg.org/dwc/terms/index.htm#theterms">DwC Quick Reference Guide</a>
   */
  institutionID(DwcTerm.GROUP_RECORD),
  collectionID(DwcTerm.GROUP_RECORD),
  datasetID(DwcTerm.GROUP_RECORD),
  institutionCode(DwcTerm.GROUP_RECORD),
  collectionCode(DwcTerm.GROUP_RECORD),
  datasetName(DwcTerm.GROUP_RECORD),
  ownerInstitutionCode(DwcTerm.GROUP_RECORD),
  basisOfRecord(DwcTerm.GROUP_RECORD),
  informationWithheld(DwcTerm.GROUP_RECORD),
  dataGeneralizations(DwcTerm.GROUP_RECORD),
  dynamicProperties(DwcTerm.GROUP_RECORD),

  occurrenceID(DwcTerm.GROUP_OCCURRENCE),
  catalogNumber(DwcTerm.GROUP_OCCURRENCE, "catalogNumberNumeric"),
  recordNumber(DwcTerm.GROUP_OCCURRENCE, "collectorNumber"),
  recordedBy(DwcTerm.GROUP_OCCURRENCE, "collector"),
  recordedByID(DwcTerm.GROUP_OCCURRENCE, "gbif:recordedByID", "http://rs.gbif.org/terms/1.0/recordedByID"),
  individualCount(DwcTerm.GROUP_OCCURRENCE),
  organismQuantity(DwcTerm.GROUP_OCCURRENCE),
  organismQuantityType(DwcTerm.GROUP_OCCURRENCE),
  sex(DwcTerm.GROUP_OCCURRENCE),
  @Vocabulary lifeStage(DwcTerm.GROUP_OCCURRENCE),
  reproductiveCondition(DwcTerm.GROUP_OCCURRENCE),
  behavior(DwcTerm.GROUP_OCCURRENCE),
  @Vocabulary establishmentMeans(DwcTerm.GROUP_OCCURRENCE),
  @Vocabulary degreeOfEstablishment(DwcTerm.GROUP_OCCURRENCE),
  @Vocabulary pathway(DwcTerm.GROUP_OCCURRENCE),
  georeferenceVerificationStatus(DwcTerm.GROUP_OCCURRENCE),
  occurrenceStatus(DwcTerm.GROUP_OCCURRENCE),
  preparations(DwcTerm.GROUP_OCCURRENCE),
  disposition(DwcTerm.GROUP_OCCURRENCE),
  associatedMedia(DwcTerm.GROUP_OCCURRENCE),
  associatedOccurrences(DwcTerm.GROUP_OCCURRENCE),
  associatedReferences(DwcTerm.GROUP_OCCURRENCE),
  associatedSequences(DwcTerm.GROUP_OCCURRENCE),
  associatedTaxa(DwcTerm.GROUP_OCCURRENCE),
  otherCatalogNumbers(DwcTerm.GROUP_OCCURRENCE),
  occurrenceRemarks(DwcTerm.GROUP_OCCURRENCE),

  organismID(DwcTerm.GROUP_ORGANISM, "individualID"),
  organismName(DwcTerm.GROUP_ORGANISM),
  organismScope(DwcTerm.GROUP_ORGANISM),
  associatedOrganisms(DwcTerm.GROUP_ORGANISM),
  previousIdentifications(DwcTerm.GROUP_ORGANISM),
  organismRemarks(DwcTerm.GROUP_ORGANISM),

  materialSampleID(DwcTerm.GROUP_MATERIAL_SAMPLE),

  eventID(DwcTerm.GROUP_EVENT),
  parentEventID(DwcTerm.GROUP_EVENT),
  fieldNumber(DwcTerm.GROUP_EVENT),
  eventDate(DwcTerm.GROUP_EVENT, "earliestDateCollected", "latestDateCollected"),
  eventTime(DwcTerm.GROUP_EVENT),
  startDayOfYear(DwcTerm.GROUP_EVENT),
  endDayOfYear(DwcTerm.GROUP_EVENT),
  year(DwcTerm.GROUP_EVENT),
  month(DwcTerm.GROUP_EVENT),
  day(DwcTerm.GROUP_EVENT),
  verbatimEventDate(DwcTerm.GROUP_EVENT),
  habitat(DwcTerm.GROUP_EVENT),
  samplingProtocol(DwcTerm.GROUP_EVENT),
  sampleSizeValue(DwcTerm.GROUP_EVENT),
  sampleSizeUnit(DwcTerm.GROUP_EVENT),
  samplingEffort(DwcTerm.GROUP_EVENT),
  fieldNotes(DwcTerm.GROUP_EVENT),
  eventRemarks(DwcTerm.GROUP_EVENT),

  locationID(DwcTerm.GROUP_LOCATION),
  higherGeographyID(DwcTerm.GROUP_LOCATION),
  higherGeography(DwcTerm.GROUP_LOCATION),
  continent(DwcTerm.GROUP_LOCATION),
  waterBody(DwcTerm.GROUP_LOCATION),
  islandGroup(DwcTerm.GROUP_LOCATION),
  island(DwcTerm.GROUP_LOCATION),
  country(DwcTerm.GROUP_LOCATION),
  countryCode(DwcTerm.GROUP_LOCATION),
  stateProvince(DwcTerm.GROUP_LOCATION, "state", "province"),
  county(DwcTerm.GROUP_LOCATION),
  municipality(DwcTerm.GROUP_LOCATION, "city"),
  locality(DwcTerm.GROUP_LOCATION),
  verbatimLocality(DwcTerm.GROUP_LOCATION),
  minimumElevationInMeters(DwcTerm.GROUP_LOCATION),
  maximumElevationInMeters(DwcTerm.GROUP_LOCATION),
  verbatimElevation(DwcTerm.GROUP_LOCATION),
  verticalDatum(DwcTerm.GROUP_LOCATION),
  minimumDepthInMeters(DwcTerm.GROUP_LOCATION),
  maximumDepthInMeters(DwcTerm.GROUP_LOCATION),
  verbatimDepth(DwcTerm.GROUP_LOCATION),
  minimumDistanceAboveSurfaceInMeters(DwcTerm.GROUP_LOCATION),
  maximumDistanceAboveSurfaceInMeters(DwcTerm.GROUP_LOCATION),
  locationAccordingTo(DwcTerm.GROUP_LOCATION),
  locationRemarks(DwcTerm.GROUP_LOCATION),
  decimalLatitude(DwcTerm.GROUP_LOCATION, "latitude"),
  decimalLongitude(DwcTerm.GROUP_LOCATION, "longitude"),
  geodeticDatum(DwcTerm.GROUP_LOCATION, "datum", "horizontaldatum"),
  coordinateUncertaintyInMeters(DwcTerm.GROUP_LOCATION),
  coordinatePrecision(DwcTerm.GROUP_LOCATION),
  pointRadiusSpatialFit(DwcTerm.GROUP_LOCATION),
  verbatimCoordinates(DwcTerm.GROUP_LOCATION),
  verbatimLatitude(DwcTerm.GROUP_LOCATION),
  verbatimLongitude(DwcTerm.GROUP_LOCATION),
  verbatimCoordinateSystem(DwcTerm.GROUP_LOCATION),
  verbatimSRS(DwcTerm.GROUP_LOCATION),
  footprintWKT(DwcTerm.GROUP_LOCATION),
  footprintSRS(DwcTerm.GROUP_LOCATION),
  footprintSpatialFit(DwcTerm.GROUP_LOCATION),
  georeferencedBy(DwcTerm.GROUP_LOCATION),
  georeferencedDate(DwcTerm.GROUP_LOCATION),
  georeferenceProtocol(DwcTerm.GROUP_LOCATION),
  georeferenceSources(DwcTerm.GROUP_LOCATION),
  georeferenceRemarks(DwcTerm.GROUP_LOCATION),

  geologicalContextID(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEonOrLowestEonothem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEonOrHighestEonothem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEraOrLowestErathem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEraOrHighestErathem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestPeriodOrLowestSystem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestPeriodOrHighestSystem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEpochOrLowestSeries(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEpochOrHighestSeries(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestAgeOrLowestStage(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestAgeOrHighestStage(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  lowestBiostratigraphicZone(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  highestBiostratigraphicZone(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  lithostratigraphicTerms(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  group(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  formation(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  member(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  bed(DwcTerm.GROUP_GEOLOGICALCONTEXT),

  identificationID(DwcTerm.GROUP_IDENTIFICATION),
  verbatimIdentification(DwcTerm.GROUP_IDENTIFICATION),
  identificationQualifier(DwcTerm.GROUP_IDENTIFICATION),
  typeStatus(DwcTerm.GROUP_IDENTIFICATION),
  identifiedBy(DwcTerm.GROUP_IDENTIFICATION),
  identifiedByID(DwcTerm.GROUP_IDENTIFICATION, "gbif:identifiedByID", "http://rs.gbif.org/terms/1.0/identifiedByID"),
  dateIdentified(DwcTerm.GROUP_IDENTIFICATION),
  identificationReferences(DwcTerm.GROUP_IDENTIFICATION),
  identificationVerificationStatus(DwcTerm.GROUP_IDENTIFICATION),
  identificationRemarks(DwcTerm.GROUP_IDENTIFICATION),

  taxonID(DwcTerm.GROUP_TAXON, "nameUsageID"),
  scientificNameID(DwcTerm.GROUP_TAXON, "nameID"),
  acceptedNameUsageID(DwcTerm.GROUP_TAXON, "acceptedTaxonID"),
  parentNameUsageID(DwcTerm.GROUP_TAXON, "higherNameUsageID", "parentTaxonID"),
  originalNameUsageID(DwcTerm.GROUP_TAXON, "originalNameID", "basionymID"),
  nameAccordingToID(DwcTerm.GROUP_TAXON, "taxonAccordingToID"),
  namePublishedInID(DwcTerm.GROUP_TAXON),
  taxonConceptID(DwcTerm.GROUP_TAXON),
  scientificName(DwcTerm.GROUP_TAXON),
  acceptedNameUsage(DwcTerm.GROUP_TAXON, "acceptedTaxon"),
  parentNameUsage(DwcTerm.GROUP_TAXON, "parentTaxon", "higherTaxon", "higherNameUsage"),
  originalNameUsage(DwcTerm.GROUP_TAXON, "originalName", "originalTaxon", "basionym"),
  nameAccordingTo(DwcTerm.GROUP_TAXON, "taxonAccordingTo"),
  namePublishedIn(DwcTerm.GROUP_TAXON),
  namePublishedInYear(DwcTerm.GROUP_TAXON),
  higherClassification(DwcTerm.GROUP_TAXON),
  kingdom(DwcTerm.GROUP_TAXON),
  phylum(DwcTerm.GROUP_TAXON),
  /**
   * The taxonomic class.
   * The real Darwin Core term is class, but as java does not allow this name we use a variation instead.
   */
  class_(DwcTerm.GROUP_TAXON, "class"),
  order(DwcTerm.GROUP_TAXON),
  family(DwcTerm.GROUP_TAXON),
  subfamily(DwcTerm.GROUP_TAXON),
  genus(DwcTerm.GROUP_TAXON),
  genericName(DwcTerm.GROUP_TAXON, "gbif:genericName", "http://rs.gbif.org/terms/1.0/genericName"),
  subgenus(DwcTerm.GROUP_TAXON),
  infragenericEpithet(DwcTerm.GROUP_TAXON),
  specificEpithet(DwcTerm.GROUP_TAXON),
  infraspecificEpithet(DwcTerm.GROUP_TAXON),
  cultivarEpithet(DwcTerm.GROUP_TAXON),
  taxonRank(DwcTerm.GROUP_TAXON, "rank"),
  verbatimTaxonRank(DwcTerm.GROUP_TAXON),
  scientificNameAuthorship(DwcTerm.GROUP_TAXON),
  vernacularName(DwcTerm.GROUP_TAXON),
  nomenclaturalCode(DwcTerm.GROUP_TAXON),
  taxonomicStatus(DwcTerm.GROUP_TAXON),
  nomenclaturalStatus(DwcTerm.GROUP_TAXON),
  taxonRemarks(DwcTerm.GROUP_TAXON, "taxonRemark"),

  measurementID(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementType(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementValue(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementAccuracy(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementUnit(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementDeterminedBy(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementDeterminedDate(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementMethod(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementRemarks(DwcTerm.GROUP_MEASUREMENTORFACT),

  resourceRelationshipID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  resourceID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipOfResourceID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relatedResourceID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipOfResource(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipAccordingTo(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipEstablishedDate(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipRemarks(DwcTerm.GROUP_RESOURCERELATIONSHIP);

  private static final String PREFIX = "dwc";
  private static final String NS = "http://rs.tdwg.org/dwc/terms/";
  private static final URI NS_URI = URI.create(NS);

  public static final String GROUP_RECORD = "Record";
  public static final String GROUP_OCCURRENCE = "Occurrence";
  public static final String GROUP_ORGANISM = "Organism";
  public static final String GROUP_MATERIAL_SAMPLE = "MaterialSample";
  public static final String GROUP_EVENT = "Event";
  public static final String GROUP_LOCATION = "Location";
  public static final String GROUP_GEOLOGICALCONTEXT = "GeologicalContext";
  public static final String GROUP_IDENTIFICATION = "Identification";
  public static final String GROUP_TAXON = "Taxon";
  public static final String GROUP_MEASUREMENTORFACT = "MeasurementOrFact";
  public static final String GROUP_RESOURCERELATIONSHIP = "ResourceRelationship";

  /**
   * Lists all term groups in the order given on the Darwin Core Quick Reference Guide.
   * @see <a href="http://rs.tdwg.org/dwc/terms/index.htm#theterms">DwC Quick Reference Guide</a>
   */
  public static final String[] GROUPS =
    {GROUP_RECORD, GROUP_OCCURRENCE, GROUP_ORGANISM, GROUP_EVENT, GROUP_LOCATION,
	  GROUP_GEOLOGICALCONTEXT, GROUP_IDENTIFICATION, GROUP_TAXON,
	  GROUP_MEASUREMENTORFACT, GROUP_RESOURCERELATIONSHIP};

  public static final DwcTerm[] TAXONOMIC_TERMS = Arrays.stream(values())
                                                        .filter(t -> !t.isClass() && t.getGroup().equals(GROUP_TAXON))
                                                        .toArray(DwcTerm[]::new);

  /**
   * List of all higher rank terms in dwc, ordered by rank and starting with kingdom.
   */
  public static final DwcTerm[] HIGHER_RANKS =
    {DwcTerm.kingdom, DwcTerm.phylum, DwcTerm.class_, DwcTerm.order,
        DwcTerm.family, DwcTerm.subfamily, DwcTerm.genus, DwcTerm.subgenus};

  /**
   * List of all class terms in dwc.
   */
  public static final DwcTerm[] CLASS_TERMS = Arrays.stream(values())
                                                    .filter(DwcTerm::isClass)
                                                    .toArray(DwcTerm[]::new);

  private final String groupName;
  public final String normQName;
  public final String[] normAlts;

  private DwcTerm(String groupName, String... alternatives) {
    normQName = TermFactory.normaliseTerm(qualifiedName());
    for (int i = 0; i < alternatives.length; i++) {
      alternatives[i] = TermFactory.normaliseTerm(alternatives[i]);
    }
    normAlts = alternatives;
    this.groupName = groupName;
  }


  /**
   * The simple term name without a namespace.
   * For example scientificName.
   * @return simple term name
   */
  @Override
  public String simpleName() {
    if (this == class_) {
      return "class";
    }
    return name();
  }

  /**
   * Array of alternative simple names in use for the term.
   * Often based on older dwc versions.
   * @return simple term name
   */
  @Override
  public String[] alternativeNames() {
    return normAlts;
  }

  /**
   * The optional group the term is grouped in.
   * For example Taxon, Identification, etc.
   */
  public String getGroup() {
    return groupName;
  }

  /**
   * @return true if the dwc term is defining a class instead of a property, e.g. Taxon
   */
  @Override
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  /**
   * List all terms that belong to a given group.
   *
   * @param group the group to list terms for
   *
   * @return the list of dwc terms in the given group
   */
  public static List<DwcTerm> listByGroup(String group) {
    List<DwcTerm> terms = new ArrayList<DwcTerm>();
    for (DwcTerm t : DwcTerm.values()) {
      if (t.getGroup().equalsIgnoreCase(group)) {
        terms.add(t);
      }
    }
    return terms;
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  @Override
  public String toString() {
    return prefixedName();
  }
}
