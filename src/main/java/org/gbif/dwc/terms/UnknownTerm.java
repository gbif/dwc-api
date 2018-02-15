package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;

public class UnknownTerm implements Term, Serializable {

  private final URI uri;
  private final String name;
  private final boolean isClass;

  public static UnknownTerm build(String qualifiedName){
    return build(qualifiedName, false);
  }

  public static UnknownTerm build(String qualifiedName, boolean isClass){
    return new UnknownTerm(URI.create(qualifiedName), isClass);
  }

  public static UnknownTerm build(String qualifiedName, String simpleName){
    return build(qualifiedName, simpleName, false);
  }

  public static UnknownTerm build(String qualifiedName, String simpleName, boolean isClass){
    return new UnknownTerm(URI.create(qualifiedName), simpleName, isClass);
  }

  public UnknownTerm(URI uri, String name, boolean isClass) {
    this.isClass = isClass;
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
  }

  public UnknownTerm(URI uri, boolean isClass) {
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
    this.isClass = isClass;
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
  public boolean isClass() {
    return isClass;
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
