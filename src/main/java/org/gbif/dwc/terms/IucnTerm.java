package org.gbif.dwc.terms;

import java.net.URI;

public enum IucnTerm implements Term, AlternativeNames {
  threatStatus;

  private static final String PREFIX = "iucn";
  private static final String NS = "http://iucn.org/terms/";
  private static final URI NS_URI = URI.create(NS);

  public final String[] normAlts;

  IucnTerm(String... alternatives) {
    normAlts = alternatives;
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
    return prefixedName();
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
  public boolean isClass() {
    return false;
  }

}
