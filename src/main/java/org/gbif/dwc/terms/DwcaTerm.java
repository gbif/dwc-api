package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Darwin Core Archive terms with namespace http://rs.tdwg.org/dwc/text/.
 */
public enum DwcaTerm implements Term, AlternativeNames, Serializable {
  
  /**
   * Archive internal (core) id column.
   */
  ID;
  
  private static final String PREFIX = "dwca";
  private static final String NS = "http://rs.tdwg.org/dwc/text/";
  private static final URI NS_URI = URI.create(NS);
  
  
  @Override
  public String prefix() {
    return PREFIX;
  }
  
  @Override
  public URI namespace() {
    return NS_URI;
  }
  
  @Override
  public String simpleName() {
    return name();
  }
  
  @Override
  public String toString() {
    return prefixedName();
  }
  
  @Override
  public String[] alternativeNames() {
    return new String[]{};
  }
  
  @Override
  public boolean isClass() {
    return false;
  }
  
}
