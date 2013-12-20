package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

  @Test
  public void testTermMap() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Integer counter = 0;
    Map<Term, Integer> terms = new HashMap<Term, Integer>();
    for (Term t : DwcTerm.values()) {
      terms.put(t, counter++);
    }

    for (Term t : GbifTerm.values()) {
      terms.put(t, counter++);
    }

    String json = mapper.writeValueAsString(terms);
    assertEquals(terms.size(), mapper.readValue(json, HashMap.class).size());
  }
}
