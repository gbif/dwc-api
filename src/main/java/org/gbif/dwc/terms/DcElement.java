package org.gbif.dwc.terms;

/**
 * All Dublin Core terms with namespace http://purl.org/dc/elements/1.1/. All terms exist as DcTerm under a different
 * namespace, but DcElement values are allowed to be free text, whereas DcTerm values must be a member of a specific
 * class and therefore given by URI.
 */
public enum DcElement implements Term, AlternativeNames {
  contributor,
  coverage,
  creator,
  date,
  description,
  format,
  identifier,
  language,
  publisher,
  relation,
  rights,
  source,
  subject,
  title,
  type;

  public static final String NS = "http://purl.org/dc/elements/1.1/";
  public static final String PREFIX = "dc";
  static final String[] PREFIXES = {NS, PREFIX + ":"};

  public final String[] alternatives;

  public String toString() {
    return NS + this.name();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  /**
   * @return true if the dc term is defining a class instead of a property, e.g. Location
   */
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  private DcElement(String... alternatives) {
    this.alternatives = alternatives;
  }
}
