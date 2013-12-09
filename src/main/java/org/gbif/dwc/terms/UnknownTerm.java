package org.gbif.dwc.terms;

public class UnknownTerm implements Term {

  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  private final String qname;
  private final String name;

  public UnknownTerm(String qname, String name) {
    this.qname = qname;
    this.name = name;
  }

  @Override
  public String qualifiedName() {
    return qname;
  }

  @Override
  public String simpleName() {
    return name;
  }

  @Override
  public String[] alternativeNames() {
    return EMPTY_STRING_ARRAY;
  }

  @Override
  public String toString() {
    return qname;
  }

}
