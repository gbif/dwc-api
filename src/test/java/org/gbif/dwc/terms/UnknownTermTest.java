package org.gbif.dwc.terms;

import org.junit.Test;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 */
public class UnknownTermTest {

  @Test
  public void testBuild() throws Exception {
    UnknownTerm.build("http://me.com/#me");
    UnknownTerm.build("http://me.com/me");
    UnknownTerm.build("http://me.org/me");


    assertEquals(UnknownTerm.build("http://me.org/me"), UnknownTerm.build("http://me.org/me"));
    assertEquals(UnknownTerm.build("me"), UnknownTerm.build("http://unknown.org/me"));
  }

  @Test
  public void testNamespace() throws Exception {
    assertEquals(URI.create("http://me.com"), UnknownTerm.build("http://me.com/me").namespace());
    assertEquals(URI.create("https://me.com"), UnknownTerm.build("https://me.com/me").namespace());
    assertEquals(URI.create("http://unknown.org"), UnknownTerm.build("me").namespace());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuildError() {
    UnknownTerm.build("http://me.com/");
  }

  @Test
  public void testBuildError2() {
    Term u = UnknownTerm.build("gbif.org/verbatimLabel");
    assertEquals("http://unknown.org/gbif.org/verbatimLabel", u.qualifiedName());
    assertEquals("verbatimLabel", u.simpleName());
  }

  @Test
  public void testPrefixedName() {
    assertEquals("http://unknown.org/gbif/verbatimLabel", UnknownTerm.build("gbif:verbatimLabel").qualifiedName());
  }

  @Test
  public void testQualifiedName() throws Exception {
    Term me = UnknownTerm.build("http://me.com/#me");
    assertEquals("http://me.com/#me", me.qualifiedName());

    me = UnknownTerm.build("http://me.com/me");
    assertEquals("http://me.com/me", me.qualifiedName());

    me = UnknownTerm.build("http://me.org/me");
    assertEquals("http://me.org/me", me.qualifiedName());
  }

  @Test
  public void testSimpleName() throws Exception {
    Term me1 = UnknownTerm.build("http://me.com/#me");
    Term me2 = UnknownTerm.build("http://me.com/me");
    Term me3 = UnknownTerm.build("http://me.org/me");

    assertEquals("me", me1.simpleName());
    assertEquals("me", me2.simpleName());
    assertEquals("me", me3.simpleName());
  }

  @Test
  public void testEquals() throws Exception {
    Term me1 = UnknownTerm.build("http://me.com/#me");
    Term me2 = UnknownTerm.build("http://me.com/me");
    Term me3 = UnknownTerm.build("http://me.org/me");

    assertNotEquals(me1, me2);
    assertNotEquals(me1, me3);
    assertNotEquals(me2, me3);

    // only qualified names are relevant when deciding if a term is equal
    Term me4 = UnknownTerm.build("http://me.org/me", "oscar");
    assertNotEquals(me1, me4);
    assertNotEquals(me2, me4);
    assertEquals(me3, me4);

  }

  @Test
  public void testSet() throws Exception {
    Set<Term> terms = new HashSet<Term>();
    terms.add(UnknownTerm.build("http://me.com/#me"));
    terms.add(UnknownTerm.build("http://me.com/me"));
    terms.add(UnknownTerm.build("http://me.org/me"));
    terms.add(UnknownTerm.build("http://me.org/me", "oscar"));
    terms.add(DcTerm.title);
    terms.add(DcElement.title);
    terms.add(DwcTerm.bed);

    assertEquals(6, terms.size());
  }
}
