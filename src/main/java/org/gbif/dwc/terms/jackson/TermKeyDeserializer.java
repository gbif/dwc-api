package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.TermFactory;

import java.io.IOException;

import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.KeyDeserializer;

/**
 *
 */
public class TermKeyDeserializer extends KeyDeserializer {
  private TermFactory factory = TermFactory.instance();

  @Override
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    return factory.findTerm(key);
  }
}
