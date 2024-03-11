/*
 * Copyright 2024 Global Biodiversity Information Facility (GBIF)
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
 * All WGS84 Geo Positioning terms with namespace http://www.w3.org/2003/01/geo/wgs84_pos#
 */
public enum WGS84GeoPositioningTerm implements Term, AlternativeNames, Serializable {
  SpatialThing,
  Point,

  lat("latitude"),
  location,
  long_("longitude"),
  alt("altitude"),
  lat_long("lat/long");

  private static final String PREFIX = "wgs84geopositioning";
  private static final String NS = "http://www.w3.org/2003/01/geo/wgs84_pos#";
  private static final URI NS_URI = URI.create(NS);
  public final String[] alternatives;

  @Override
  public String toString() {
    return prefixedName();
  }

  @Override
  public String simpleName() {
    if (this == long_) {
      return "long";
    }
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return alternatives;
  }

  /**
   * @return true if the WGS84 Geo Positioning term is defining a class instead of a property, e.g. SpatialThing.
   */
  @Override
  public boolean isClass() {
    return Character.isUpperCase(simpleName().charAt(0));
  }

  @Override
  public String prefix() {
    return PREFIX;
  }

  @Override
  public URI namespace() {
    return NS_URI;
  }

  WGS84GeoPositioningTerm(String... alternatives) {
    this.alternatives = alternatives;
  }
}