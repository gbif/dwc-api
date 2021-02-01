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

import java.net.URI;

/**
 * Terms for Adobe XMP have URIs that are not resolvable. Instead, visit
 * <a href="http://www.adobe.com/content/dam/Adobe/en/devnet/xmp/pdfs/XMPSpecificationPart1.pdf">XMP Specification Part 1, Sec 8.4 </a>
 * for further documentation.
 *
 * These terms are used by the Audubon Core standard.
 */
public enum XmpRightsTerm implements Term, AlternativeNames {

  Owner,
  UsageTerms,
  WebStatement;

  private static final String PREFIX = "xmp";
  private static final String NS = "http://ns.adobe.com/xap/1.0/rights/";
  private static final URI NS_URI = URI.create(NS);


  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return new String[]{};
  }

  @Override
  public String toString() {
    return prefixedName();
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
