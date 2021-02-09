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

import java.util.Comparator;

/**
 * Compares terms by their qualified name.
 * Java enums (term classes) have a final natural order implemented which cannot be changed.
 */
public class TermComparator implements Comparator<Term> {

  @Override
  public int compare(Term o1, Term o2) {
    return o1.qualifiedName().compareTo(o2.qualifiedName());
  }
}
