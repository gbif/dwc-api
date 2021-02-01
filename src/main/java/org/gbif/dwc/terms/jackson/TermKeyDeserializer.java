package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.TermFactory;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class TermKeyDeserializer extends KeyDeserializer {

  private final TermFactory factory = TermFactory.instance();

  @Override
  public Object deserializeKey(String key, DeserializationContext ctxt) {
    return factory.findTerm(key);
  }
}
