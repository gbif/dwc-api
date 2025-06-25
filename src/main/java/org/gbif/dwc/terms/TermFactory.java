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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple, threadsafe factory for terms that knows about all ConceptTerms of this library and keeps singletons for
 * all unknown Term instances.
 */
public class TermFactory {

  private static final Logger LOG = LoggerFactory.getLogger(TermFactory.class);
  private static final Pattern NON_ALPHA_NUM_PATTERN = Pattern.compile("[^a-zA-Z0-9#-]+");
  private static TermFactory singleton;
  private static boolean initialized = false;
  private static final Object LOCK = new Object();

  private final Map<String, Term> terms = new HashMap<String, Term>();
  private final Map<String, Term> classTerms = new HashMap<String, Term>();
  private final Set<Class<? extends Enum>> registeredEnumClasses = new HashSet<>();

  public static TermFactory instance() {
    if (initialized) {
      return singleton;
    }

    synchronized (LOCK) {
      if (singleton == null) {
        LOG.debug("Building new TermFactory instance");
        singleton = new TermFactory();
        singleton.loadKnownTerms();
        initialized = true;
      }
    }

    return singleton;
  }

  private TermFactory() {
  }

  private void loadKnownTerms() {
    registerTermEnum(DwcTerm.class);
    registerTermEnum(DcTerm.class, "dct");
    registerTermEnum(GbifTerm.class);
    registerTermEnum(GbifInternalTerm.class);
    registerTermEnum(IucnTerm.class);
    registerTermEnum(DcElement.class);
    registerTermEnum(AcefTerm.class, "http://rs.col.plus/terms/acef/");
    registerTermEnum(PlaziTerm.class);
    registerTermEnum(GadmTerm.class);
    registerTermEnum(DwcaTerm.class);

    // Audubon core
    registerTermEnum(AcTerm.class);
    registerTermEnum(ExifTerm.class);
    registerTermEnum(IptcTerm.class);
    registerTermEnum(PhotoshopTerm.class);
    registerTermEnum(XmpTerm.class, "adobe");
    registerTermEnum(XmpRightsTerm.class, "xmp", "adobe"); // the same as above, but luckily different simple term names

    // DWCA extensions
    registerTermEnum(ChronoTerm.class);
    registerTermEnum(GbifMiqeTerm.class);
    registerTermEnum(GermplasmTerm.class);
    registerTermEnum(GgbnTerm.class);
    registerTermEnum(MixsTerm.class);
    registerTermEnum(ObisTerm.class);
    registerTermEnum(Wgs84GeoPositioningTerm.class);

    registerQualifiedTermEnum(DwcaTerm.class);
    addTerm(BibTexTerm.CLASS_TERM);
  }

  /**
   * @return the set of term enum classes that have been registered with this TermFactory
   */
  public Set<Class<? extends Enum>> listRegisteredTermEnums() {
    return Collections.unmodifiableSet(registeredEnumClasses);
  }

  public void registerTerm(Term term) {
    addTerm(term);
  }

  public void registerTerm(UnknownTerm term) {
    addTerm(term.qualifiedName(), term);
  }

  /**
   * Registers all terms from a term enumeration.
   * If the same class is registered again it will be silently ignored.
   *
   * @param altPrefixes alternative prefixes to be used to register simple prefixed term names
   */
  public synchronized <T extends Enum & Term & AlternativeNames> void registerTermEnum(Class<T> termClass, String ... altPrefixes) {
    if (registeredEnumClasses.contains(termClass)) {
      LOG.debug("{} is already registered", termClass);
    } else {
      registeredEnumClasses.add(termClass);
      for (T term : termClass.getEnumConstants()) {
        // add regular term representations (simple, prefixed & qualified)
        addTerm(term, altPrefixes);
        // add alternatives
        for (String alt : term.alternativeNames()) {
          addTerm(alt, term);
          if (!alt.startsWith("http") && !alt.contains(":")) {
            addTerm(term.prefix() + ":" + alt, term);
            addTerm(term.namespace().resolve(alt).toString(), term);
            for (String pre : altPrefixes) {
              addTerm(pre + ":" + alt, term);
            }
          }
        }
      }
    }
  }

  /**
   * Registers all terms from a new term enumeration, but only adds their qualified and prefixed names.
   * This is to avoid clashes with other usually more important terms that should be known by their simple name.
   */
  public <T extends Enum & Term> void registerQualifiedTermEnum(Class<T> termClass) {
    if (registeredEnumClasses.contains(termClass)) {
      LOG.debug("{} is already registered", termClass);
    } else {
      registeredEnumClasses.add(termClass);
      for (T term : termClass.getEnumConstants()) {
        // add only the prefixed and qualified representation to avoid clashes
        addTerm(term.prefixedName(), term);
        addTerm(term.qualifiedName(), term);
      }
    }
  }

  private void addTerm(Term term, String ... altPrefixes) {
    addTerm(term.simpleName(), term);
    addTerm(term.prefixedName(), term);
    addTerm(term.qualifiedName(), term);
    for (String pre : altPrefixes) {
      addTerm(pre + ":" + term.simpleName(), term);
    }
  }

  private void addTerm(String key, Term term) {
    if (key == null || key.trim().isEmpty()) {
      return;
    }

    // keep class terms distinct
    Map<String, Term> map = termMap(term.isClass());
    if (map.containsKey(key)) {
      Term t1 = map.get(key);
      if (!t1.equals(term)) {
        LOG.info("{} terms {} and {} are both known as \"{}\". Keeping only earlier {}", term.isClass() ? "Class" : "Property", map.get(key), term, key, map.get(key));
      }
    } else {
      map.put(key, term);
      // also add a normalised version
      key = normaliseTerm(key);
      if (!map.containsKey(key)) {
        map.put(key, term);
      }
    }
  }

  private Map<String, Term> termMap(boolean isClass) {
    return isClass ? classTerms : terms;
  }

  /**
   * @return a purely alphanumerical, lower cased term with all other characters replaced
   */
  public static String normaliseTerm(String term) {
    String x = NON_ALPHA_NUM_PATTERN.matcher(term).replaceAll("");
    // remove http(s)
    x = x.replaceFirst("^https?", "");
    if (x.isEmpty()) {
      return "";
    }
    return x.toLowerCase();
  }

  /**
   * This is the main method to get a term from the factory searching both for property or class terms.
   * It will lookup matching terms applying some normalization and known synonyms first.
   * In case of ambiguous terms Class terms will be preferred.
   *
   * If nothing matches the factory creates a new UnknownTerm property instance and keeps it for further requests so that
   * all terms with the same qualified name return a single UnknownTerm instance.
   *
   * For clearly bad term names an IllegalArgumentException is thrown.
   * For example in the case of a simple name containing whitespace like "hello tom".
   * Ideally the term names to be looked up should be full URIs, but simple names made up of alphanumerics and dashes
   * will also work fine. Unknown simple names will be put into the namespace http://unknown.org when a new UnknownTerm
   * instance is created.
   */
  public Term findTerm(final String termName) throws IllegalArgumentException {
    // First try an exact match.
    if (terms.containsKey(termName)) {
      return terms.get(termName);
    }

    // Try class term
    Term t = findTermOnly(termName, true);
    if (t == null) {
      // Try property term
      t = findTermOnly(termName, false);
    }
    // create new term if needed
    if (t == null) {
      if (termName.startsWith(BibTexTerm.NS) || termName.startsWith(BibTexTerm.PREFIX+":")) {
        t = createBibtexTerm(termName, termName.startsWith(BibTexTerm.NS));
      } else {
        t = createUnknownTerm(termName, false);
      }
    }
    return t;
  }

  /**
   * This method works just as findTerm(final String termName) but restricts
   * the results to just property terms.
   */
  public Term findPropertyTerm(final String termName) throws IllegalArgumentException {
    return findTerm(termName, false);
  }

  /**
   * This method works just as findTerm(final String termName) but restricts
   * the results to just class terms.
   */
  public Term findClassTerm(final String termName) throws IllegalArgumentException {
    return findTerm(termName, true);
  }

  /**
   * This method works just as findTerm(final String termName) but restricts
   * the results to just property or class terms.
   */
  public Term findTerm(final String termName, boolean isClassTerm) throws IllegalArgumentException {
    if (termName == null || termName.trim().isEmpty()) {
      return null;
    }

    Term t = findTermOnly(termName, isClassTerm);
    // create new term if needed
    if (t == null) {
      t = createUnknownTerm(termName, isClassTerm);
    }
    return t;
  }

  /**
   * Does not create Unknown terms
   */
  private Term findTermOnly(final String termName, boolean isClassTerm) throws IllegalArgumentException {
    if (termName == null || termName.trim().isEmpty()) {
      return null;
    }

    Map<String, Term> map = termMap(isClassTerm);
    // first try term just as it is
    if (map.containsKey(termName)) {
      return map.get(termName);
    }

    // try normalised term otherwise
    if (map.containsKey(normaliseTerm(termName))) {
      return map.get(normaliseTerm(termName));
    }
    return null;
  }

  private Term createUnknownTerm(String termName, boolean isClassTerm) {
    // create new term instance
    Term term = UnknownTerm.build(termName, isClassTerm);
    addTerm(termName, term);
    addTerm(term.qualifiedName(), term);
    return term;
  }

  private Term createBibtexTerm(String termName, boolean qualified) {
    // create new term instance
    Term term = qualified ? BibTexTerm.buildFromURI(termName) : BibTexTerm.buildFromPrefix(termName);
    addTerm(term.qualifiedName(), term);
    addTerm(term.prefixedName(), term);
    return term;
  }

}
