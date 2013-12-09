package org.gbif.dwc.terms;

public enum IucnTerm implements Term {
  threatStatus;

  public static final String NS = "http://iucn.org/terms/";
  public static final String PREFIX = "iucn";
  static final String[] PREFIXES = {NS, PREFIX + ":"};

  public final String[] normAlts;

  IucnTerm(String... alternatives) {
    normAlts = alternatives;
  }

  @Override
  public String qualifiedName() {
    return NS + name();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return normAlts;
  }

  @Override
  public String toString() {
    return PREFIX + ":" + name();
  }

}
