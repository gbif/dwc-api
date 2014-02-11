package org.gbif.dwc.terms;

import org.junit.Test;

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
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuildError() {
    UnknownTerm.build("http://me.com/");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuildError2() {
    UnknownTerm.build("gbif.org/verbatimLabel");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuildError3() {
    UnknownTerm.build("gbif:verbatimLabel");
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
}
