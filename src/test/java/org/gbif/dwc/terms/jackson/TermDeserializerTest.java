package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.Term;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TermDeserializerTest {

  @Test
  public void testTermSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    for (Term t : DwcTerm.values()) {
      String json = mapper.writeValueAsString(t);
      assertEquals(t, mapper.readValue(json, Term.class));
    }

    for (Term t : GbifTerm.values()) {
      String json = mapper.writeValueAsString(t);
      assertEquals(t, mapper.readValue(json, Term.class));
    }
  }
}
