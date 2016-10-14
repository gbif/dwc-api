package org.gbif.dwc.terms;

import org.gbif.dwc.terms.jackson.TermDeserializer;
import org.gbif.dwc.terms.jackson.TermSerializer;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using= TermSerializer.class)
@JsonDeserialize(using= TermDeserializer.class)
public interface Term extends Serializable {

  /**
   * The simple term name without any namespace or paths.
   * For example scientificName.
   */
  String simpleName();

  /**
   * The full qualified term uri including the namespace.
   * For example http://rs.tdwg.org/dwc/terms/scientificName.
   */
  String qualifiedName();

}
