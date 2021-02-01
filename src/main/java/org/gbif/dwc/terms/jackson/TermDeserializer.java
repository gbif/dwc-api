package org.gbif.dwc.terms.jackson;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * A json deserializer that turns full qualified term names into dwc terms instances.
 */
public class TermDeserializer extends JsonDeserializer<Term> {

  private final TermFactory factory = TermFactory.instance();

  @Override
  public Term deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      return factory.findTerm(jp.getText());
    }
    throw JsonMappingException.from(jp, "Expected JSON String");
  }
}
