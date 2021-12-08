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

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility methods for {@link Term}
 */
public class Terms {

  private static final Pattern NULL_PATTERN = Pattern.compile("^\\s*(\\\\N|\\\\?NULL)\\s*$");
  private static final List<Term> VOCABULARY_BACKED_TERMS = findVocabularyBackedTerms();

  /**
   * static utility class
   */
  private Terms() {
  }

  /**
   * Tries various terms in given order until it finds a non empty value.
   *
   * @return value or null
   */
  public static String getValueOfFirst(Map<Term, String> record, Term... terms) {
    for (Term t : terms) {
      if (record.containsKey(t)) {
        String val = clean(record.get(t));
        if (val != null) {
          return val;
        }
      }
    }
    return null;
  }

  private static String clean(String str) {
    if (isTermValueBlank(str)) {
      return null;
    }
    str = str.trim();
    if (str.isEmpty()) {
      return null;
    }
    return str;
  }

  /**
   * Check if the value provided should be considered "blank" in the context of a {@link Term} value.
   */
  public static boolean isTermValueBlank(String termValue) {
    return termValue == null || termValue.isEmpty() || NULL_PATTERN.matcher(termValue).find();
  }

  /**
   * Returns all the {@link Term} that are annotated with {@link Vocabulary}.
   */
  public static List<Term> getVocabularyBackedTerms() {
    return VOCABULARY_BACKED_TERMS;
  }

  private static List<Term> findVocabularyBackedTerms() {
    return Stream.of(getTerms(AcefTerm.class),
                     getTerms(AcTerm.class),
                     getTerms(DcTerm.class),
                     getTerms(DwcaTerm.class),
                     getTerms(DwcTerm.class),
                     getTerms(GadmTerm.class),
                     getTerms(GbifInternalTerm.class),
                     getTerms(GbifTerm.class),
                     getTerms(IucnTerm.class),
                     getTerms(XmpRightsTerm.class),
                     getTerms(XmpTerm.class),
                     getTerms(GbifTerm.class))
      .flatMap(Arrays::stream)
      .filter(Terms::isVocabulary)
      .collect(Collectors.toList());
  }

  private static <T extends Term> Term[] getTerms(Class<T> term) {
    return term.getEnumConstants();
  }

  /**
   * @return true if the term is a handled/annotated as Vocabulary.
   */
  public static boolean isVocabulary(Term term) {
    return term instanceof Enum && hasTermAnnotation(term, Vocabulary.class);
  }

  private static boolean hasTermAnnotation(Term term, Class<? extends Annotation> annotation) {
    try {
      return term.getClass().getField(((Enum<?>) term).name()).isAnnotationPresent(annotation);
    } catch (NoSuchFieldException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
