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
package org.gbif.dwc.terms;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnknownTerm implements Term, Serializable {

  private final URI uri;
  private final String name;
  private final String prefix;
  private final boolean isClass;

  private static final String NS = "unknown.org";
  private static final String SCHEME = "http://";
  private static final String URL = SCHEME + NS + "/";
  private static final String PREFIX = "unknown";
  private static final Pattern PREFIX_PATTERN = Pattern.compile("^([a-zA-Z0-9]+):([a-zA-Z0-9._+# -]+)$");

  public static UnknownTerm build(String name){
    return build(name, false);
  }

  private static URI unknownURI(String name) {
    return URI.create(URL + name);
  }

  public static UnknownTerm build(String name, boolean isClass){
    Matcher m = PREFIX_PATTERN.matcher(name);
    if (m.find()) {
      if (m.group(1).equalsIgnoreCase(PREFIX)) {
        // unknown:
        return new UnknownTerm(unknownURI(m.group(2)), PREFIX, m.group(2), isClass);
      } else {
        return new UnknownTerm(unknownURI(m.group(1) + "/" + m.group(2)), m.group(1), m.group(2), isClass);
      }
    }
    URI uri = URI.create(name);
    if (uri.getAuthority() != null) {
      return new UnknownTerm(uri, isClass);

    } else if (uri.getScheme() != null) {
      String scheme = uri.getScheme().equalsIgnoreCase(PREFIX) ? "" : uri.getScheme() + "/";
      return build(URL + scheme + uri.getSchemeSpecificPart(), isClass);

    } else {
      if (!name.contains("/") && !name.contains("#")) {
        try {
          name = URLEncoder.encode(name, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
      }
      return build(URL +name, isClass);
    }
  }

  public static UnknownTerm build(String qualifiedName, String simpleName){
    return build(qualifiedName, simpleName, false);
  }

  public static UnknownTerm build(String qualifiedName, String simpleName, boolean isClass){
    return new UnknownTerm(URI.create(qualifiedName), null, simpleName, isClass);
  }

  public UnknownTerm(URI uri, String prefix, String name, boolean isClass) {
    this.isClass = isClass;
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI must be an absolute URI");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The simple name is required");
    }
    this.uri = uri;
    this.name = name;
    this.prefix = prefix != null ? prefix : (
        uri.getAuthority().equalsIgnoreCase(NS) ? PREFIX : null
    );
  }

  public UnknownTerm(URI uri, boolean isClass) {
    this(uri, null, extractName(uri), isClass);
  }

  private static String extractName(URI uri) {
    if (uri == null || !uri.isAbsolute()) {
      throw new IllegalArgumentException("The qualified name URI is required and must be an absolute URI");
    }

    if (uri.getFragment() != null) {
      return uri.getFragment();

    } else if (uri.getPath() != null) {
      String name = uri.getPath();
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
      return name;

    } else {
      throw new IllegalArgumentException("The qualified name URI must have a path or fragment to automatically derive a simple name");
    }
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
    return prefixedName();
  }

  @Override
  public String prefixedName() {
    return prefix != null ? prefix + ":" + simpleName() : qualifiedName();
  }

  @Override
  public String prefix() {
    return prefix;
  }

  @Override
  public URI namespace() {
    return URI.create(uri.getScheme() + "://" + uri.getAuthority());
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
    return Objects.hash(uri, name, prefix, isClass);
  }
}
