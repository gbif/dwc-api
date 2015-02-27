package org.gbif.dwc.terms;

/**
 * EOL terms taken from http://eol.org/schema/reference_extension.xml
 */
public enum EolReferenceTerm implements Term, AlternativeNames {

  referenceID,
  publicationType,
  full_reference,
  primaryTitle;

  public static final String NS = "http://eol.org/schema/reference/";
  public static final String PREFIX = "eolref";
  static final String[] PREFIXES = {NS, PREFIX + ":", "eolr:"};

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

}
