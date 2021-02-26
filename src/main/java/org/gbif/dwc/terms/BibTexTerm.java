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

public class BibTexTerm implements Term, Serializable {

  public static final String PREFIX = "bib";
  public static final String NS = "http://bibtex.org/";
  public static final URI NS_URI = URI.create(NS);

  public static final Term CLASS_TERM = new BibTexTerm("BibTeX", true);

  private final String name;
  private final boolean isClass;

  public static BibTexTerm buildFromURI(String uri) {
    if (uri == null || !uri.startsWith(NS) || uri.equalsIgnoreCase(NS)) {
      throw new IllegalArgumentException("The qualified name URI is required and must be in the bibtex.org domain");
    }
    String name = uri.replaceFirst(NS, "");
    return new BibTexTerm(name, false);
  }

  public static BibTexTerm buildFromPrefix(String prefixedTerm) {
    if (prefixedTerm == null || !prefixedTerm.startsWith(PREFIX+":")) {
      throw new IllegalArgumentException("The prefixed name is required and must start with " + PREFIX);
    }
    String name = prefixedTerm.replaceFirst(PREFIX+":", "");
    return new BibTexTerm(name, false);
  }

  public BibTexTerm(String name) {
    this(name, false);
  }

  private BibTexTerm(String name, boolean isClass) {
    this.name = name;
    this.isClass = isClass;
  }

  @Override
  public String simpleName() {
    return name;
  }

  @Override
  public boolean isClass() {
    return isClass;
  }

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Term)) return false;

    Term that = (Term) o;

    return qualifiedName().equals(that.qualifiedName());
  }

  @Override
  public int hashCode() {
    return qualifiedName().hashCode();
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
