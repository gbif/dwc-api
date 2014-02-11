package org.gbif.dwc.terms;

import java.net.URI;
import java.net.URISyntaxException;

public class UnknownTerm implements Term {

  private final URI uri;
  private final String name;

  public static UnknownTerm build(String qualifiedName){
    try {
      return new UnknownTerm(new URI(qualifiedName));
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
  }

  public static UnknownTerm build(String qualifiedName, String simpleName){
    try {
      return new UnknownTerm(new URI(qualifiedName), simpleName);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
  }

  public UnknownTerm(URI uri, String name) {
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
  }

  public UnknownTerm(URI uri) {
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI is required and must be an absolute URI");
    }

    String name = null;
    if (uri.getFragment() != null) {
      name = uri.getFragment();

    } else if (uri.getPath() != null) {
      name = uri.getPath();
      // remove trailing and ending slash if existing
      if (name.endsWith("/")) {
        name = name.substring(0, name.length()-1);
      }
      if (name.startsWith("/")) {
        name = name.substring(1);
      }
      // only use last part of path
      int pos = name.lastIndexOf("/");
      if (pos > 0) {
        name = name.substring(pos + 1);
      }

    } else {
      throw new IllegalArgumentException("The qualified name URI must have a path or fragment to automatically derive a simple name");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
  }

  @Override
  public String qualifiedName() {
    return uri.toString();
  }

  @Override
  public String simpleName() {
    return name;
  }

  @Override
  public String toString() {
    return uri.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Term)) return false;

    Term that = (Term) o;

    return qualifiedName().equals(that.qualifiedName());
  }

  @Override
  public int hashCode() {
    return qualifiedName().hashCode();
  }

}
