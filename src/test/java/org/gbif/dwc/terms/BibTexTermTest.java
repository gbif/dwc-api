package org.gbif.dwc.terms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibTexTermTest {
  private static final TermFactory TERM_FACTORY = TermFactory.instance();

  @Test
  public void build() {
    BibTexTerm t = new BibTexTerm("creator");
    assertEquals("creator", t.simpleName());
    assertEquals("http://bibtex.org/creator", t.qualifiedName());
    assertEquals("bib:creator", t.prefixedName());
    assertFalse(t.isClass());

    t = BibTexTerm.buildFromURI("http://bibtex.org/creator");
    assertEquals("creator", t.simpleName());
    assertEquals("http://bibtex.org/creator", t.qualifiedName());
    assertEquals("bib:creator", t.prefixedName());
    assertFalse(t.isClass());

    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("https://bibtex.org/creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("bibtex.org/creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("bibtex.org.creator"));
    assertThrows(IllegalArgumentException.class, ()->BibTexTerm.buildFromURI("creator"));

    assertEquals("BibTeX", BibTexTerm.CLASS_TERM.simpleName());
    assertEquals("http://bibtex.org/BibTeX", BibTexTerm.CLASS_TERM.qualifiedName());
    assertEquals("bib:BibTeX", BibTexTerm.CLASS_TERM.prefixedName());
    assertTrue(BibTexTerm.CLASS_TERM.isClass());

  }

}