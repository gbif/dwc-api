package org.gbif.dwc.terms;

public enum DcTerm implements Term {
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
  public static final String PREFIX = "dc";
  static final String[] PREFIXES = {NS, PREFIX + ":", "http://purl.org/dc/elements/1.1/", "dct", "dcterm:", "dcterms:"};

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
    return NS + name();
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
