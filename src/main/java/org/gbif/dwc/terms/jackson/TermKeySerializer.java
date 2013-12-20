package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.Term;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 */
public class TermKeySerializer extends JsonSerializer<Term> {

  @Override
  public void serialize(Term value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
    jgen.writeFieldName(value.qualifiedName());
  }
}