package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.Term;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 *
 */
public class DwcModule extends SimpleModule {

  public DwcModule() {
    super("Dwc API", new Version(1,0,0,null));
    addSerializer(Term.class, new TermSerializer());
    addDeserializer(Term.class, new TermDeserializer());
    addKeySerializer(Term.class, new TermKeySerializer());
    addKeyDeserializer(Term.class, new TermKeyDeserializer());
  }
}
