package org.gbif.dwc.terms;

import java.net.URI;

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

  private static final String PREFIX = "gbint";
  private static final String NS = "http://rs.gbif.org/terms/internal/";
  private static final URI NS_URI = URI.create(NS);

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
  public String prefixedName() {
    return PREFIX + ":" + simpleName();
  }

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String[] alternativeNames() {
    return EMPTY;
  }

  @Override
  public boolean isClass() {
    return false;
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }
}
