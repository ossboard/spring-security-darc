package com.konantech.spring.darcLib.query.search;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeTerm extends SearchTerm {

    private static final long serialVersionUID = 4599981700942929707L;

    protected List<SearchTerm> terms;

    protected CompositeTerm() {
        this(new ArrayList<SearchTerm>());
    }

    protected CompositeTerm(List<SearchTerm> terms) {
        this.terms = terms;
    }

    public CompositeTerm addTerm(SearchTerm term) {
        if (term != null) {
            terms.add(term);
        }
        return this;
    }
    public List<SearchTerm> getTerms() {
        return terms;
    }

    public int size() {
        return terms.size();
    }

    protected String toString(String operator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (terms != null && terms.size() > 0) {
            for (SearchTerm term : terms) {
                if (i++ > 0) {
                    sb.append(" ").append(operator).append(" ");
                }
                sb.append(term.toString());
            }
        }
        return sb.toString();
    }

}
