package org.gbif.dwc.terms;

import java.io.Serializable;
import java.net.URI;

public class UnknownTerm implements Term, Serializable {

  private final URI uri;
  private final String name;
  private final String prefix;
  private final boolean isClass;

  private static final String DEFAULT_PREFIX = "unknown";
  private static final String NS = "http://unknown.org/";

  public static UnknownTerm fromSimpleName(String simpleName){
    return build(NS + simpleName, false);
  }

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
    return new UnknownTerm(URI.create(qualifiedName), simpleName, DEFAULT_PREFIX, isClass);
  }

  public static UnknownTerm build(String qualifiedName, String simpleName, String prefix, boolean isClass){
    return new UnknownTerm(URI.create(qualifiedName), simpleName, DEFAULT_PREFIX, isClass);
  }

  public UnknownTerm(URI uri, String name, String prefix, boolean isClass) {
    this.isClass = isClass;
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
    this.prefix = prefix == null || prefix.trim().equals("") ? DEFAULT_PREFIX : prefix;
  }

  public UnknownTerm(URI uri, boolean isClass) {
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI is required and must be an absolute URI");
    }

    String prefix = null;
    String name = null;
    if (uri.getFragment() != null) {
      name = uri.getFragment();

    } else if (uri.getPath() != null) {
      name = uri.getPath();
      // remove trailing and ending slash if existing
      if (name.endsWith("/")) {
        name = name.substring(0, name.length() - 1);
      }
      if (name.startsWith("/")) {
        name = name.substring(1);
      }
      // only use last part of path
      int pos = name.lastIndexOf("/");
      if (pos > 0) {
        name = name.substring(pos + 1);
      }

    } else if (uri.getScheme() != null && uri.getSchemeSpecificPart() != null) {
        // prefix:name
        prefix = uri.getScheme();
        name = uri.getSchemeSpecificPart();
        uri = URI.create(NS+prefix+"/"+name);

    } else {
      throw new IllegalArgumentException("The qualified name URI must have a path or fragment to automatically derive a simple name");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
    this.isClass = isClass;
    this.prefix = prefix != null ? prefix : DEFAULT_PREFIX;
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
    return qualifiedName();
  }

  @Override
  public String prefixedName() {
    return prefix + ":" + simpleName();
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

  @Override
  public String prefix() {
    return prefix;
  }

  @Override
  public URI namespace() {
    return URI.create(uri.getScheme() + "://" + uri.getAuthority());
  }
}
