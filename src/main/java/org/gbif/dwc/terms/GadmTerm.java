package org.gbif.dwc.terms;

import java.net.URI;

public enum GadmTerm implements Term, AlternativeNames {
  level0Gid(DwcTerm.GROUP_LOCATION),
  level1Gid(DwcTerm.GROUP_LOCATION),
  level2Gid(DwcTerm.GROUP_LOCATION),
  level3Gid(DwcTerm.GROUP_LOCATION);

  private static final String PREFIX = "gadm";
  private static final String NS = "http://gadm.org/terms/";
  private static final URI NS_URI = URI.create(NS);

  public final String[] normAlts;

  GadmTerm(String... alternatives) {
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
