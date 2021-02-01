/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.UnknownTerm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.junit.Assert.assertEquals;

public class TermDeserializerTest {

  /**
   * Test class resembling a verbatim occurrence field map that causes json serde trouble.
   */
  static class Occ {
    private Term term;
    private Map<Term, String> data = new HashMap<Term, String>();

    @JsonSerialize(keyUsing = TermKeySerializer.class)
    @JsonDeserialize(keyUsing = TermKeyDeserializer.class)
    public Map<Term, String> getData() {
      return data;
    }

    public void setData(Map<Term, String> data) {
      this.data = data;
    }

    public Term getTerm() {
      return term;
    }

    public void setTerm(Term term) {
      this.term = term;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Occ)) return false;

      Occ occ = (Occ) o;

      if (!data.equals(occ.data)) return false;
      if (!term.equals(occ.term)) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = term.hashCode();
      result = 31 * result + data.hashCode();
      return result;
    }

  }

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
    int counter = 0;
    Map<Term, Integer> terms = new HashMap<>();
    for (Term t : DwcTerm.values()) {
      terms.put(t, counter++);
    }

    for (Term t : GbifTerm.values()) {
      terms.put(t, counter++);
    }

    String json = mapper.writeValueAsString(terms);
    assertEquals(terms.size(), mapper.readValue(json, HashMap.class).size());
  }

  @Test
  public void testOcc() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    final Term custom = UnknownTerm.build("http://me.com/mum", "mum");
    Occ o = new Occ();
    o.setTerm(custom);
    o.getData().put(custom, "custom");
    o.getData().put(GbifTerm.ageInDays, "21");

    for (Term t : DwcTerm.values()) {
      o.getData().put(t, t.simpleName());
    }

    String json = mapper.writeValueAsString(o);
    Occ o2 = mapper.readValue(json, Occ.class);
    System.out.println(o.equals(o2));

    assertEquals(o, mapper.readValue(json, Occ.class));
  }
}
