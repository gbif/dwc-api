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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TermFactoryTest {

  final TermFactory TF = TermFactory.instance();

  /**
   * GBIF code assumes a term coming from any of the Term enumerations mostly have unique simple names.
   * This tests verifies that!
   *
   * AcefTerm is known to overlap, so its excluded, see skipSimple in AcefTermTest.
   */
  @Test
  public void testKnownTermUniqueness() {
    Set<String> names = new HashSet<>();

    addTerms(names, DwcTerm.values());
    addTerms(names, DcTerm.values());
    addTerms(names, GbifTerm.values());
    addTerms(names, GbifInternalTerm.values());
    addTerms(names, IucnTerm.values());
    //addTerms(names, DcElement.values());
    //addTerms(names, AcefTerm.values());
    //addTerms(names, PlaziTerm.values());
    addTerms(names, GadmTerm.values());
    //addTerms(names, DwcaTerm.values());

    // Audubon Core
    addTerms(names, termsBut(AcTerm.values(), AcTerm.Multimedia, AcTerm.relatedResourceID));
    addTerms(names, ExifTerm.values());
    addTerms(names, IptcTerm.values());
    addTerms(names, PhotoshopTerm.values());
    addTerms(names, XmpTerm.values());
    addTerms(names, XmpRightsTerm.values());

    // Terms for extensions supported in GBIF downloads.
    addTerms(names, ChronoTerm.values());
    addTerms(names, GbifDnaTerm.values());
    addTerms(names, GbifMiqeTerm.values());
    addTerms(names, GermplasmTerm.values());
    addTerms(names, termsBut(GgbnTerm.values(), GgbnTerm.MaterialSample));
    addTerms(names, MixsTerm.values());
    addTerms(names, ObisTerm.values());
    addTerms(names, Wgs84GeoPositioningTerm.values());
  }

  private Term[] termsBut(Term[] terms, Term... exclude) {
    Set<Term> excl = new HashSet<>(Arrays.asList(exclude));
    return Arrays.stream(terms)
        .filter(t -> !excl.contains(t))
        .toArray(Term[]::new);
  }

  private void addTerms(Set<String> names, Term[] terms) {
    for (Term t : terms) {
      assertFalse(names.contains(t.simpleName()), "Duplicate simple name " + t.simpleName() + " for " + t);
      if (t instanceof AlternativeNames) {
        for (String a : ((AlternativeNames) t).alternativeNames()) {
          assertFalse(names.contains(a), "Duplicate alternative name " + a + " for " + t);
          names.add(a);
        }
      }
      names.add(t.simpleName());
    }
  }

  @Test
  public void testCompleteness() throws Exception {
    String packageName = DwcTerm.class.getPackage().getName();
    for (Class<?> cl : getClassesInPackage(packageName)) {
      if (cl.isEnum() && Term.class.isAssignableFrom(cl)) {
        Class<Term> tcl = (Class<Term>) cl;
        for (Term t : tcl.getEnumConstants()) {
          assertEquals(t, TF.findTerm(t.qualifiedName()), "Unknown term " + t.qualifiedName());
          assertEquals(t, TF.findTerm(t.prefixedName()), "Unknown term " + t.prefixedName());
        }
      }
    }
  }

  /**
   * Scans all classes in a given package.
   */
  private Set<Class<?>> getClassesInPackage(String packageName) throws IOException, URISyntaxException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace('.', '/');
    URI resource = classLoader.getResource(path).toURI();

    if (resource.getScheme().equals("jar")) {
      try (FileSystem fileSystem = FileSystems.newFileSystem(resource, Collections.emptyMap())) {
        Path packagePath = fileSystem.getPath(path);
        return findClasses(packagePath, packageName);
      }
    } else {
      Path packagePath = Paths.get(resource);
      return findClasses(packagePath, packageName);
    }
  }

  /**
   * Finds all classes in a given directory.
   */
  private Set<Class<?>> findClasses(Path directory, String packageName) throws IOException {
    Set<Class<?>> classes = new HashSet<>();
    try (Stream<Path> paths = Files.walk(directory, 1)) {
      paths.filter(path -> path.toString().endsWith(".class"))
          .forEach(path -> {
            try {
              String className = packageName + '.' +
                  path.getFileName().toString().replace(".class", "");
              classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
              // Ignore
            }
          });
    }
    return classes;
  }

  @Test
  public void testFindTerm() {
    assertEquals(DwcTerm.scientificName, TF.findTerm("ScientificName"));
    assertEquals(DwcTerm.scientificName, TF.findTerm("dwc:scientificName"));
    assertEquals(DwcTerm.scientificName, TF.findTerm("http://rs.tdwg.org/dwc/terms/scientificName"));
    assertEquals(DcElement.identifier, TF.findTerm("dc:identifier"));
    assertEquals(GbifTerm.Identifier, TF.findTerm("Identifier"));
    assertEquals(GbifTerm.Identifier, TF.findClassTerm("identifier"));
    assertEquals(DcTerm.identifier, TF.findTerm("identifier"));
    assertEquals(DcTerm.identifier, TF.findPropertyTerm("identifier"));
    assertEquals(DcTerm.identifier, TF.findTerm("id"));
    assertEquals(DwcTerm.parentNameUsageID, TF.findTerm("dwc:higherNameUsageID"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findTerm("dwc:acceptedTaxonId"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findTerm("dwc:acceptedTaxonID"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findTerm("acceptedTaxonID"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findTerm("http://rs.tdwg.org/dwc/terms/acceptedTaxonId"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findPropertyTerm("dwc:acceptedTaxonId"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findPropertyTerm("dwc:acceptedTaxonID"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findPropertyTerm("acceptedTaxonID"));
    assertEquals(DwcTerm.acceptedNameUsageID, TF.findPropertyTerm("http://rs.tdwg.org/dwc/terms/acceptedTaxonId"));
    assertEquals(AcefTerm.AcceptedTaxonID, TF.findPropertyTerm("AcceptedTaxonID"));
    assertEquals(AcefTerm.AcceptedTaxonID, TF.findTerm("acef:AcceptedTaxonID"));
    assertEquals(AcefTerm.AcceptedTaxonID, TF.findPropertyTerm("acef:AcceptedTaxonID"));

    assertEquals(DwcTerm.vernacularName, TF.findTerm("dwc:vernacularName"));
    assertEquals(DwcTerm.vernacularName, TF.findTerm("vernacularName"));
    assertEquals(GbifTerm.VernacularName, TF.findTerm("VernacularName"));
    assertEquals(GbifTerm.VernacularName, TF.findTerm("gbif:VernacularName"));

    assertEquals(GbifInternalTerm.unitQualifier, TF.findTerm("UNIT_QUALIFIER"));

    assertEquals("threatStatus", TF.findTerm("http://rs.gbif.org/terms/1.0/threatStatus").simpleName());
    assertEquals("threatStatus", TF.findTerm("http://rs.gbif.org/terms/1321.43/threatStatus").simpleName());

    assertEquals(DwcTerm.catalogNumber, TF.findTerm("\"catalogNumber\""));
    assertEquals(AcefTerm.Details, TF.findTerm("acef:source"));
    assertEquals(DwcTerm.family, TF.findTerm("dwc:family"));
    assertEquals(DwcTerm.family, TF.findTerm("family"));
    assertEquals(AcefTerm.Family, TF.findTerm("acef:family"));

    assertEquals(DwcaTerm.ID, TF.findTerm("dwca:ID"));

    assertEquals(BibTexTerm.CLASS_TERM, TF.findTerm("bib:BibTeX"));
    assertEquals(BibTexTerm.CLASS_TERM, TF.findTerm("http://bibtex.org/BibTeX"));

    Term t = BibTexTerm.buildFromURI("http://bibtex.org/creator");
    assertEquals(t, TF.findTerm("http://bibtex.org/creator"));
    assertEquals(t, TF.findTerm("bib:creator"));

    // ACEF namespace has changed: https://github.com/gbif/portal-feedback/issues/4890
    assertEquals(AcefTerm.Country, TF.findTerm("acef:Country"));
    assertEquals(AcefTerm.Country, TF.findTerm("http://rs.col.plus/terms/acef/Country"));
    assertEquals(AcefTerm.Country, TF.findTerm("https://rs.col.plus/terms/acef/Country"));
    assertEquals(DwcTerm.country, TF.findTerm("country"));

    // MIxS uses unreadable identifiers in qualified names.
    assertEquals(MixsTerm.samp_size, TF.findTerm("samp_size"));
    assertEquals(MixsTerm.samp_size, TF.findTerm("mixs:samp_size"));
    assertEquals(MixsTerm.samp_size, TF.findTerm("https://w3id.org/gensc/terms/MIXS:0000001"));
    assertEquals(MixsTerm.samp_size, TF.findTerm("https://w3id.org/mixs/0000001"));
    assertEquals(MixsTerm.lib_reads_seqd, TF.findTerm("http://gensc.org/ns/mixs/lib_reads_seqd"));
    assertEquals(MixsTerm.assembly_name, TF.findTerm("http://gensc.org/ns/mixs/assembly"));
    assertEquals(MixsTerm.assembly_qual, TF.findTerm("http://gensc.org/ns/mixs/finishing_strategy"));
    assertEquals(MixsTerm.annot, TF.findTerm("http://gensc.org/ns/mixs/annot_source"));
  }

  @Test
  public void addUnknownTerm() {
    TermFactory factory = TermFactory.instance();

    Term me1 = factory.findTerm("http://me.com/#me");
    Term me2 = factory.findTerm("http://me.com/me");
    Term me3 = factory.findTerm("http://me.org/me");

    assertNotEquals(me1, me2);
    assertNotEquals(me1, me3);
    assertNotEquals(me2, me3);
  }

  @Test
  public void badTerm() {
    TermFactory factory = TermFactory.instance();
    assertThrows(IllegalArgumentException.class, () -> factory.findTerm("Hallo Tim"));
  }

  @Test
  public void removedGbifTerms() {
    assertEquals(DwcTerm.genericName, TF.findTerm("genericName"));
    assertEquals(DwcTerm.recordedByID, TF.findTerm("recordedByID"));
    assertEquals(DwcTerm.identifiedByID, TF.findTerm("identifiedByID"));

    assertEquals(DwcTerm.genericName, TF.findTerm("http://rs.gbif.org/terms/1.0/genericName"));
    assertEquals(DwcTerm.recordedByID, TF.findTerm("http://rs.gbif.org/terms/1.0/recordedByID"));
    assertEquals(DwcTerm.identifiedByID, TF.findTerm("http://rs.gbif.org/terms/1.0/identifiedByID"));

    assertEquals(DwcTerm.genericName, TF.findTerm("gbif:genericName"));
    assertEquals(DwcTerm.recordedByID, TF.findTerm("gbif:recordedByID"));
    assertEquals(DwcTerm.identifiedByID, TF.findTerm("gbif:identifiedByID"));
  }

  @Test
  public void addSimpleTerm() {
    TermFactory factory = TermFactory.instance();

    Term hallo = factory.findTerm("hallo");
    assertEquals(UnknownTerm.class, hallo.getClass());
    assertEquals("http://unknown.org/hallo", hallo.qualifiedName());
    assertEquals("hallo", hallo.simpleName());


    Term tim = factory.findTerm("Tim");
    assertEquals(UnknownTerm.class, tim.getClass());
    assertEquals("http://unknown.org/Tim", tim.qualifiedName());
    assertEquals("http://unknown.org", tim.namespace().toString());
    assertEquals("Tim", tim.simpleName());

    Term eva = factory.findTerm("tim:Eva");
    assertEquals(UnknownTerm.class, eva.getClass());
    assertEquals("http://unknown.org/tim/Eva", eva.qualifiedName());
    assertEquals("http://unknown.org", tim.namespace().toString());
    assertEquals("http://unknown.org/tim/Eva", eva.qualifiedName());
    assertEquals("tim:Eva", eva.prefixedName());
    assertEquals("Eva", eva.simpleName());

    assertNotEquals(hallo, tim);
  }

  @Test
  public void addUnknownSimpleTerm() {
    TermFactory factory = TermFactory.instance();

    Term t1 = factory.findTerm("me");
    Term t2 = factory.findTerm("me");
    Term t3 = factory.findTerm("Ne");

    assertEquals(t1, t2);
    assertNotEquals(t1, t3);
    assertNotEquals(t2, t3);
  }

  /**
     * Not a real test, just a way of running many concurrent TermFactory.instance() calls to verify thread safety.
     */
  @Test
  public void testMultithreadStart() throws InterruptedException {
    int threadCount = 100;
    ExecutorService tp = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      tp.submit(new TermFactoryLoader());
    }
    tp.shutdown();
    tp.awaitTermination(30, TimeUnit.SECONDS);
  }

  private static class TermFactoryLoader implements Runnable {
    @Override
    public void run() {
      TermFactory.instance();
    }
  }
}
