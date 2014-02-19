package org.gbif.dwc.terms;

import java.util.HashMap;
import java.util.Map;
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
  private static final String UNKNOWN_NAMESPACE = "http://unknown.org/";
  private static TermFactory singleton;
  private static boolean initialized = false;
  private static final Object LOCK = new Object();

  private final Map<String, Term> terms = new HashMap<String, Term>();

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
    addTerms(DwcTerm.values(), DwcTerm.PREFIXES);
    addTerms(DcTerm.values(), DcTerm.PREFIXES);
    addTerms(GbifTerm.values(), GbifTerm.PREFIXES);
    addTerms(IucnTerm.values(), IucnTerm.PREFIXES);
  }

  private <T extends Term & AlternativeNames> void addTerms(T[] terms, String[] prefixes) {
    for (T term : terms) {
      addTerm(term.simpleName(), term);
      addTerm(term.qualifiedName(), term);
      for (String pre : prefixes) {
        addTerm(pre + term.simpleName(), term);
      }
      // also index alt names
      for (String alt : term.alternativeNames()) {
        addTerm(alt, term);
        for (String pre : prefixes) {
          addTerm(pre + alt, term);
        }
      }
    }
  }

  public void addTerm(String key, Term term) {
    if (key == null || key.trim().isEmpty()) {
      return;
    }
    key = normaliseTerm(key);
    if (terms.containsKey(key)) {
      Term t1 = terms.get(key);
      if (!t1.equals(term)) {
        LOG
          .warn("Terms {} and {} are both known as \"{}\". Keeping only {}", terms.get(key), term, key, terms.get(key));
      }
    } else {
      terms.put(key, term);
    }
  }

  /**
   * @return a purely alphanumerical, lower cased term with all other characters replaced
   */
  public static String normaliseTerm(String term) {
    return NON_ALPHA_NUM_PATTERN.matcher(term).replaceAll("").toLowerCase();
  }

  /**
   * This is the main method to get a term from the factory.
   * It will lookup matching terms applying some normalization and known synonyms first.
   * If nothing matches the factory creates a new UnknownTerm instance and keeps it for further requests so that
   * all terms with the same qualified name return a single UnknownTerm instance.
   *
   * For clearly bad term names an IllegalArgumentException is thrown.
   * For example in the case of a simple name containing whitespace like "hello tom".
   * Ideally the term names to be looked up should be full URIs, but simple names made up of alphanumerics and dashes
   * will also work fine. Unknown simple names will be put into the namespace http://unknown.org when a new UnknownTerm
   * instance is created.
   */
  public Term findTerm(String termName) throws IllegalArgumentException {
    if (termName == null || termName.trim().isEmpty()) {
      return null;
    }
    // normalise terms
    Term term;
    String normTermName = normaliseTerm(termName);
    // try known terms first
    if (terms.containsKey(normTermName)) {
      return terms.get(normTermName);
    } else {
      // create new term instance
      try {
        term = UnknownTerm.build(termName);
      } catch (IllegalArgumentException e) {
        // simple names as found in ATB file headers are rejected
        // convert into a standard unknown term namespace and try again
        term = UnknownTerm.build(UNKNOWN_NAMESPACE + termName);
      }
      addTerm(normTermName, term);
      addTerm(termName, term);
    }
    return term;
  }

}
