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
