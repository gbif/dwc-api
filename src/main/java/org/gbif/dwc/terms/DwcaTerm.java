/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;

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
