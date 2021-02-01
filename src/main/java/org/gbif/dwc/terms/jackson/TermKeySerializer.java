package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.Term;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TermKeySerializer extends JsonSerializer<Term> {

  @Override
  public void serialize(Term value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException {
    jgen.writeFieldName(value.qualifiedName());
  }
}
