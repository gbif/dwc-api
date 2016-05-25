package org.gbif.dwc.terms;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Utility methods for {@link Term}
 *
 */
public class Terms {

  private static final Pattern NULL_PATTERN = Pattern.compile("^\\s*(\\\\N|\\\\?NULL)\\s*$");

  /**
   * static utility class
   */
  private Terms() {
  }

  /**
   * Tries various terms in given order until it finds a non empty value.
   *
   * @param record
   * @param terms
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
    if(str.isEmpty()){
      return null;
    }
    return str;
  }

  /**
   * Check if the value provided should be considered "blank" in the context of a {@link Term} value.
   *
   * @param termValue
   * @return
   */
  public static boolean isTermValueBlank(String termValue){
    return termValue == null || termValue.isEmpty() || NULL_PATTERN.matcher(termValue).find();
  }
}
