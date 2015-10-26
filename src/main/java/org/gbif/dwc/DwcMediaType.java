package org.gbif.dwc;

/**
 * Darwin Core media type(s).
 * Unregistered media type(s), use carefully, in most of the cases it is more appropriate to return a simple application/zip
 * http://www.iana.org/assignments/media-types/media-types.xhtml
 *
 * Currently used for experimenting in OAI-PMH DublinCore resources.
 */
public class DwcMediaType {

  //Darwin Core Archive with underlying zip structure
  public final static String APPLICATION_DWCA = "application/dwca+zip";

}
