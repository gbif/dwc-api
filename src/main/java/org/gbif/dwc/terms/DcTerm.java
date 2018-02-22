package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;

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

  private static final String PREFIX = "dcterms";
  private static final String NS = "http://purl.org/dc/terms/";
  private static final URI NS_URI = URI.create(NS);
  public final String[] alternatives;

  @Override
  public String prefixedName() {
    return PREFIX + ":" + simpleName();
  }

  @Override
  public String toString() {
    return prefixedName();
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

  DcTerm(String... alternatives) {
    this.alternatives = alternatives;
  }

}
