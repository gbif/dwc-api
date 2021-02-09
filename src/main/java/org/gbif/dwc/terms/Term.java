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

import org.gbif.dwc.terms.jackson.TermDeserializer;
import org.gbif.dwc.terms.jackson.TermKeyDeserializer;
import org.gbif.dwc.terms.jackson.TermKeySerializer;
import org.gbif.dwc.terms.jackson.TermSerializer;

import java.io.Serializable;
import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TermSerializer.class, keyUsing = TermKeySerializer.class)
@JsonDeserialize(using = TermDeserializer.class, keyUsing = TermKeyDeserializer.class)
public interface Term extends Serializable {

  /**
   * A unique standard prefix representing the namespace.
   * For example dwc.
   */
  String prefix();

  /**
   * The namespace the terms are in.
   * Default implementations here expect the namespace to end with a slash.
   */
  URI namespace();

  /**
   * The simple term name without any namespace or paths.
   * For example scientificName.
   */
  String simpleName();

  /**
   * The simple term name prefixed by a short unique namespace abbreviation.
   * For example dwc:scientificName.
   */
  default String prefixedName() {
    return prefix() + ":" + simpleName();
  }

  /**
   * The full qualified term uri including the namespace.
   * For example http://rs.tdwg.org/dwc/terms/scientificName.
   */
  default String qualifiedName() {
    return namespace() + simpleName();
  }

  /**
   * Informs if a term is generally used as a class term, i.e. defining rowTypes not properties.
   * For example VernacularName, Taxon or Occurrence
   * @return true if the term is defining a class instead of a property, e.g. Taxon
   */
  boolean isClass();

}
