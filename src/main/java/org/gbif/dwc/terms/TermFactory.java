package org.gbif.dwc.terms;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple factory for terms that knows about all ConceptTerms of this library and keeps singletons for
 * all unknown Term instances.
 */
public class TermFactory {
    private final static Logger LOG = LoggerFactory.getLogger(TermFactory.class);
    private static final Pattern NON_ALPHA_NUM_PATTERN = Pattern.compile("[^a-zA-Z0-9]+");

    private static TermFactory singleton;

    private final Map<String, Term> terms = new HashMap<String, Term>();

    public static TermFactory instance() {
      if (singleton == null) {
        singleton = new TermFactory();
        singleton.loadKnownTerms();
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

    private void addTerms(Term[] terms, String[] prefixes) {
        for (Term term : terms) {
            addTerm(term.simpleName(), term);
            addTerm(term.qualifiedName(), term);
            for (String pre : prefixes) {
                addTerm(pre + term.simpleName(), term);
            }
            // alt names
            for (String alt : term.alternativeNames()) {
                addTerm(alt, term);
                for (String pre : prefixes) {
                    addTerm(pre + alt, term);
                }
            }

        }
    }

    private void addTerm(String key, Term term) {
        if (key == null || key.trim().isEmpty()) {
            return;
        }
        key = normaliseTerm(key);
        if (terms.containsKey(key)) {
            Term t1 = terms.get(key);
            if (!t1.equals(term)) {
                LOG.warn("Terms {} and {} are both known as \"{}\". Keeping only {}", terms.get(key), term, key, terms.get(key));
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

    public Term findTerm(String termName) {
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
            int pos = termName.lastIndexOf("/");
            String tmpName = pos == -1 ? "" : termName.substring(pos + 1);
            term = new UnknownTerm(termName, tmpName);
            addTerm(normTermName, term);
            addTerm(termName, term);
            addTerm(tmpName, term);
        }
        return term;
    }

}
