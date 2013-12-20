package org.gbif.dwc.terms.jackson;

import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 *
 */
public class DwcModule extends SimpleModule {

  public DwcModule() {
    super("Dwc API", new Version(1,0,0,null));
    TermFactory factory = new TermFactory();
    addSerializer(Term.class, new TermSerializer());
    addDeserializer(Term.class, new TermDeserializer(factory));
    addKeySerializer(Term.class, new TermSerializer());
    addKeyDeserializer(Term.class, new TermKeyDeserializer(factory));
  }
}
