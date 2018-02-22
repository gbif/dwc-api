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
  static final String[] PREFIXES = {PREFIX + ":"};

  public final String[] alternatives;

  @Override
  public String prefixedName() {
    return PREFIX + ":" + name();
  }

  @Override
  public String toString() {
    return prefixedName();
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

  @Override
  public boolean isClass() {
    return false;
  }

  DcElement(String... alternatives) {
    this.alternatives = alternatives;
  }
}
