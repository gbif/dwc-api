package org.gbif.dwc.terms;

/**
 * Optional interface for terms that have known alternative names the TermFactory should be aware of.
 */
public interface AlternativeNames {

  /**
   * @return array of simple, alternative term names
   */
  String[] alternativeNames();

}
