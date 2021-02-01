package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.Term;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * A json serializer that turns dwc terms into their full qualified term name.
 */
public class TermSerializer extends JsonSerializer<Term> {

  @Override
  public void serialize(Term value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException {
    jgen.writeString(value.qualifiedName());
  }
}
