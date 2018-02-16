package org.gbif.dwc.terms;

/**
 * Terms for Adobe XMP have URIs that are not resolvable. Instead, visit
 * <a href="http://www.adobe.com/content/dam/Adobe/en/devnet/xmp/pdfs/XMPSpecificationPart1.pdf">XMP Specification Part 1, Sec 8.4 </a>
 * for further documentation.
 *
 * These terms are used by the Audubon Core standard.
 */
public enum XmpTerm implements Term, AlternativeNames {

  MetadataDate,
  Rating,
  CreateDate;

  public static final String NS = "http://ns.adobe.com/xap/1.0/";
  public static final String PREFIX = "xmp";
  static final String[] PREFIXES = {PREFIX + ":", "adobe:"};

  public String toString() {
    return PREFIX + ":" + this.name();
  }

  @Override
  public String simpleName() {
    return name();
  }

  @Override
  public String[] alternativeNames() {
    return new String[]{};
  }

  @Override
  public String qualifiedName() {
    return NS + simpleName();
  }

  @Override
  public boolean isClass() {
    return false;
  }

}
