package org.gbif.dwc.terms;

import java.util.ArrayList;
import java.util.List;

/**
 * All Darwin Core terms with namespace http://rs.tdwg.org/dwc/terms/ as an enumeration
 * with alternative term names found sometimes in data.
 * Old, deprecated terms are kept but marked as such.
 */
public enum DwcTerm implements Term, AlternativeNames {

  // CLASS TERMS
  Event(DwcTerm.GROUP_EVENT),
  GeologicalContext(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  Identification(DwcTerm.GROUP_IDENTIFICATION),
  MaterialSample(DwcTerm.GROUP_MATERIAL_SAMPLE),
  MeasurementOrFact(DwcTerm.GROUP_MEASUREMENTORFACT),
  Occurrence(DwcTerm.GROUP_OCCURRENCE, "DarwinCore", "SimpleDarwinCore"),
  ResourceRelationship(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  Taxon(DwcTerm.GROUP_TAXON),

  // PROPERTY TERMS
  acceptedNameUsage(DwcTerm.GROUP_TAXON, "acceptedTaxon"),
  acceptedNameUsageID(DwcTerm.GROUP_TAXON, "acceptedTaxonID"),
  associatedMedia(DwcTerm.GROUP_OCCURRENCE),
  associatedOccurrences(DwcTerm.GROUP_OCCURRENCE),
  associatedReferences(DwcTerm.GROUP_OCCURRENCE),
  associatedSequences(DwcTerm.GROUP_OCCURRENCE),
  associatedTaxa(DwcTerm.GROUP_OCCURRENCE),
  basisOfRecord(DwcTerm.GROUP_RECORD),
  bed(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  behavior(DwcTerm.GROUP_OCCURRENCE),
  catalogNumber(DwcTerm.GROUP_OCCURRENCE, "catalogNumberNumeric"),
  /**
   * The taxonomic class.
   * The real Darwin Core term is class, but as java does not allow this name we use a variation instead.
   */
  class_(DwcTerm.GROUP_TAXON, "class"),
  collectionCode(DwcTerm.GROUP_RECORD),
  collectionID(DwcTerm.GROUP_RECORD),
  continent(DwcTerm.GROUP_LOCATION),
  coordinatePrecision(DwcTerm.GROUP_LOCATION),
  coordinateUncertaintyInMeters(DwcTerm.GROUP_LOCATION),
  country(DwcTerm.GROUP_LOCATION),
  countryCode(DwcTerm.GROUP_LOCATION),
  county(DwcTerm.GROUP_LOCATION),
  dataGeneralizations(DwcTerm.GROUP_RECORD),
  datasetID(DwcTerm.GROUP_RECORD),
  datasetName(DwcTerm.GROUP_RECORD),
  dateIdentified(DwcTerm.GROUP_IDENTIFICATION),
  day(DwcTerm.GROUP_EVENT),
  decimalLatitude(DwcTerm.GROUP_LOCATION, "latitude"),
  decimalLongitude(DwcTerm.GROUP_LOCATION, "longitude"),
  disposition(DwcTerm.GROUP_OCCURRENCE),
  dynamicProperties(DwcTerm.GROUP_RECORD),
  earliestAgeOrLowestStage(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEonOrLowestEonothem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEpochOrLowestSeries(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestEraOrLowestErathem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  earliestPeriodOrLowestSystem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  endDayOfYear(DwcTerm.GROUP_EVENT),
  establishmentMeans(DwcTerm.GROUP_OCCURRENCE),
  eventDate(DwcTerm.GROUP_EVENT, "earliestDateCollected", "latestDateCollected"),
  eventID(DwcTerm.GROUP_EVENT),
  eventRemarks(DwcTerm.GROUP_EVENT),
  eventTime(DwcTerm.GROUP_EVENT),
  family(DwcTerm.GROUP_TAXON),
  fieldNotes(DwcTerm.GROUP_EVENT),
  fieldNumber(DwcTerm.GROUP_EVENT),
  footprintSRS(DwcTerm.GROUP_LOCATION),
  footprintSpatialFit(DwcTerm.GROUP_LOCATION),
  footprintWKT(DwcTerm.GROUP_LOCATION),
  formation(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  genus(DwcTerm.GROUP_TAXON),
  genericName(DwcTerm.GROUP_TAXON),    // not yet ratified !!!
  geodeticDatum(DwcTerm.GROUP_LOCATION, "datum"),
  geologicalContextID(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  georeferencedDate(DwcTerm.GROUP_LOCATION),
  georeferenceProtocol(DwcTerm.GROUP_LOCATION),
  georeferenceRemarks(DwcTerm.GROUP_LOCATION),
  georeferenceSources(DwcTerm.GROUP_LOCATION),
  georeferenceVerificationStatus(DwcTerm.GROUP_LOCATION),
  georeferencedBy(DwcTerm.GROUP_LOCATION),
  group(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  habitat(DwcTerm.GROUP_EVENT),
  higherClassification(DwcTerm.GROUP_TAXON),
  higherGeography(DwcTerm.GROUP_LOCATION),
  higherGeographyID(DwcTerm.GROUP_LOCATION),
  highestBiostratigraphicZone(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  identificationID(DwcTerm.GROUP_IDENTIFICATION),
  identificationQualifier(DwcTerm.GROUP_IDENTIFICATION),
  identificationReferences(DwcTerm.GROUP_IDENTIFICATION),
  identificationRemarks(DwcTerm.GROUP_IDENTIFICATION),
  identificationVerificationStatus(DwcTerm.GROUP_IDENTIFICATION),
  identifiedBy(DwcTerm.GROUP_IDENTIFICATION),
  individualCount(DwcTerm.GROUP_OCCURRENCE),
  individualID(DwcTerm.GROUP_OCCURRENCE),
  informationWithheld(DwcTerm.GROUP_RECORD),
  infraspecificEpithet(DwcTerm.GROUP_TAXON),
  institutionCode(DwcTerm.GROUP_RECORD),
  institutionID(DwcTerm.GROUP_RECORD),
  island(DwcTerm.GROUP_LOCATION),
  islandGroup(DwcTerm.GROUP_LOCATION),
  kingdom(DwcTerm.GROUP_TAXON),
  latestAgeOrHighestStage(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEonOrHighestEonothem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEpochOrHighestSeries(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestEraOrHighestErathem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  latestPeriodOrHighestSystem(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  lifeStage(DwcTerm.GROUP_OCCURRENCE),
  lithostratigraphicTerms(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  locality(DwcTerm.GROUP_LOCATION),
  locationAccordingTo(DwcTerm.GROUP_LOCATION),
  locationID(DwcTerm.GROUP_LOCATION),
  locationRemarks(DwcTerm.GROUP_LOCATION),
  lowestBiostratigraphicZone(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  materialSampleID(DwcTerm.GROUP_MATERIAL_SAMPLE),
  maximumDepthInMeters(DwcTerm.GROUP_LOCATION),
  maximumDistanceAboveSurfaceInMeters(DwcTerm.GROUP_LOCATION),
  maximumElevationInMeters(DwcTerm.GROUP_LOCATION),
  measurementAccuracy(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementDeterminedBy(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementDeterminedDate(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementID(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementMethod(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementRemarks(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementType(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementUnit(DwcTerm.GROUP_MEASUREMENTORFACT),
  measurementValue(DwcTerm.GROUP_MEASUREMENTORFACT),
  member(DwcTerm.GROUP_GEOLOGICALCONTEXT),
  minimumDepthInMeters(DwcTerm.GROUP_LOCATION),
  minimumDistanceAboveSurfaceInMeters(DwcTerm.GROUP_LOCATION),
  minimumElevationInMeters(DwcTerm.GROUP_LOCATION),
  month(DwcTerm.GROUP_EVENT),
  municipality(DwcTerm.GROUP_LOCATION, "city"),
  nameAccordingTo(DwcTerm.GROUP_TAXON, "taxonAccordingTo"),
  nameAccordingToID(DwcTerm.GROUP_TAXON, "taxonAccordingToID"),
  namePublishedIn(DwcTerm.GROUP_TAXON),
  namePublishedInID(DwcTerm.GROUP_TAXON),
  namePublishedInYear(DwcTerm.GROUP_TAXON),
  nomenclaturalCode(DwcTerm.GROUP_TAXON),
  nomenclaturalStatus(DwcTerm.GROUP_TAXON),
  /**
   * @deprecated occurrenceDetails is deprecated in favor over the more general term dc:references
   */
  @Deprecated occurrenceDetails(DwcTerm.GROUP_OCCURRENCE),
  occurrenceID(DwcTerm.GROUP_OCCURRENCE),
  occurrenceRemarks(DwcTerm.GROUP_OCCURRENCE),
  occurrenceStatus(DwcTerm.GROUP_OCCURRENCE),
  order(DwcTerm.GROUP_TAXON),
  originalNameUsage(DwcTerm.GROUP_TAXON, "originalName", "originalTaxon", "basionym"),
  originalNameUsageID(DwcTerm.GROUP_TAXON, "originalNameID", "originalTaxonID", "basionymID"),
  otherCatalogNumbers(DwcTerm.GROUP_OCCURRENCE),
  ownerInstitutionCode(DwcTerm.GROUP_RECORD),
  parentNameUsage(DwcTerm.GROUP_TAXON, "parentTaxon", "higherTaxon", "higherNameUsage"),
  parentNameUsageID(DwcTerm.GROUP_TAXON, "parentTaxonID", "higherTaxonID", "higherNameUsageID"),
  phylum(DwcTerm.GROUP_TAXON),
  pointRadiusSpatialFit(DwcTerm.GROUP_LOCATION),
  preparations(DwcTerm.GROUP_OCCURRENCE),
  previousIdentifications(DwcTerm.GROUP_OCCURRENCE),
  recordNumber(DwcTerm.GROUP_OCCURRENCE, "collectorNumber"),
  recordedBy(DwcTerm.GROUP_OCCURRENCE, "collector"),
  relatedResourceID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipAccordingTo(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipEstablishedDate(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipOfResource(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  relationshipRemarks(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  reproductiveCondition(DwcTerm.GROUP_OCCURRENCE),
  resourceID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  resourceRelationshipID(DwcTerm.GROUP_RESOURCERELATIONSHIP),
  samplingEffort(DwcTerm.GROUP_EVENT),
  samplingProtocol(DwcTerm.GROUP_EVENT),
  scientificName(DwcTerm.GROUP_TAXON),
  scientificNameAuthorship(DwcTerm.GROUP_TAXON),
  scientificNameID(DwcTerm.GROUP_TAXON, "nameID"),
  sex(DwcTerm.GROUP_OCCURRENCE),
  specificEpithet(DwcTerm.GROUP_TAXON),
  startDayOfYear(DwcTerm.GROUP_EVENT),
  stateProvince(DwcTerm.GROUP_LOCATION, "state", "province"),
  subgenus(DwcTerm.GROUP_TAXON),
  taxonConceptID(DwcTerm.GROUP_TAXON),
  taxonID(DwcTerm.GROUP_TAXON, "nameUsageID"),
  taxonRank(DwcTerm.GROUP_TAXON, "rank"),
  taxonRemarks(DwcTerm.GROUP_TAXON, "taxonRemark"),
  taxonomicStatus(DwcTerm.GROUP_TAXON),
  typeStatus(DwcTerm.GROUP_IDENTIFICATION),
  typifiedName(DwcTerm.GROUP_IDENTIFICATION),    // not yet ratified !!!
  verbatimCoordinateSystem(DwcTerm.GROUP_LOCATION),
  verbatimCoordinates(DwcTerm.GROUP_LOCATION),
  verbatimDepth(DwcTerm.GROUP_LOCATION, "depth"),
  verbatimElevation(DwcTerm.GROUP_LOCATION, "elevation"),
  verbatimEventDate(DwcTerm.GROUP_EVENT),
  verbatimLatitude(DwcTerm.GROUP_LOCATION),
  verbatimLocality(DwcTerm.GROUP_LOCATION),
  verbatimLongitude(DwcTerm.GROUP_LOCATION),
  verbatimSRS(DwcTerm.GROUP_LOCATION),
  verbatimTaxonRank(DwcTerm.GROUP_TAXON),
  vernacularName(DwcTerm.GROUP_TAXON),
  waterBody(DwcTerm.GROUP_LOCATION),
  year(DwcTerm.GROUP_EVENT);


  public static final String NS = "http://rs.tdwg.org/dwc/terms/";
  public static final String PREFIX = "dwc";
  static final String[] PREFIXES = {NS, PREFIX + ":", "darwin:", "darwincore:", "dw:"};
  public static final String GROUP_TAXON = "Taxon";
  public static final String GROUP_MATERIAL_SAMPLE = "MaterialSample";
  public static final String GROUP_LOCATION = "Location";
  public static final String GROUP_EVENT = "Event";
  public static final String GROUP_IDENTIFICATION = "Identification";
  public static final String GROUP_OCCURRENCE = "Occurrence";
  public static final String GROUP_RESOURCERELATIONSHIP = "ResourceRelationship";
  public static final String GROUP_MEASUREMENTORFACT = "MeasurementOrFact";
  public static final String GROUP_GEOLOGICALCONTEXT = "GeologicalContext";
  public static final String GROUP_RECORD = "Record";

  /**
   * Lists all term groups in the order given on the darwin core quick reference guide.
   * @see <a href="http://rs.tdwg.org/dwc/terms/index.htm#theterms">DwC quick reference guide</a>
   */
  public static final String[] GROUPS =
    {GROUP_RECORD, GROUP_OCCURRENCE, GROUP_EVENT, GROUP_LOCATION, GROUP_GEOLOGICALCONTEXT, GROUP_IDENTIFICATION,
      GROUP_TAXON, GROUP_RESOURCERELATIONSHIP, GROUP_MEASUREMENTORFACT};

  public static final DwcTerm[] TAXONOMIC_TERMS =
    {DwcTerm.taxonID, DwcTerm.scientificNameID, DwcTerm.acceptedNameUsageID, DwcTerm.parentNameUsageID,
      DwcTerm.originalNameUsageID, DwcTerm.nameAccordingToID, DwcTerm.namePublishedInID, DwcTerm.taxonConceptID,
      DwcTerm.scientificName, DwcTerm.acceptedNameUsage, DwcTerm.parentNameUsage, DwcTerm.originalNameUsage,
      DwcTerm.nameAccordingTo, DwcTerm.namePublishedIn, DwcTerm.namePublishedInYear, DwcTerm.higherClassification,
      DwcTerm.kingdom, DwcTerm.phylum, DwcTerm.class_, DwcTerm.order, DwcTerm.family, DwcTerm.genus, DwcTerm.subgenus,
      DwcTerm.specificEpithet, DwcTerm.infraspecificEpithet, DwcTerm.taxonRank, DwcTerm.verbatimTaxonRank,
      DwcTerm.scientificNameAuthorship, DwcTerm.vernacularName, DwcTerm.nomenclaturalCode, DwcTerm.taxonomicStatus,
      DwcTerm.nomenclaturalStatus, DwcTerm.taxonRemarks, DwcTerm.genericName};

  /**
   * List of all higher rank terms in dwc, ordered by rank and starting with kingdom.
   */
  public static final DwcTerm[] HIGHER_RANKS =
    {DwcTerm.kingdom, DwcTerm.phylum, DwcTerm.class_, DwcTerm.order, DwcTerm.family, DwcTerm.genus, DwcTerm.subgenus};

  /**
   * List of all class terms in dwc.
   */
  //TODO: create dynamically via method!
  public static final DwcTerm[] CLASS_TERMS =
    {DwcTerm.Event, DwcTerm.GeologicalContext, DwcTerm.Identification, DwcTerm.MeasurementOrFact, DwcTerm.Occurrence,
      DwcTerm.ResourceRelationship, DwcTerm.Taxon, DwcTerm.MaterialSample};

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
   * The full qualified term uri including the namespace.
   * For example http://rs.tdwg.org/dwc/terms/scientificName.
   * @return full qualified term uri
   */
  @Override
  public String qualifiedName() {
    return NS + simpleName();
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
   * Array of alternative simple names in used for the term.
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
  public String toString() {
    return PREFIX + ":" + name();
  }

}
