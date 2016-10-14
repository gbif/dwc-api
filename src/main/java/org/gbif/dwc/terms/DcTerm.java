package org.gbif.dwc.terms;

import java.io.Serializable;

/**
 * All Dublin Core terms with namespace http://purl.org/dc/terms/. A small subset of 15 terms exist as DcElement under
 * a different namespace, but DcTerm values must be a member of a specific class and therefore given by URI, whereas
 * DcElement values are allowed to be free text.
 */
public enum DcTerm implements Term, AlternativeNames, Serializable {
  abstract_,
  accessRights,
  accrualMethod,
  accrualPeriodicity,
  accrualPolicy,
  alternative,
  audience,
  available,
  bibliographicCitation,
  conformsTo,
  contributor,
  coverage,
  created,
  creator,
  date,
  dateAccepted,
  dateCopyrighted,
  dateSubmitted,
  description,
  educationLevel,
  extent,
  format,
  hasFormat,
  hasPart,
  hasVersion,
  identifier("ID"),
  instructionalMethod,
  isFormatOf,
  isPartOf,
  isReferencedBy,
  isReplacedBy,
  isRequiredBy,
  isVersionOf,
  issued,
  language,
  license,
  mediator,
  medium,
  modified,
  provenance,
  publisher,
  references,
  relation,
  replaces,
  requires,
  rights,
  rightsHolder,
  source,
  spatial,
  subject,
  tableOfContents,
  temporal,
  title,
  type,
  valid,
  Location;

  public static final String NS = "http://purl.org/dc/terms/";
  public static final String PREFIX = "dcterms";
  static final String[] PREFIXES = {NS, PREFIX + ":", "dct", "dcterm:"};

  public final String[] alternatives;

  public String toString() {
    return PREFIX + ":" + this.name();
  }

  @Override
  public String simpleName() {
    if (this == abstract_) {
      return "abstract";
    }
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  /**
   * @return true if the dc term is defining a class instead of a property, e.g. Location
   */
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  private DcTerm(String... alternatives) {
    this.alternatives = alternatives;
  }

}
