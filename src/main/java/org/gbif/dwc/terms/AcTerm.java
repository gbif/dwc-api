package org.gbif.dwc.terms;

/**
 * All Audubon Core terms with namespace http://rs.tdwg.org/ac/terms/.
 * See
 * <ul>
 *   <li>http://terms.tdwg.org/wiki/Audubon_Core_Term_List</li>
 *   <li>http://terms.tdwg.org/wiki/Audubon_Core</li>
 * </ul>
 */
public enum AcTerm implements Term, AlternativeNames {

  accessURI,
  associatedObservationReference,
  associatedSpecimenReference,
  attributionLinkURL,
  attributionLogoURL,
  caption,
  captureDevice,
  commenter,
  commenterLiteral,
  comments,
  derivedFrom,
  digitizationDate,
  fundingAttribution,
  furtherInformationURL,
  hasServiceAccessPoint,
  hashFunction,
  hashValue,
  IDofContainingCollection,
  licenseLogoURL,
  licensingException,
  metadataCreator,
  metadataCreatorLiteral,
  metadataLanguage,
  metadataLanguageLiteral,
  metadataProvider,
  metadataProviderLiteral,
  otherScientificName,
  physicalSetting,
  provider,
  providerID,
  providerLiteral,
  providerManagedID,
// we leave this AC term out as the simple name exists already in DwC!
//  relatedResourceID,
  resourceCreationTechnique,
  reviewer,
  reviewerComments,
  reviewerLiteral,
  serviceExpectation,
  subjectCategoryVocabulary,
  subjectOrientation,
  subjectPart,
  subtype,
  subtypeLiteral,
  tag,
  taxonCount,
  taxonCoverage,
  timeOfDay,
  variant,
  variantDescription,
  variantLiteral;

  public static final String NS = "http://rs.tdwg.org/ac/terms/";
  public static final String PREFIX = "ac";
  static final String[] PREFIXES = {PREFIX + ":"};

  public String toString() {
    return PREFIX + ":" + this.name();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return new String[]{};
  }

  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  @Override
  public boolean isClass() {
    return false;
  }

}
