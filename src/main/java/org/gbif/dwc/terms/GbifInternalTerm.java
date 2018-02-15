package org.gbif.dwc.terms;

/**
 * Internal GBIF terms used for processing, fragmenting, crawling, ...
 * These are not exposed in downloads or the public API.
 */
public enum GbifInternalTerm implements Term, AlternativeNames {
  identifierCount,
  crawlId,
  fragment,
  fragmentHash,
  fragmentCreated,
  xmlSchema,
  publishingOrgKey,
  unitQualifier,;

  public static final String NS = "http://rs.gbif.org/terms/internal/";
  public static final String PREFIX = "gbint";
  private static final String[] EMPTY = new String[0];

  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String toString() {
    return PREFIX + ":" + name();
  }

  @Override
  public String[] alternativeNames() {
    return EMPTY;
  }

  @Override
  public boolean isClass() {
    return false;
  }

}
